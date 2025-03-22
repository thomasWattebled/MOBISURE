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
        <h2 className="assistance-form-title">Demande d'Assistance</h2>
        <form onSubmit={handleSubmit} className="assistance-form">
          <div className="form-group">
            <label className="form-label">Type d'assistance :</label>
            <select
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
      <SuccessDemande show={isModalVisible} onClose={handleModalClose} />
    </div>
  );
  
};

export default AssistanceFormView;