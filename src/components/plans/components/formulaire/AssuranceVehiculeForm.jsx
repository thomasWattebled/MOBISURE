import React, { useState } from "react";
import "../../style/form.css";

const AssuranceVehiculeForm = () =>  {
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
    // Mettre à jour la liste des modèles correspondants
    setModels(modelsByMarque[marque] || []);
  };

  return (
    <div className="form-container">
      <h2>Formulaire Assurance Véhicule</h2>
      <form>
        <div className="form-group">
          <label>
            Marque de véhicule :
            <select
              name="MarqueVehicule"
              value={selectedMarque}
              onChange={handleMarqueChange}
              required
            >
              <option value="">Sélectionnez une marque</option>
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
            <select name="vehicleModel" required disabled={!selectedMarque}>
              <option value="">Sélectionnez un modèle</option>
              {models.map((model) => (
                <option key={model} value={model}>
                  {model}
                </option>
              ))}
            </select>
          </label>
        </div>
        <div className="form-group" >
      <label>
      <p>Electrique ? </p>
      </label>
      <div className="radio-group">
      <input type="radio" id="oui" name="oui" value="oui" checked={selectedOption === "oui"}
            onChange={(e) => setSelectedOption(e.target.value)}/>
      <label for="oui">Oui</label>
      <input type="radio" id="non" name="non" value="non" checked={selectedOption === "non"}
            onChange={(e) => setSelectedOption(e.target.value)}/>
      <label for="non">Non</label>
          </div>
        </div>
        <div className="form-group">
          <label>
            Année de fabrication :
            <input type="number" name="manufactureYear" required />
          </label>
        </div>
        <div className="form-group">
          <label>
            Utilisation du véhicule :
            <select name="usage" required>
              <option value="personnel">Personnel</option>
              <option value="professionnel">Professionnel</option>
            </select>
          </label>
        </div>
        <div className="form-group">
          <label>
            Durée :
            <select name="duration" required>
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
    </div>
    

  );
};

export default AssuranceVehiculeForm;
