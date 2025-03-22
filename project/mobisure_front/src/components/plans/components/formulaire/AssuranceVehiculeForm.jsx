import React, { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../../style/form.css";
import SuccessDemande from '../../../assistance/SuccesDemande'

const AssuranceVehiculeForm = ({formData,setFormData,isModalVisible, setModalVisible}) =>  {

    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState(null);
  const [selectedMarque, setSelectedMarque] = useState("");
  const [models, setModels] = useState([]);
  const [selectedOptions, setSelectedOptions] = useState(new Set());
  const navigate = useNavigate();
  
  useEffect(() => {
      setFormData((prevData) => ({
        ...prevData,      
        marque: "",
        modele: "",
		motorisation: "THERMIQUE",
		utilisation: "",
		duree: 0,
		fabrication: 0,
		plaque: "",
		options: Array
      }));
	},[]);

  const marques = [
    "Audi",
    "BMW",
    "Mercedes-Benz",
    "Volkswagen",
    "Toyota",
    "Ford",
    "Honda",
    "Nissan",
    "Renault",
    "Peugeot",
  ];
  
  const optionsDisponibles = [
    "Assistance zéro km",
    "Véhicule de remplacement",
    "Bris de glace",
  ];


  const handleModalClose = () => {
    setModalVisible(false); // Fermer le modal
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




  // Liste des modèles associés à chaque marque
  const modelsByMarque = {
    Audi: ["A3", "A4", "A6", "Q5", "Q7"],
    BMW: ["Serie 3", "Serie 5", "X1", "X3", "X5"],
    "Mercedes-Benz": ["Classe A", "Classe C", "Classe E", "GLC", "GLE"],
    Volkswagen: ["Golf", "Polo", "Passat", "Tiguan", "Touareg"],
    Toyota: ["Corolla", "Yaris", "RAV4", "Hilux", "Camry"],
    Ford: ["Fiesta", "Focus", "Mustang", "Explorer", "Ranger"],
    Honda: ["Civic", "Accord", "CR-V", "Fit", "Pilot"],
    Nissan: ["Micra", "Qashqai", "X-Trail", "Navara", "Juke"],
    Renault: ["Clio", "Mégane", "Captur", "Kadjar", "Twingo"],
    Peugeot: ["208", "308", "508", "3008", "5008"],
  };

  const durations = ["1 semaine", "2 semaines", "1 mois", "6 mois", "1 an"];

  const handleMarqueChange = (e) => {
    const marque = e.target.value;
    setSelectedMarque(marque);
	setFormData((prevData) => ({
	    ...prevData,
	    marque: marque
	  }));
    setModels(modelsByMarque[marque] || []);
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    // Redirige vers la page de récap en passant formData
    navigate("/devis", { state: { formData } });
  };
	
  return (
    <div className="form-container">
      <h2>Formulaire Assurance Véhicule</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>
            Marque de véhicule :
            <select
              name="marqueVehicule"
              value={formData.marque || ""}
              onChange={handleMarqueChange}
              required
            >
              <option >Sélectionnez une marque</option>
              {marques.map((marque) => (
                <option key={marque} value={marque}>
                  {marque}
                </option>
              ))}
            </select>
          </label>
        </div>
        <div className="form-group">
          <label>
            Modèle de véhicule :
            <select name="modele" value={formData.modele || ""}  required disabled={!selectedMarque} onChange={handleChange}>
              <option value="">Sélectionnez un modèle</option>
              {models.map((modele) => (
                <option key={modele} value={modele}>
                  {modele}
                </option>
              ))}
            </select>
          </label>
        </div>
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
          <label>
            Année de fabrication :
            <input type="number" name="fabrication"
              value={formData.fabrication || ""} onChange={handleChange} required/>
          </label>
        </div>
        <div className="form-group">
          <label>
            Utilisation du véhicule :
            <select name="utilisation" value={formData.utilisation || ""}
              onChange={handleChange} required >
              <option value="">Sélectionnez l'utilisation</option>
              <option value="PERSONNEL">Personnel</option>
              <option value="PROFESSIONNELLE">Professionnel</option>
            </select>
          </label>
        </div>
        <div className="form-group">
          <label> Durée :
            <select name="duree" value={formData.duree || ""}
              onChange={handleChange} required >
              <option value="">Sélectionnez une durée</option>
              {durations.map((duration) => (
                <option key={duration} value={duration}>
                  {duration}
                </option>
              ))}
            </select>
          </label>
        </div>
		<div className="form-group">
			<label>
		    	Plaque d'immatriculation :
		        <input type="text" name="plaque"
		           value={formData.plaque || ""} onChange={handleChange} required/>
		 	</label>
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
      <SuccessDemande show={isModalVisible} onClose={handleModalClose} />

    </div>
    

  );
};

export default AssuranceVehiculeForm;
