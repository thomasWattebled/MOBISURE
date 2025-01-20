import React, { useState } from "react";
import '../../style/form.css';

const AssuranceVehiculeForm = () => {
  const [selectedBrand, setSelectedBrand] = useState("");

  const brands = [
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
    // Ajoutez d'autres marques ici
  ];

  return (
    <div className="form-container">
      <h2>Formulaire Assurance Véhicule</h2>
      <form>
      <div className="form-group">
        <label>
          Marque de véhicule :
          <select 
            name="vehicleBrand" 
            value={selectedBrand} 
            onChange={(e) => setSelectedBrand(e.target.value)} 
            required
          >
            <option value="">Sélectionnez une marque</option>
            {brands.map((brand) => (
              <option key={brand} value={brand}>
                {brand}
              </option>
            ))}
          </select>
        </label>
        </div>
        <div className="form-group">
        <label>
          Modèle de véhicule :
          <input type="text" name="vehicleModel" required />
        </label>
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
        <button type="submit">Soumettre</button>
      </form>
    </div>
  );
};

export default AssuranceVehiculeForm;