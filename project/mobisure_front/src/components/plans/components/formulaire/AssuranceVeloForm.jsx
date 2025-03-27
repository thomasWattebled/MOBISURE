import React, { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../../style/form.css";
import { Tooltip, Toast, Popover } from 'bootstrap';

const AssuranceVeloForm = ({userData,setUserData,isModalVisible, setModalVisible}) =>  {
      const [selectedOptions, setSelectedOptions] = useState(new Set());
	  const navigate = useNavigate();
	  
	  document.querySelectorAll('[data-bs-toggle="tooltip"]').forEach((tooltipNode) => {
	  	  new Tooltip(tooltipNode);
	  	});
	  
	
	 const [formData, setFormData] = useState({
		motorisation: "THERMIQUE",
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
		"Protection contre le vandalisme",
		"Assistance crevaison",
	];
	
			const handleSubmit = (e) => {
			      e.preventDefault();
			      // Redirige vers la page de récap en passant formData
			      navigate("/devis", { state: { formData } });
			    };
			
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

    
    return (
        <div className="form-container">
          <h2>Formulaire Assurance Vélo</h2>
          <form onSubmit={handleSubmit}>
		  <div className="form-group">
		  	          <label>
		  	            <p>Électrique ?</p>
		  	          </label>
		  	          <div className="radio-group">
		  	            <input
		  	              type="radio"
		  	              id="oui"
		  	              name="motorisation"
		  	              value="ELECTRIQUE"
		  	              checked={formData.motorisation === "ELECTRIQUE"}
		  	              onChange={handleChange}
		  	            />
		  	            <label htmlFor="oui">Oui</label>
		  	            <input
		  	              type="radio"
		  	              id="non"
		  	              name="motorisation"
		  	              value="THERMIQUE"
		  	              checked={formData.motorisation === "THERMIQUE"}
		  	              onChange={handleChange}
		  	            />
		  	            <label htmlFor="non">Non</label>
		  	          </div>
            </div>
			<div className="form-group">
					   <label>Options supplémentaires :</label>
					   <div className="checkbox-group">
					     {optionsDisponibles.map((option) => {
					       let prixOption = "pas de prix affiché";

					       switch(option) {
					         case"Protection contre le vandalisme":
					           prixOption = "8€ par mois";
					           break;
					         case "Assistance crevaison":
					           prixOption = "5€ par mois";
					           break;
					         default:
					           prixOption = "pas de prix affiché";
					       }

					       return (
					         <div key={option}>
					           <input
					             type="checkbox"
					             id={option}
					             name="options"
					             value={option}
					             checked={selectedOptions.has(option)}
					             onChange={() => handleOptionChange(option)}
					           />
					           <label
					             htmlFor={option}
					             data-bs-toggle="tooltip"
					             title={`Prix de l'option : ${prixOption}`}
					           >
					             {option}
					           </label>
					         </div>
					       );
					     })}
					   </div>
					 </div>
            
            <button type="submit">Soumettre</button>
          </form>
        </div>
        
    
      );
    
};
export default AssuranceVeloForm;
    
    
    


