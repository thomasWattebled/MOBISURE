import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import '../../assets/css/assistance.css';
import UpdateAssistance from './UpdateAssistance';
import UserService from '../../services/userService';
import { useAuth } from '../auth/AuthContext';

const MyFolder = () => {
  const [assistanceList, setAssistanceList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [filters, setFilters] = useState({ num_dossier: "", nom: "", prenom: "", mail: "", telephone: "", type: "", status: "" });
  const [showFilters, setShowFilters] = useState(false);
  const [selectedMessage, setSelectedMessage] = useState(""); // 🔹 Stocke le message sélectionné
  const [showModal, setShowModal] = useState(false); // 🔹 État pour afficher/masquer la modale
  const [showGestion, setGestion] = useState(false);
  const [selectDossier, setSelectedDossier] = useState("");
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const { getUser } = useAuth();
  const userDetails = getUser();
  
  useEffect(() => {
  	   
  	   UserService.fetchUserByEmail(userDetails.unsername).then(data => {
  	     setUser(data);
  	   });

  	   setLoading(false);
  	 }, []);

	 
  useEffect(() => {
	
	if (!user) return;
	
    fetch(`http://localhost:8081/access/myFolder?idUser=${user.id}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Erreur de récupération des données');
        }
        return response.json();
      })
      .then(data => {
        setAssistanceList(data);
        setLoading(false);
      })
      .catch(error => {
        setError(error.message);
        setLoading(false);
      });
  }, [user]);

  const startConversation = (userId) => {
    navigate(`/messagerie/${userId}`);
  };

  const handleStatusChange = async (id, newStatus) => {
    try {
      const response = await fetch(`http://localhost:8081/assistance/updateStatus/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ status: newStatus }),
      });

      if (!response.ok) {
        throw new Error('Erreur lors de la mise à jour du statut');
      }
      setAssistanceList(prevList =>
        prevList.map(item =>
          item.id === id ? { ...item, status: newStatus } : item
        )
      );
    } catch (error) {
      alert("Erreur lors de la mise à jour du statut");
    }
  };

  const filteredList = assistanceList.filter(assistance => 
    (!filters.nom || assistance.nom.toLowerCase().includes(filters.nom.toLowerCase())) &&
    (!filters.prenom || assistance.prenom.toLowerCase().includes(filters.prenom.toLowerCase())) &&
    (!filters.mail || assistance.mail.toLowerCase().includes(filters.mail.toLowerCase())) &&
    (!filters.telephone || assistance.telephone.includes(filters.telephone)) &&
	(!filters.type || assistance.type.toLowerCase().includes(filters.type.toLowerCase())) &&
	(!filters.status || assistance.status === filters.status) &&
	(!filters.num_dossier || assistance.num_dossier.toLowerCase().includes(filters.num_dossier.toLowerCase()))
  );
  
  const openMessageModal = (message) => {
      setSelectedMessage(message);
      setShowModal(true);
    };

    const closeMessageModal = () => {
      setShowModal(false);
    };
	
	const openGestion = async (numDossier) => {
		setGestion(true);
		setSelectedDossier(numDossier);
	}
	
	const closeGestion = () => {
		setGestion(false);
	}
			

  if (loading) {
    return <p>Chargement des demandes d'assistance...</p>;
  }

  if (error) {
    return <p>Erreur : {error}</p>;
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center">Mes dossiers</h2>
      <button className="btn btn-primary mb-3" onClick={() => setShowFilters(!showFilters)}>
        {showFilters ? "Masquer les filtres" : "Afficher les filtres"}
      </button>
      {showFilters && (
        <div className="mb-3">
		<p>Numéro du dossier : 
			<input 
		    	type="text" 
		        placeholder="Numéro du dossier" 
		        value={filters.num_dossier} 
		        onChange={(e) => setFilters({ ...filters, num_dossier: e.target.value })}
		        className="form-control mb-2"
		  	/>
		  </p>
		  <p>Nom : 
          <input 
            type="text" 
            placeholder="Nom" 
            value={filters.nom} 
            onChange={(e) => setFilters({ ...filters, nom: e.target.value })}
            className="form-control mb-2"
          />
		  </p>
		  <p>Prénom :
          <input 
            type="text" 
            placeholder="Prénom" 
            value={filters.prenom} 
            onChange={(e) => setFilters({ ...filters, prenom: e.target.value })}
            className="form-control mb-2"
          />
		  </p>
		  <p>Type :
		  <input 
		  	type="text" 
		   	placeholder="Type" 
		    value={filters.type} 
		    onChange={(e) => setFilters({ ...filters, type: e.target.value })}
		    className="form-control"
		    />
			</p>
			<p>Status : 
			<select
				value={filters.status}
			    onChange={(e) => setFilters({ ...filters, status: e.target.value })}
			    className="form-control mb-2"
			 >
			 	<option value="">Tous</option>
			   	<option value="ATTENTE">En attente</option>
			  	<option value="TRAITEMENT">En cours</option>
			    <option value="CLOTURER">Terminée</option>
			  </select>
			  </p>
        </div>
      )}
      <table className="table table-bordered">
        <thead>
          <tr>
		  	<th>Numéro dossier</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Type</th>
            <th>Status</th>
            <th>Date</th>
            <th>Message</th>
            <th>Contacter</th>
			<th>Gestion de l'assistance</th>
          </tr>
        </thead>
        <tbody>
          {filteredList.map((assistance) => (
            <tr key={assistance.id}>
			  <td>{assistance.num_dossier}</td>
              <td>{assistance.nom}</td>
              <td>{assistance.prenom}</td>
              <td>{assistance.type}</td>
              <td>
                <select
                  value={assistance.status}
                  onChange={(e) => handleStatusChange(assistance.id, e.target.value)}
                >
                  <option value="ATTENTE">En attente</option>
                  <option value="TRAITEMENT">En cours</option>
                  <option value="CLOTURER">Terminée</option>
                </select>
              </td>
              <td>{new Date(assistance.date).toLocaleDateString()}</td>
              <td>
			  	<button onClick={() => openMessageModal(assistance.message)}>
			    	Voir message
			  	</button>
			  </td>
              <td>
                <button id="btn-conversation" type="button" onClick={() => startConversation(assistance.id_client)}>
                  Démarrer une conversation
                </button>
              </td>
			  <td>
			  	<button onClick={() => openGestion(assistance.num_dossier)}>
			  		Gérer
			  	</button>
			  </td>
            </tr>
          ))}
        </tbody>
      </table>
	  
	  {showModal && (
	          <div className="modal fade show d-block" tabIndex="-1">
	            <div className="modal-dialog">
	              <div className="modal-content">
	                <div className="modal-header">
	                  <h5 className="modal-title">Message de l'utilisateur</h5>
	                  <button type="button" className="btn-close" onClick={closeMessageModal}></button>
	                </div>
	                <div className="modal-body">
	                  <p>{selectedMessage}</p>
	                </div>
	                <div className="modal-footer">
	                  <button type="button" className="btn btn-secondary" onClick={closeMessageModal}>
	                    Fermer
	                  </button>
	                </div>
	              </div>
	            </div>
	          </div>
	        )}
			
			{showGestion && (
				         <div className="modal">
				           <div className="modal-content">
				             <div className="modal-header">
				               <h5 className="modal-title">Vous modifiez le dossier suivant : {selectDossier}</h5>
				               <button type="button" onClick={closeGestion}>×</button>
				             </div>
				             <div className="modal-body">
				               <UpdateAssistance numDossier={selectDossier} />
				             </div>
				             <div className="modal-footer">
				               <button type="button" onClick={closeGestion}>
				                 Fermer
				               </button>
				             </div>
				           </div>
				         </div>
				       )}
			
	        {/* 🔹 Ajoute un fond pour la modale */}
	        {showModal && showGestion && <div className="modal-backdrop fade show"></div>}
    </div>
  );
};

export default MyFolder;