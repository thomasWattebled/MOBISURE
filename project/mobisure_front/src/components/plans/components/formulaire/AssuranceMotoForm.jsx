import React, { useState } from "react";
import "../../style/form.css";

const AssuranceMotoForm = () => {
  const [selectedMarque, setSelectedMarque] = useState("");
  const [models, setModels] = useState([]);
  const [selectedOption, setSelectedOption] = useState("");
  
  const marques = [
    "Yamaha",
    "Honda",
    "Kawasaki",
    "Suzuki",
    "Ducati",
    "Harley-Davidson",
    "BMW",
    "Triumph",
    "KTM",
    "Indian",
  ];

  // Liste des modèles associés à chaque marque
  const modelsByMarque = {
    Yamaha: ["MT-07", "MT-09", "R1", "R6", "FZ-09"],
    Honda: ["CBR600RR", "CB1000R", "Africa Twin", "CB500F", "Rebel 500"],
    Kawasaki: ["Ninja 650", "Z900", "KLR650", "Versys 650", "Vulcan S"],
    Suzuki: ["GSX-R600", "V-Strom 650", "SV650", "Hayabusa", "Bandit 1200"],
    Ducati: ["Monster 1200", "Panigale V2", "Scrambler", "Multistrada 950", "Diavel"],
    "Harley-Davidson": ["Sportster", "Softail", "Road King", "Street Glide", "Iron 883"],
    BMW: ["S1000RR", "R1250GS", "F850GS", "R18", "K1600GTL"],
    Triumph: ["Street Triple", "Bonneville", "Tiger 800", "Rocket 3", "Speed Triple"],
    KTM: ["Duke 390", "RC 390", "Adventure 790", "Duke 890", "Super Duke 1290"],
    Indian: ["Scout", "Chieftain", "Roadmaster", "FTR 1200", "Chief Dark Horse"],
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
      <h2>Formulaire Assurance Moto</h2>
      <form >
        <div className="form-group">
          <label>
            Marque de moto :
            <select
              name="MarqueMoto"
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
            Modèle de moto :
            <select name="motoModel" required disabled={!selectedMarque}>
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
      <span className="radio-group">
      <label for="oui">Oui</label>
      <input type="radio" id="oui" name="oui" value="oui" checked={selectedOption === "oui"}
            onChange={(e) => setSelectedOption(e.target.value)}/>
      <label for="non">Non</label>
      <input type="radio" id="non" name="non" value="non" checked={selectedOption === "non"}
            onChange={(e) => setSelectedOption(e.target.value)}/>
          </span>
        </div>
        <div className="form-group">
          <label>
            Année de fabrication :
            <input type="number" name="manufactureYear" required />
          </label>
        </div>
        <div className="form-group">
          <label>
            Utilisation de la moto :
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

export default AssuranceMotoForm;
