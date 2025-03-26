import React, { useState, useEffect, useRef } from "react";
import { useAuth } from '../auth/AuthContext';
import UserService from '../../services/userService';
import AdminService from '../../services/adminService';
import SockJS from 'sockjs-client';
import { useParams } from "react-router-dom";
import { Client } from '@stomp/stompjs';
import "../../assets/css/messagerie.css";

const MessageApp = () => {
  const { userId } = useParams(); // Récupère l'ID de l'utilisateur sélectionné depuis l'URL
  const { getUser } = useAuth();
  const userDetails = getUser();
  const [loading, setLoading] = useState(true);
  const [user, setUser] = useState(null);
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState("");
  const stompClient = useRef(null);
  const [showUserList, setShowUserList] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [allUsers, setAllUsers] = useState([]);

  console.log(userId);
  useEffect(() => {
    // Charge les données de l'utilisateur connecté
    UserService.fetchUserByEmail(userDetails.unsername).then(data => {
      setUser(data);
    });

    // Charge tous les utilisateurs
    AdminService.fetchAllUsers().then(data => {
      setAllUsers(data);
    });

    setLoading(false);
  }, []);

  useEffect(() => {
    if (user && allUsers.length > 0) {
      fetchUserConversations(); // Charge les conversations de l'utilisateur
      connectWebSocket(); // Connecte le WebSocket pour les messages en temps réel
    }
  }, [user, allUsers]);

  useEffect(() => {
    if (userId && allUsers.length > 0) {
      // Trouve l'utilisateur sélectionné dans la liste des utilisateurs
      const selected = allUsers.find(u => u.id === parseInt(userId));
      if (selected) {
        setSelectedUser(selected);
        fetchConversation(selected.id); // Charge les messages de la conversation
      }
    }
  }, [userId, allUsers]);
  
  useEffect(() => {
    if (selectedUser) {
      fetchConversation(selectedUser.id); // Charge les messages lorsque l'utilisateur sélectionné change
    }
  }, [selectedUser]);

  const connectWebSocket = () => {
      if (stompClient.current) {
        stompClient.current.deactivate();
      }

      const socket = new SockJS('http://localhost:8081/ws');
      stompClient.current = new Client({
        webSocketFactory: () => socket,
        reconnectDelay: 5000,
        onConnect: () => {
          stompClient.current.subscribe(`/topic/messages/${user.id}`, (message) => {
            const newMessage = JSON.parse(message.body);

            // Vérifie si le message a un contenu non vide et est destiné à l'utilisateur sélectionné
            if (newMessage.contenu && newMessage.contenu.trim() !== "") {
              setMessages((prevMessages) => {
                if (selectedUser && newMessage.expediteurId === selectedUser.id) {
                  return [...prevMessages, newMessage];
                }
                return prevMessages;
              });
            }
          });
        },
        onStompError: (frame) => {
          console.error('Broker reported error: ' + frame.headers['message']);
          console.error('Additional details: ' + frame.body);
        },
      });

      stompClient.current.activate();
    };

  const fetchConversation = async (receveurId) => {
    if (!user) return;
    try {
      const response = await fetch("http://localhost:8081/Conversation", {
        method: "POST",
        credentials: 'include',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ expediteurId: user.id, receveurId }),
      });
      const data = await response.json();
      setMessages(data);
    } catch (error) {
      console.error("Erreur lors de la récupération des messages :", error);
    }
  };

  const fetchUserConversations = async () => {
    if (!user) return;

    try {
      const response = await fetch(`http://localhost:8081/MyMessage/${user.id}`, {
        credentials: 'include',
      });
      const data = await response.json();

      // Récupérer les utilisateurs uniques des conversations
      const uniqueUserIds = new Set();
      const userConversations = data.reduce((acc, message) => {
        const otherUserId = message.expediteurId === user.id ? message.receveurId : message.expediteurId;
        if (!uniqueUserIds.has(otherUserId)) {
          uniqueUserIds.add(otherUserId);
          acc.push({
            id: otherUserId,
            nom: allUsers.find((u) => u.id === otherUserId)?.nom || "Utilisateur inconnu",
          });
        }
        return acc;
      }, []);

      setUsers(userConversations); // Met à jour la liste des utilisateurs avec lesquels l'utilisateur a conversé
    } catch (error) {
      console.error("Erreur lors de la récupération des conversations :", error);
    }
  };

  const sendMessage = async () => {

    if (!newMessage || !selectedUser || !user) return;

    // Vérifie si une conversation existe déjà
    const conversationExists = await checkConversationExists(selectedUser.id);

    let conversationId;
    if (!conversationExists) {
      // Crée une nouvelle conversation si elle n'existe pas
      console.log("la");
      const response = await fetch("http://localhost:8081/createConversation", {
        method: "POST",
        credentials: 'include',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ expediteurId: user.id, receveurId: selectedUser.id }),
      });
      const conversation = await response.json();
      conversationId = conversation.id;
    } else {
      conversationId = conversationExists.id;
    }

    // Crée un objet message local
    const message = {
      conversationId: conversationId,
      expediteurId: user.id,
      receveurId: selectedUser.id,
      contenu: newMessage,
    };

    // Ajoute le message à l'état local immédiatement
    setMessages(prevMessages => [...prevMessages, message]);
    setNewMessage(""); // Réinitialise le champ de saisie

    try {
      // Envoie le message au serveur
      await fetch("http://localhost:8081/send", {
        method: "POST",
        credentials: 'include',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(message),
      });
    } catch (error) {
      console.error("Erreur lors de l'envoi du message :", error);

      // Si l'envoi échoue, retire le message de l'état local
      setMessages(prevMessages => prevMessages.filter(m => m !== message));
    }
  };

  const checkConversationExists = async (userId) => {
    try {
      const response = await fetch(`http://localhost:8081/checkConversation/${user.id}/${userId}`, {
        credentials: 'include',
      });
      const data = await response.json();
      return data;
    } catch (error) {
      console.error("Erreur lors de la vérification de la conversation :", error);
      return false;
    }
  };
  
  const handleUserSelect = (userItem) => {
    setSelectedUser(userItem); // Met à jour l'utilisateur sélectionné
    fetchConversation(userItem.id); // Charge les messages de la conversation
  };

  return (
    <div className="message-app">
      {/* Colonne latérale pour la liste des utilisateurs */}
	  <div className="user-list">
	    <h2>Conversations</h2>
	    <ul>
	      {users.map((userItem) => (
	        <li
	          key={userItem.id}
	          onClick={() => handleUserSelect(userItem)} // Appelle handleUserSelect au clic
	          className={selectedUser?.id === userItem.id ? "active" : ""}
	        >
	          {userItem.nom}
	        </li>
	      ))}
	    </ul>
	  </div>

	  {/* Zone de chat */}
	  <div className="chat-container">
	    {selectedUser ? (
	      <>
	        <h2 className="chat-header">Conversation avec {selectedUser.nom}</h2>
	        <div className="message-list">
	          {messages
	            .filter((message) => message.contenu && message.contenu.trim() !== "") // Filtre les messages vides ou null
	            .map((message, index) => (
	              <div
	                key={index}
	                className={`message ${message.expediteurId === user.id ? "sent" : "received"}`}
	              >
	                <strong>{message.expediteurId === user.id ? "Vous" : selectedUser.nom} :</strong>
	                <p>{message.contenu}</p>
	              </div>
	            ))}
	        </div>

	        <div className="message-input">
	          <textarea
	            value={newMessage}
	            onChange={(e) => setNewMessage(e.target.value)}
	            placeholder="Écrire un message..."
	            rows="3"
	          ></textarea>
	          <button onClick={sendMessage}>Envoyer</button>
	        </div>
	      </>
	    ) : (
	      <p className="select-user">Sélectionnez un utilisateur pour voir la conversation.</p>
	    )}
	  </div>
    </div>
  );
};

export default MessageApp;