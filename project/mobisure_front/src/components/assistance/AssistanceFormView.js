import React, { useState } from 'react';
import SuccessDemande from './SuccesDemande';
import '../../assets/css/addAssistance.css';

const AssistanceFormView = ({ formData, handleChange, handleSubmit, isModalVisible, setModalVisible }) => {
  const handleModalClose = () => {
    setModalVisible(false); // Fermer le modal
  };

  return (
    <div className="assistance-form-container">
      <div className="assistance-form-card">
        <h2 htmlFor="type-assistance" className="assistance-form-title">Demande d'Assistance</h2>
        <form onSubmit={handleSubmit} className="assistance-form">
          <div className="form-group">
		  <label htmlFor="type-assistance" className="form-label">Type d'assistance :</label>
			<select
			id="type-assistance"
			name="type"
              value={formData.type}
              onChange={handleChange}
              className="form-control"
              required
            >
              <option value="">Sélectionner un type de demande</option>
              <option value="AUTO">Dépannage auto</option>
              <option value="MEDICAL">Assistance médicale</option>
              <option value="CONTRAT">Question sur vos contrats</option>
              <option value="ACCIDENT">Accident de la route</option>
              <option value="VOYAGE">Assistance voyage</option>
              <option value="REMBOURSEMENT">Demande de remboursement</option>
              <option value="AUTRE">Autres demandes</option>
            </select>
          </div>
		  {(formData.type === "AUTO" || formData.type === "ACCIDENT" ) && (
		  	<div>
		    	<div className="form-group">
		         	<label htmlFor="ville" className="form-label">Ville :</label>
		            <input
		            	type="text"
		            	id="ville"
		            	name="ville"
		            	value={formData.ville || ""}
		            	onChange={handleChange}
		            	placeholder="Entrez votre ville"
		            	className="form-control"
		            	required
		            />
		            </div>

		                <div className="form-group">
		                  <label htmlFor="rue" className="form-label">Rue :</label>
		                  <input
		                    type="text"
		                    id="rue"
		                    name="rue"
		                    value={formData.rue || ""}
		                    onChange={handleChange}
		                    placeholder="Entrez votre rue"
		                    className="form-control"
		                    required
		                  />
		                </div>
		              </div>
		            )}
					
			{formData.type === "ACCIDENT" && (
				<div>
					<div className="form-group">
						<label htmlFor="nbBlesse" className="form-label">Nombre de bléssés :</label>
						<input
							type="number"
							id="nbBlesse"
							name="nbBlesse"
							value={formData.nbBlesse || 0}
							onChange={handleChange}
							placeholder="Entrez le nombre de bléssés"
							className="form-control"
							required
						/>
					</div>
				</div>
			)}
				
			{formData.type === "REMBOURSEMENT" && (
				<div>
					<div className="form-group">
						<label htmlFor="montant" className="form-label">Montant du remboursement :</label>
						<input
							type="number"
							id="montant"
							name="montant"
							value={formData.montant}
							onChange={handleChange}
							placeholder="Entrez le montant du remboursement"
							className="form-control"
							required
						/>
					</div>
				</div>
			)}
			
			{(formData.type === "REMBOURSEMENT" || formData.type === "MEDICAL")&& (
				<div>
					<div className="form-group">
						<label htmlFor="motif" className="form-label">Entrer le motif de votre demande :</label>
							<input
								type="text"
								id="motif"
								name="motif"
								value={formData.motif}
								onChange={handleChange}
								placeholder="Entrez le motif de la demande"
								className="form-control"
								required
							/>
					</div>
				</div>
			)}	
          <div className="form-group">
            <label htmlFor="message" className="form-label">
              Message :
            </label>
            <textarea
              id="message"
              name="message"
              value={formData.message}
              onChange={handleChange}
              placeholder="Décrivez votre demande"
              className="form-control"
              rows="5"
              maxLength={1000}
              required
            />
            <small className="form-text">
              {formData.message.length} / {1000} caractères
            </small>
          </div>
          <button type="submit" className="submit-button">
            Envoyer la demande
          </button>
        </form>
        <p className="urgence-message">En cas d'urgence, merci de contacter le : 03 20 17 59 47</p>
      </div>
      <SuccessDemande id='success-modal' show={isModalVisible} onClose={handleModalClose} />
    </div>
  );
  
};

export default AssistanceFormView;