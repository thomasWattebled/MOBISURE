import React, { useState,useEffect } from "react";
import "../../style/form.css";
import SuccessDemande from '../../../assistance/SuccesDemande'

const AssuranceVehiculeForm = ({formData,setFormData, handleSubmit, isModalVisible, setModalVisible}) =>  {

    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState(null);
  const [selectedMarque, setSelectedMarque] = useState("");
  const [models, setModels] = useState([]);
  const [selectedOption, setSelectedOption] = useState("");
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
    formData.marque=marque
    // Mettre à jour la liste des modèles correspondants
    setModels(modelsByMarque[marque] || []);
  };

  return (
    <div className="form-container">
      <h2>Formulaire Assurance Véhicule</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>
            Marque de véhicule :
            <select
              name="MarqueVehicule"
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
            <select name="vehicleModel" value={formData.model}  required disabled={!selectedMarque}>
              <option value="">Sélectionnez un modèle</option>
              {models.map((model) => (
                <option key={model} value={model}>
                  {model}
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
              name="electrique"
              value="oui"
              checked={formData.electrique === "oui"}
              onChange={handleChange}
            />
            <label htmlFor="oui">Oui</label>
            <input
              type="radio"
              id="non"
              name="electrique"
              value="non"
              checked={formData.electrique === "non"}
              onChange={handleChange}
            />
            <label htmlFor="non">Non</label>
          </div>
        </div>
        <div className="form-group">
          <label>
            Année de fabrication :
            <input type="number" name="annee"
              value={formData.annee || ""} onChange={handleChange} required/>
          </label>
        </div>
        <div className="form-group">
          <label>
            Utilisation du véhicule :
            <select name="utilisation" value={formData.utilisation || ""}
              onChange={handleChange} required >
              <option value="">Sélectionnez l'utilisation</option>
              <option value="personnel">Personnel</option>
              <option value="professionnel">Professionnel</option>
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
        <button type="submit">Soumettre</button>
      </form>
      <SuccessDemande show={isModalVisible} onClose={handleModalClose} />

    </div>
    

  );
};

export default AssuranceVehiculeForm;
