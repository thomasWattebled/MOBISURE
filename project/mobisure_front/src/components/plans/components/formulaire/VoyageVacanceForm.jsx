import React, { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import '../../style/form.css';




const VoyageVacanceForm = ({userData,setUserData,isModalVisible, setModalVisible,onSubmit}) => {


	const navigate = useNavigate();
	const [selectedOptions, setSelectedOptions] = useState(new Set());
	
		
		const [formData, setFormData] = useState({
      paysDepart:"",
      paysArrive: "",
      dateDepart: "",
      dateArrive: "",
      nbPersonnes: "",
        options: []
      });

      useEffect(() => {
        if (userData) {
          setFormData((prevFormData) => ({
            ...prevFormData, 
            ...userData, 
          }));
        }
      }, [userData]);
    
      const optionsDisponibles = [
        "Perte de documents",
        "Matériel pro couvert",
      "Assistance juridique à l’étranger"
      ];
					  
					  const handleChange = (e) => {
					  				    const { name, value } = e.target;
					  				    setFormData((prevData) => ({
					  				      ...prevData,
					  				      [name]: value,
					  				    }));
					  				  };
					  				  
					  				 const handleOptionChange = (option) => {
					  				      setSelectedOptions(prevSet => {
					  				        const newSet = new Set(prevSet);
					  				        if (newSet.has(option)) {
					  				          newSet.delete(option);
					  				        } else {
					  				          newSet.add(option);
					  				        }

					  				        // Mettre à jour formData avec les options sélectionnées
					  				        setFormData((prevData) => ({
					  				          ...prevData,
					  				          options: Array.from(newSet) // Convertir le Set en tableau
					  				        }));

					  				        return newSet;
					  				      });
					  				    };
	
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
    navigate("/devis", { state: { formData } });
    console.log('Form Data Submitted:', formData);
  };


  
  return (
    <div className="form-container">
      <h3>Formulaire pour Voyage Vacance</h3>
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
          <label htmlFor="paysArrive">Destination :</label>
          <input
            type="text"
            id="paysArrive"
            name="paysArrive"
            value={formData.paysArrive}
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
          <label htmlFor="dateArrive">Date Retour :</label>
          <input
            type="date"
            id="dateArrive"
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
		
		<div className="form-group">
							   <label>Options supplémentaires :</label>
							   <div className="checkbox-group">
							     {optionsDisponibles.map((option) => (
							       <div key={option}>
							         <input
							           type="checkbox"
							           id={option}
							           name="options"
							           value={option}
							           checked={selectedOptions.has(option)}
							           onChange={() => handleOptionChange(option)}
							         />
							         <label htmlFor={option}>{option}</label>
							       </div>
							     ))}
							   </div>
							 </div>

        <button type="submit">Soumettre</button>
      </form>
    </div>
  );
};

export default VoyageVacanceForm;
