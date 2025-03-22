import React, { useState } from 'react';
import '../../style/form.css';

const VoyageVacanceForm = () => {
  const [formData, setFormData] = useState({
    paysDepart: '',
    destination: '',
    dateDepart: '',
    dateRetour:'',
    nbPersonnes:'',
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
    console.log('Form Data Submitted:', formData);
  };

  return (
    <div className="form-container">
      <h3>Formulaire pour Voyage Professionnel</h3>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="companyName">Pays de depart</label>
          <input
            type="text"
            id="paysDepart"
            name="paysDepart"
            value={formData.paysDepart}
            onChange={handleInputChange}
            required
            placeholder="Le pays de depart"
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
          <label htmlFor="dates">Date Depart :</label>
          <input
            type="date"
            id="dates"
            name="dates"
            value={formData.dateDepart}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="dates">Date Retour :</label>
          <input
            type="date"
            id="dates"
            name="dates"
            value={formData.dateRetour}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="dates">Nombre de personnes</label>
          <input
            type="text"
            id="nbPersonnes"
            name="nbPersonnes"
            value={formData.nbPersonnes}
            onChange={handleInputChange}
            required
            placeholder="Pour combien de personnes ?"
          />
        </div>
        <button type="submit">Soumettre</button>
      </form>
    </div>
  );
};

export default VoyageVacanceForm;
