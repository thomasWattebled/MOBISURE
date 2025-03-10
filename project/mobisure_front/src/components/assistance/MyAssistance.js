import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import UserService from '../../services/userService';
import { useAuth } from '../auth/AuthContext';
import AssistanceForm from './AssistanceForm';

const MyAssistance = () => {
	
	const [user, setUser] = useState(null);
	const [assistanceList, setAssistanceList] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [selectedMessage, setSelectedMessage] = useState(""); // 🔹 Stocke le message sélectionné
	const [showModal, setShowModal] = useState(false); // 🔹 État pour afficher/masquer la modale
	const [showAssistanceForm, setShowAssistanceForm] = useState(false);
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

	   if (loading) {
	     return <p>Chargement des demandes d'assistance...</p>;
	   }

	   if (error) {
	     return <p>Erreur : {error}</p>;
	   }
	
	 return (
	     <div>
	         <h1>Vos demandes d'assistances</h1>
			 
			 <button className="btn btn-primary mb-3" onClick={openAssistanceForm}>
			 	Ajouter une demande
			 </button>
	         
	         <table>
	             <thead>
	                 <tr>
					 <th>Numéro dossier</th>
					 <th>Date</th>
					 <th>Type d'assistance</th>
					 <th>Message</th>
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
			 	        {/* 🔹 Ajoute un fond pour la modale */}
			 	        {showModal && <div className="modal-backdrop fade show"></div>}
						
						{showAssistanceForm && (
										<div className="modal fade show d-block">
											<div >
												<div className="modal-content">
													<div className="modal-header">
														<h5 className="modal-title">Nouvelle demande d'assistance</h5>
														<button type="button" className="btn-close" onClick={closeAssistanceForm}></button>
													</div>
													<div className="modal-body">
														<AssistanceForm />
													</div>
													<div className="modal-footer">
														<button type="button" className="btn btn-secondary" onClick={closeAssistanceForm}>
															Fermer
														</button>
													</div>
												</div>
											</div>
										</div>
									)}

									{/* 🔹 Ajoute un fond pour la modale */}
									{(showModal || showAssistanceForm) && <div className="modal-backdrop fade show"></div>}
	     </div>
	 );

};

export default MyAssistance;