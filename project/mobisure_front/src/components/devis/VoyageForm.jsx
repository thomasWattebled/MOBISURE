import { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

export default function VoyageForm() {
  const [voyage, setVoyage] = useState({
    marqueVehicule: "",
    motorisation: "",
    dureeSemaines: "",
    typeVoyage: "",
    distanceKm: "",
    emissionCO2: "",
    nombreVoyageurs: "",
  });

  const handleChange = (e) => {
    const { name, value, type } = e.target;
    const newValue = type === "number" ? (value.includes(".") ? parseFloat(value) : parseInt(value, 10)) : value;
    setVoyage({ ...voyage, [name]: newValue });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8083/devis/calculer", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          ...voyage,
          motorisation: voyage.motorisation === "ELECTRIQUE" ? "ELECTRIQUE" : "THERMIQUE",
        }),
      });

      if (!response.ok) {
        throw new Error("Erreur lors de la soumission du formulaire");
      }

      const data = await response.json();
      const roundedCoutBase = parseFloat(data.coutBase).toFixed(2);
      alert(`Devis calculé : ${roundedCoutBase} €`);
    } catch (error) {
      console.error("Erreur :", error);
      alert("Erreur lors de la soumission du formulaire");
    }
  };

  return (
    <div className="container d-flex justify-content-center align-items-center" style={{ minHeight: "100vh" }}>
      <div className="card shadow-lg p-4" style={{ maxWidth: "600px", width: "100%", backgroundColor: "#f8f9fa" }}>
        <h2 className="text-center text-primary mb-4">Formulaire de Voyage</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Marque du véhicule :</label>
            <select name="marqueVehicule" onChange={handleChange} className="form-select">
              <option value="">Sélectionner</option>
              <option value="BMW">BMW</option>
              <option value="MERCEDES">Mercedes</option>
              <option value="FORD">Ford</option>
              <option value="AUDI">Audi</option>
              <option value="VOLKSWAGEN">Volkswagen</option>
              <option value="TOYOTA">Toyota</option>
              <option value="NISSAN">Nissan</option>
              <option value="HONDA">Honda</option>
            </select>
          </div>

          <div className="mb-3">
            <label className="form-label">Motorisation :</label>
            <select name="motorisation" onChange={handleChange} className="form-select">
              <option value="">Sélectionner</option>
              <option value="ELECTRIQUE">Électrique</option>
              <option value="THERMIQUE">Thermique</option>
            </select>
          </div>

          <div className="mb-3">
            <label className="form-label">Durée en semaines :</label>
            <input type="number" name="dureeSemaines" onChange={handleChange} className="form-control" />
          </div>

          <div className="mb-3">
            <label className="form-label">Type de voyage :</label>
            <select name="typeVoyage" onChange={handleChange} className="form-select">
              <option value="">Sélectionner</option>
              <option value="VACANCES">Vacances</option>
              <option value="PROFESSIONNEL">Professionnel</option>
            </select>
          </div>

          <div className="mb-3">
            <label className="form-label">Distance en km :</label>
            <input type="number" name="distanceKm" onChange={handleChange} className="form-control" />
          </div>

          <div className="mb-3">
            <label className="form-label">Émission CO2 :</label>
            <input type="number" step="0.1" name="emissionCO2" onChange={handleChange} className="form-control" />
          </div>

          <div className="mb-3">
            <label className="form-label">Nombre de voyageurs :</label>
            <input type="number" name="nombreVoyageurs" onChange={handleChange} className="form-control" />
          </div>

          <button type="submit" className="btn btn-primary w-100">Envoyer</button>
        </form>
      </div>
    </div>
  );
}
