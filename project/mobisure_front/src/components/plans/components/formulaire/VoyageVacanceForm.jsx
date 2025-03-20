import React, { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import '../../style/form.css';

const VoyageVacanceForm = ({formData,setFormData,isModalVisible, setModalVisible}) => {

	const navigate = useNavigate();
	
	useEffect(() => {
	      setFormData((prevData) => ({
	        ...prevData,      
			paysdepart: "",
			paysArrive: "",
			dateDepart: "",
			dateArrive: "",
			nbPersonnes: 0
	      }));
		},[]);
	
	
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
      e.preventDefault();
      // Redirige vers la page de récap en passant formData
      navigate("/devis", { state: { formData } });
    };

  console.log(formData);
  
  return (
    <div className="form-container">
      <h3>Formulaire pour Voyage Vacance</h3>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="companyName">Pays de depart</label>
          <input
            type="text"
            id="paysDepart"
            name="paysdepart"
            value={formData.paysdepart}
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
            name="paysArrive"
            value={formData.paysArrive}
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
            name="dateDepart"
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
            name="dateArrive"
            value={formData.dateArrive}
            onChange={handleInputChange}
            required
          />
        </div>
		<div className="form-group">
		  <label htmlFor="nbPersonnes">Nombre de personnes</label>
		  <select
		    id="nbPersonnes"
		    name="nbPersonnes"
		    value={formData.nbPersonnes}
		    onChange={handleInputChange}
		    required
		  >
		    <option value="">Sélectionner</option>
		    <option value="une personne">Une personne</option>
		    <option value="deux à trois">Deux à trois</option>
		    <option value="quatres à six">Quatre à six</option>
		    <option value="plus de six">Plus de six</option>
		  </select>
		</div>

        <button type="submit">Soumettre</button>
      </form>
    </div>
  );
};

export default VoyageVacanceForm;
