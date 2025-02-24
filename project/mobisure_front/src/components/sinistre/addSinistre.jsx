import React, { useState } from "react";
import PopupConfirmation from "./popUpConfirmation";

const AddSinistre = () => {
  const [showModal, setShowModal] = useState(false);
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [formData, setFormData] = useState({
    type: "",
    date: "",
    description: "",
    photo:""
  });

  // Gestion des changements dans les champs du formulaire
  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "photo") {
      setFormData({
        ...formData,
        [name]: files[0], // Stocker le fichier image
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

  // Configuration pour le PDF
  const pdfConfig = [
    { label: "Type de sinistre", key: "type" },
    { label: "Date du sinistre", key: "date" },
    { label: "Description", key: "description" },
    { label: "Photo", key: "photo", type: "image" }, 

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
                  <div className="mb-3">
                    <label className="form-label">Type de sinistre</label>
                    <input
                      type="text"
                      className="form-control"
                      name="type"
                      value={formData.type}
                      onChange={handleChange}
                      placeholder="Ex: Accident, Vol, Incendie..."
                      required
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
                  <div className="mb-3">
                    <label className="form-label">Photo (optionnel)</label>
                    <input
                      type="file"
                      className="form-control"
                      name="photo"
                      accept="image/*"
                      onChange={handleChange}
                    />
                  </div>
                  <button type="submit" className="btn btn-success">
                    Soumettre
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Popup de confirmation */}
      {showConfirmationModal && (
        <PopupConfirmation
          onClose={() => setShowConfirmationModal(false)}
          formData={formData}
          pdfConfig={pdfConfig}
        />
      )}


      {(showModal || showConfirmationModal) && (
        <div
          className="modal-backdrop show"
          onClick={() => {
            setShowModal(false);
            setShowConfirmationModal(false);
          }}
        ></div>
      )}
    </div>
  );
};

export default AddSinistre;