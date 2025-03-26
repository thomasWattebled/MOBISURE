import React, { useState, useEffect } from "react";
import PopupConfirmation from "./popUpConfirmation";
import { useAuth } from "../auth/AuthContext";
import sinistreService from "../../services/sinistreService";
import UserService from '../../services/userService';

const AddSinistre = (props) => {
  const { getUser } = useAuth();
  const userDetails = getUser();
  const [user, setUser] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [formData, setFormData] = useState({
    // Informations générales
    categorieSinistre: "", // Ajout du champ catégorie
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

  useEffect(() => {
    UserService.fetchUserByEmail(userDetails.unsername).then(data => {
      setUser(data);
      console.log(data);
    });
  }, [userDetails.unsername]);

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
    if (formData.categorieSinistre === "Véhicule") {
      sinistreService.postCarSinitre({
        "contractId" : formData.numeroContrat,
        "userId" : user.id,
        "category" : formData.categorieSinistre,
        "date" : formData.date,
        "brand" : formData.marque,
        "immatriculation" : formData.immatriculation,
        "modele" : formData.modele,
        "description" : formData.description,
        "responsable" : formData.responsable,
      }).then(response => {
        if (!response.ok) {
          throw new Error('Erreur de récupération des données');
        }
        else {
          response.json().then(data => {
            if (typeof formData.constat !== "string") sinistreService.postSinistreFile(formData.constat, data);
          });
        }
      }).then(response => props.refreshData());
    } else if (formData.categorieSinistre === "Santé") {
      sinistreService.postHealthSinitre({
        "contractId" : formData.numeroContrat,
        "userId" : user.id,
        "category" : formData.categorieSinistre,
        "date" : formData.date,
        "description" : formData.description,
        "nature" : formData.natureBlessure,
        "hospital" : formData.hopital,
      }).then(response => {
        if (!response.ok) {
          throw new Error('Erreur de récupération des données');
        }
        else {
          response.json().then(data => {
            if (typeof formData.ordonnance !== "string") sinistreService.postSinistreFile(formData.ordonnance, data);
          });
        }
      }).then(response => props.refreshData());
    }
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
                   <label htmlFor="categorieSinistre" className="form-label">
                      Catégorie de sinistre
                  </label>
                  <select
                      id="categorieSinistre"
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

                  {/* Informations spécifiques selon la catégorie */}
                  {formData.categorieSinistre === "Véhicule" && (
                    <>
                      <h5>Informations du véhicule</h5>
                      <div className="mb-3">
                      <label htmlFor="immatriculation" className="form-label"> Immatriculation </label>
                      <input
                        id="immatriculation"
                        type="text"
                        className="form-control"
                        name="immatriculation"
                        value={formData.immatriculation}
                        onChange={handleChange}
                        required
                      />
                      </div>
                      <div className="mb-3">
                        <label  htmlFor="marque" className="form-label">Marque</label>
                        <input
                          id="marque"
                          type="text"
                          className="form-control"
                          name="marque"
                          value={formData.marque}
                          onChange={handleChange}
                          required
                        />
                      </div>
                      <div className="mb-3">
                        <label htmlFor="modele" className="form-label">Modele</label>
                        <input
                          id="modele"
                          type="text"
                          className="form-control"
                          name="modele"
                          value={formData.modele}
                          onChange={handleChange}
                          required
                        />
                      </div>
                  <div className="mb-3">
                    <label htmlFor="responsable" className="form-label">Responsable</label>
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
                        <label htmlFor="constat" className="form-label">Constat (photo/PDF)</label>
                        <input
                        id="constat"
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
                        <label htmlFor="nature_blessure" className="form-label">Nature de la blessure</label>
                        <input
                          id="nature_blessure"
                          type="text"
                          className="form-control"
                          name="natureBlessure"
                          value={formData.natureBlessure}
                          onChange={handleChange}
                          required
                        />
                      </div>
                      <div className="mb-3">
                        <label htmlFor="hopital" className="form-label">Hôpital</label>
                        <input
                        id="hopital"
                          type="text"
                          className="form-control"
                          name="hopital"
                          value={formData.hopital}
                          onChange={handleChange}
                          required
                        />
                      </div>
                      <div className="mb-3">
                        <label htmlFor="ordonnance" className="form-label">Ordonnance (photo/PDF)</label>
                        <input
                        id="ordonnance"
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