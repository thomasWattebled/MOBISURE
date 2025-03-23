// VoyageProfessionnelForm.js
import React, { useState } from 'react';
import '../../style/form.css';

const VoyageProfessionnelForm = ({ onSubmit }) => {
  const [formData, setFormData] = useState({
    companyName: '',
    destination: '',
    dateDepart: '',
    dateRetour:'',
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
    console.log('Form Data Submitted:', formData);
  };

  return (
    <div className="form-container">
      <h3>Formulaire pour Voyage Professionnel</h3>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="companyName">Nom de l'entreprise :</label>
          <input
            type="text"
            id="companyName"
            name="companyName"
            value={formData.companyName}
            onChange={handleInputChange}
            required
            placeholder="Entrez le nom de votre entreprise"
          />
        </div>
        <div className="form-group">
          <label htmlFor="destination">Destination :</label>
          <input
            type="text"
            id="destination"
            name="destination"
            value={formData.destination}
            onChange={handleInputChange}
            required
            placeholder="Entrez la destination"
          />
        </div>
        <div className="form-group">
          <label htmlFor="dateDepart">Date Depart :</label>
          <input
            type="date"
            id="dateDepart"
            name="dateDepart"
            value={formData.dateDepart}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="dateRetour">Date Retour :</label>
          <input
            type="date"
            id="dateRetour"
            name="dateRetour"
            value={formData.dateRetour}
            onChange={handleInputChange}
            required
          />
        </div>
        <button type="submit">Soumettre</button>
      </form>
    </div>
  );
};

export default VoyageProfessionnelForm;
