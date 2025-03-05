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
                <FormField
                  id="message"
                  label="Message :"
                  type="text"
                  name="message"
                  value={formData.message}
                  placeholder="Décrivez votre demande"
                  handleChange={handleChange}
                  required
                />
                <button type="submit" className="btn btn-primary w-100">
                  Envoyer la demande
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
      <SuccessDemande show={isModalVisible} onClose={handleModalClose} />
    </div>
  );
};

export default AssistanceFormView;
