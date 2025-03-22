import React, { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import '../../style/form.css';

const VoyageProfessionnelForm = ({formData,setFormData,isModalVisible, setModalVisible}) => {
	
	const navigate = useNavigate();
	const [selectedOptions, setSelectedOptions] = useState(new Set());
	
	useEffect(() => {
		      setFormData((prevData) => ({
		        ...prevData,      
				entreprise: "",
				paysArrive: "",
				dateDepart: "",
				dateArrive: "",
				options: Array
		      }));
			},[]);
	
			const optionsDisponibles = [
						    "Perte de documents",
						    "Matériel pro couvert",
							"Assistance juridique à l’étranger"
						  ];
						  
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
        // Redirige vers la page de récap en passant formData
        navigate("/devis", { state: { formData } });
      };

  return (
    <div className="form-container">
      <h3>Formulaire pour Voyage Professionnel</h3>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="entreprise">Nom de l'entreprise :</label>
          <input
            type="text"
            id="entreprise"
            name="entreprise"
            value={formData.entreprise}
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

export default VoyageProfessionnelForm;
