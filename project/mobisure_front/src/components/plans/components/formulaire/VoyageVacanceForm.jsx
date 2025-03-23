import React, { useState } from 'react';
import '../../style/form.css';

const VoyageVacanceForm = ({ onSubmit })  => {
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
    onSubmit(formData);
    console.log('Form Data Submitted:', formData);
  };

  return (
    <div className="form-container">
      <h3>Formulaire pour Voyage Professionnel</h3>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="paysDepart">Pays de depart</label>
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
        <div className="form-group">
          <label htmlFor="nbPersonnes">Nombre de personnes</label>
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
