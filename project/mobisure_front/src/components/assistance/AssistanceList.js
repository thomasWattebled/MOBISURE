import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import '../../assets/css/assistance.css';

const AssistanceList = () => {
  const [assistanceList, setAssistanceList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [filters, setFilters] = useState({ nom: "", prenom: "", mail: "", telephone: "", type: "", status: "" });
  const [showFilters, setShowFilters] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://localhost:8081/assistance/all')
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
  }, []);

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
	(!filters.status || assistance.status === filters.status)
  );

  if (loading) {
    return <p>Chargement des demandes d'assistance...</p>;
  }

  if (error) {
    return <p>Erreur : {error}</p>;
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center">Liste des Demandes d'Assistance</h2>
      <button className="btn btn-primary mb-3" onClick={() => setShowFilters(!showFilters)}>
        {showFilters ? "Masquer les filtres" : "Afficher les filtres"}
      </button>
      {showFilters && (
        <div className="mb-3">
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
		  <p>Mail :
          <input 
            type="text" 
            placeholder="Mail" 
            value={filters.mail} 
            onChange={(e) => setFilters({ ...filters, mail: e.target.value })}
            className="form-control mb-2"
          />
		  </p>
		  <p>Téléphone :
          <input 
            type="text" 
            placeholder="Téléphone" 
            value={filters.telephone} 
            onChange={(e) => setFilters({ ...filters, telephone: e.target.value })}
            className="form-control"
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
            <th>Nom</th>
            <th>Prenom</th>
            <th>Mail</th>
            <th>Numéro de téléphone</th>
            <th>Type</th>
            <th>Status</th>
            <th>Date</th>
            <th>Message</th>
            <th>Contacter</th>
          </tr>
        </thead>
        <tbody>
          {filteredList.map((assistance) => (
            <tr key={assistance.id}>
              <td>{assistance.nom}</td>
              <td>{assistance.prenom}</td>
              <td>{assistance.mail}</td>
              <td>{assistance.telephone}</td>
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
              <td>{assistance.message}</td>
              <td>
                <button id="btn-conversation" type="button" onClick={() => startConversation(assistance.id_client)}>
                  Démarrer une conversation
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AssistanceList;

