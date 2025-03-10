import React, { useState } from 'react';
import FormField from '../register/FormField';
import SuccessDemande from './SuccesDemande';

const AssistanceFormView = ({ formData, handleChange, handleSubmit, isModalVisible, setModalVisible }) => {

  const handleModalClose = () => {
    setModalVisible(false); // Fermer le modal
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h2 className="card-title text-center">Demande d'Assistance</h2>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label className="form-label">Type d'assistance :</label>
                  <select name="type" value={formData.type} onChange={handleChange} className="form-select" required>
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
				<div className="mb-3">
				  <label htmlFor="message" className="form-label">Message :</label>
				  <textarea
				    id="message"
				    name="message"
				    value={formData.message}
				    onChange={handleChange}
				    placeholder="Décrivez votre demande"
				    className="form-control"
				    rows="5" // Ajuste le nombre de lignes affichées
					maxLength={1000}
				    required
				  />
				  <small className="text-muted">
				  	{formData.message.length} / {1000} caractères
				  </small>
				</div>

                <button type="submit" className="btn btn-primary w-100">
                  Envoyer la demande
                </button>
              </form>
            </div>
			<p>En cas d'urgence, merci de contacter le : 03 20 17 59 47</p>
          </div>
        </div>
      </div>
      <SuccessDemande show={isModalVisible} onClose={handleModalClose} />
    </div>
  );
};

export default AssistanceFormView;
