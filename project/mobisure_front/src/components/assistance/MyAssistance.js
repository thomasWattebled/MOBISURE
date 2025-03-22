import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import UserService from '../../services/userService';
import { useAuth } from '../auth/AuthContext';
import AssistanceForm from './AssistanceForm';
import UpdateAssistance from './UpdateAssistance';
import '../../assets/css/myAssistance.css';

const MyAssistance = () => {
	
	const [user, setUser] = useState(null);
	const [assistanceList, setAssistanceList] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [selectedMessage, setSelectedMessage] = useState(""); // 🔹 Stocke le message sélectionné
	const [selectDossier, setSelectedDossier] = useState("");
	const [showModal, setShowModal] = useState(false); // 🔹 État pour afficher/masquer la modale
	const [showAssistanceForm, setShowAssistanceForm] = useState(false);
	const [showGestion, setGestion] = useState(false);
	const { getUser } = useAuth();
	const userDetails = getUser();
	
	useEffect(() => {
	   
	   UserService.fetchUserByEmail(userDetails.unsername).then(data => {
	     setUser(data);
	   });

	   setLoading(false);
	 }, []);
	 
	 useEffect(() => {
		
		if (!user || !user.id) return;
		console.log(user.id);
		fetch(`http://localhost:8081/assistance/getMyAssistance/${user.id}`)
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
		
	 },[user]);
	 
	 const openMessageModal = (message) => {
	       setSelectedMessage(message);
	       setShowModal(true);
	     };

	     const closeMessageModal = () => {
	       setShowModal(false);
	     };
		 
		 const openAssistanceForm = () => {
		 		setShowAssistanceForm(true);
		 	};

		 	const closeAssistanceForm = () => {
		 		setShowAssistanceForm(false);
		 	};
			
		const openGestion = (numDossier) => {
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
	     <div className="my-assistance-container">
	       <h1 className="my-assistance-title">Vos demandes d'assistances</h1>
	       <button className="add-demande" onClick={openAssistanceForm}>
	         Ajouter une demande
	       </button>
	       <table className="my-assistance-table">
	         <thead>
	           <tr>
	             <th>Numéro dossier</th>
	             <th>Date</th>
	             <th>Type d'assistance</th>
	             <th>Message</th>
	             <th>Gestion de l'assistance</th>
	           </tr>
	         </thead>
	         <tbody>
	           {assistanceList.map((assistance) => (
	             <tr key={assistance.id}>
	               <td>{assistance.num_dossier}</td>
	               <td>{new Date(assistance.date).toLocaleDateString()}</td>
	               <td>{assistance.type}</td>
	               <td>
	                 <button onClick={() => openMessageModal(assistance.message)}>
	                   Voir message
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
	         <div className="modal">
	           <div className="modal-content">
	             <div className="modal-header">
	               <h5 className="modal-title">Message de l'utilisateur</h5>
	               <button type="button" onClick={closeMessageModal}>×</button>
	             </div>
	             <div className="modal-body">
	               <p>{selectedMessage}</p>
	             </div>
	             <div className="modal-footer">
	               <button type="button" onClick={closeMessageModal}>
	                 Fermer
	               </button>
	             </div>
	           </div>
	         </div>
	       )}

	       {showAssistanceForm && (
	         <div className="modal">
	           <div className="modal-content">
	             <div className="modal-header">
	               <h5 className="modal-title">Nouvelle demande d'assistance</h5>
	               <button type="button" onClick={closeAssistanceForm}>×</button>
	             </div>
	             <div className="modal-body">
	               <AssistanceForm />
	             </div>
	             <div className="modal-footer">
	               <button type="button" onClick={closeAssistanceForm}>
	                 Fermer
	               </button>
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

	       {(showModal || showAssistanceForm || showGestion) && (
	         <div className="modal-backdrop"></div>
	       )}
	     </div>
	   );

};

export default MyAssistance;