import React, { useState } from "react";
import PopupConfirmation from "./popUpConfirmation";
import { useAuth } from "../auth/AuthContext";

const AddSinistre = () => {
  const { getUser } = useAuth();
  const userDetails = getUser();

  const [showModal, setShowModal] = useState(false);
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [formData, setFormData] = useState({
    // Informations générales
    categorieSinistre: "", // Ajout du champ catégorie
    type: "",
    date: "",
    description: "",
    photo: "",

    // Informations sur l’assuré
    nom: userDetails.nom || "",
    prenom: userDetails.prenom || "",
    email: userDetails.email || "",
    numeroContrat: "",

    // Spécifique au sinistre véhicule
    immatriculation: "",
    marque: "",
    modele: "",
    responsable: "",
    constat: "",

    // Spécifique au sinistre santé
    natureBlessure: "",
    hopital: "",
    medecin: "",
    ordonnance: "",
  });

  // Gestion des changements dans les champs du formulaire
  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "photo" || name === "constat" || name === "ordonnance") {
      setFormData({
        ...formData,
        [name]: files[0], // Stocker les fichiers image/PDF
      });
    } else {
      setFormData({
        ...formData,
        [name]: value,
      });
    }
  };

  // Soumission du formulaire
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Formulaire soumis:", formData);
    setShowConfirmationModal(true);
    setShowModal(false);
  };

  // Configuration pour le PDF selon la catégorie sélectionnée
  const pdfConfig = [
    { label: "Numéro de contrat", key: "numeroContrat" },
    { label: "Date du sinistre", key: "date" },
    { label: "Description", key: "description" },
    { label: "Photo", key: "photo", type: "image" },

    ...(formData.categorieSinistre === "Véhicule"
      ? [
          { label: "Immatriculation", key: "immatriculation" },
          { label: "Marque", key: "marque" },
          { label: "Modèle", key: "modele" },
          { label: "Responsable", key: "responsable" },
          { label: "Constat", key: "constat", type: "image" },
        ]
      : formData.categorieSinistre === "Santé"
      ? [
          { label: "Nature de la blessure", key: "natureBlessure" },
          { label: "Hôpital", key: "hopital" },
          { label: "Médecin", key: "medecin" },
          { label: "Ordonnance", key: "ordonnance", type: "image" },
        ]
      : []),
  ];

  return (
    <div className="container mt-5">
      <button className="btn btn-primary" onClick={() => setShowModal(true)}>
        +
      </button>

      {showModal && (
        <div className="modal show d-block" tabIndex="-1">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Déclarer un sinistre</h5>
                <button
                  type="button"
                  className="btn-close"
                  onClick={() => setShowModal(false)}
                ></button>
              </div>
              <div className="modal-body">
                <form onSubmit={handleSubmit}>
                  {/* Sélection du type de sinistre */}
                  <h5>Informations générales</h5>
                  <div className="mb-3">
                    <label className="form-label">Catégorie de sinistre</label>
                    <select
                      className="form-control"
                      name="categorieSinistre"
                      value={formData.categorieSinistre}
                      onChange={handleChange}
                      required
                    >
                      <option value="">Sélectionner...</option>
                      <option value="Véhicule">Véhicule</option>
                      <option value="Santé">Santé</option>
                    </select>
                  </div>

                  {/* Informations de l’assuré */}
                  <h5>Informations de l'assuré</h5>
                  <div className="mb-3">
                    <label className="form-label">Numéro de contrat</label>
                    <input
                      type="text"
                      className="form-control"
                      name="numeroContrat"
                      value={formData.numeroContrat}
                      onChange={handleChange}
                      required
                      placeholder="Ex: 123456789"
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Date du sinistre</label>
                    <input
                      type="date"
                      className="form-control"
                      name="date"
                      value={formData.date}
                      onChange={handleChange}
                      required
                    />
                    </div>

                  {/* Informations spécifiques selon la catégorie */}
                  {formData.categorieSinistre === "Véhicule" && (
                    <>
                      <h5>Informations du véhicule</h5>
                      <div className="mb-3">
                        <label className="form-label">Immatriculation</label>
                        <input
                          type="text"
                          className="form-control"
                          name="immatriculation"
                          value={formData.immatriculation}
                          onChange={handleChange}
                          required
                        />
                      </div>
                      <div className="mb-3">
                        <label className="form-label">Marque</label>
                        <input
                          type="text"
                          className="form-control"
                          name="marque"
                          value={formData.marque}
                          onChange={handleChange}
                          required
                        />
                      </div>
                      <div className="mb-3">
                        <label className="form-label">Modele</label>
                        <input
                          type="text"
                          className="form-control"
                          name="modele"
                          value={formData.modele}
                          onChange={handleChange}
                          required
                        />
                      </div>
                      <div className="mb-3">
                    <label className="form-label">Description</label>
                    <textarea
                      type="text"
                      className="form-control"
                      rows="3"
                      name="description"
                      value={formData.description}
                      onChange={handleChange}
                      placeholder="Décrivez votre sinistre..."
                      required
                    ></textarea>
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Responsable</label>
                    <textarea
                      type="text"
                      className="form-control"
                      rows="3"
                      name="responsable"
                      value={formData.responsable}
                      onChange={handleChange}
                      placeholder="vous ou l'autre usager"
                      required
                    ></textarea>
                  </div>
                      <div className="mb-3">
                        <label className="form-label">Constat (photo/PDF)</label>
                        <input
                          type="file"
                          className="form-control"
                          name="constat"
                          accept="image/*, application/pdf"
                          onChange={handleChange}
                        />
                      </div>
                    </>
                  )}

                  {formData.categorieSinistre === "Santé" && (
                    <>
                      <h5>Informations médicales</h5>
                      <div className="mb-3">
                        <label className="form-label">Nature de la blessure</label>
                        <input
                          type="text"
                          className="form-control"
                          name="natureBlessure"
                          value={formData.natureBlessure}
                          onChange={handleChange}
                          required
                        />
                      </div>
                      <div className="mb-3">
                        <label className="form-label">Hôpital</label>
                        <input
                          type="text"
                          className="form-control"
                          name="hopital"
                          value={formData.hopital}
                          onChange={handleChange}
                          required
                        />
                      </div>
                      <div className="mb-3">
                        <label className="form-label">Ordonnance (photo/PDF)</label>
                        <input
                          type="file"
                          className="form-control"
                          name="ordonnance"
                          accept="image/*, application/pdf"
                          onChange={handleChange}
                        />
                      </div>
                    </>
                  )}

                  <button type="submit" className="btn btn-success">
                    Soumettre
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      )}

      {showConfirmationModal && (
        <PopupConfirmation
          onClose={() => setShowConfirmationModal(false)}
          formData={formData}
          pdfConfig={pdfConfig}
        />
      )}
    </div>
  );
};

export default AddSinistre;
