import React, { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../../style/form.css";

const AssuranceVeloForm = ({formData,setFormData,isModalVisible, setModalVisible}) =>  {
      const [selectedOption, setSelectedOption] = useState("");
	  const navigate = useNavigate();
	  
	  useEffect(() => {
	          setFormData((prevData) => ({
	            ...prevData,      
	    		motorisation: "THERMIQUE",
	          }));
	    	},[]);
	
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
            
            <button type="submit">Soumettre</button>
          </form>
        </div>
        
    
      );
    
};
export default AssuranceVeloForm;
    
    
    


