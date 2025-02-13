import React, { useState, useEffect, useRef } from "react";
import { useAuth } from '../auth/AuthContext';
import UserService from '../../services/userService';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import "../../assets/css/messagerie.css";

const MessageApp = () => {
  const { getUser } = useAuth();
  const userDetails = getUser(); // Détails de l'utilisateur connecté
  const [loading, setLoading] = useState(true);
  const [user, setUser] = useState(null);
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState("");
  const stompClient = useRef(null);

  useEffect(() => {
    UserService.fetchUserByEmail(userDetails.unsername).then(data => {
	console.log(userDetails)
      setUser(data);
      setLoading(false);
    });
  }, []);

  useEffect(() => {
	console.log(user)
    if (user) {
      fetchUsers();
      connectWebSocket();
    }
  }, [user]);

  const connectWebSocket = () => {
    const socket = new SockJS('http://localhost:8081/ws');
    stompClient.current = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 5000,
      onConnect: () => {
        stompClient.current.subscribe(`/topic/messages/${user.id}`, (message) => {
          const newMessage = JSON.parse(message.body);
          setMessages(prevMessages => [...prevMessages, newMessage]);
        });
      },
      onStompError: (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
      },
    });

    stompClient.current.activate();
  };

  const fetchUsers = async () => {
    try {
      const response = await fetch("http://localhost:8080/users", { credentials: 'include' });
      const data = await response.json();
	  console.log("la");
      const filterUser = data.filter((u) => u.id !== user.id);
      setUsers(filterUser);
    } catch (error) {
      console.error("Erreur lors de la récupération des utilisateurs :", error);
    }
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

  const sendMessage = async () => {
    if (!newMessage || !selectedUser || !user) return;

    // Créez un objet message local
    const message = {
      expediteurId: user.id,
      receveurId: selectedUser.id,
      contenu: newMessage,
    };

    // Ajoutez le message à l'état local immédiatement
    setMessages(prevMessages => [...prevMessages, message]);
    setNewMessage(""); // Réinitialisez le champ de saisie

    try {
      // Envoyez le message au serveur
      await fetch("http://localhost:8081/send", {
        method: "POST",
        credentials: 'include',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(message),
      });
    } catch (error) {
      console.error("Erreur lors de l'envoi du message :", error);

      // Si l'envoi échoue, retirez le message de l'état local
      setMessages(prevMessages => prevMessages.filter(m => m !== message));
    }
  };

  return (
    <div className="message-app">
      <div className="user-list">
        <h2>Utilisateurs</h2>
        <ul>
          {users.map((userItem) => (
            <li
              key={userItem.id}
              onClick={() => {
                setSelectedUser(userItem);
                fetchConversation(userItem.id);
              }}
            >
              {userItem.nom}
            </li>
          ))}
        </ul>
      </div>

      <div className="chat-container">
        {selectedUser ? (
          <>
            <h2 className="chat-header">Conversation avec {selectedUser.nom}</h2>
            <div className="message-list">
              {messages.map((message, index) => (
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
