import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";


const AssistanceList = () => {
	
  const [assistanceList, setAssistanceList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  // Fonction pour récupérer les demandes d'assistance depuis l'API
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
  
  const startConversation = async (userId) => {
      navigate(`/messagerie/${userId}`);
    };
	
	const handleStatusChange = async (id, newStatus) => {
		try{
			const response = await fetch(`http://localhost:8081/assistance/updateStatus/${id}`,{
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify({ status: newStatus }),
			});
			
			if(!response.ok){
				throw new Error('Erreur lors de la mise à jour du statut');
			}
			setAssistanceList(prevList =>
				prevList.map(item =>
					item.id === id ? {...item, status: newStatus} : item
				)
			);
		} catch(error){
			alert("Erreur lors de la mise à jour du statut");
		}
	}
 

  if (loading) {
    return <p>Chargement des demandes d'assistance...</p>;
  }

  if (error) {
    return <p>Erreur : {error}</p>;
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center">Liste des Demandes d'Assistance</h2>
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
          {assistanceList.map((assistance) => (
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
			  	<button id="btn-conversation" type="button" onClick={() => startConversation(assistance.id_client)}>Démarrer une conversation</button>
			  </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AssistanceList;
