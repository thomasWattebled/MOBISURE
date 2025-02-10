import React, { useState } from "react";

const AddSinistre = () => {

const [showModal, setShowModal] = useState(false);
return (
<div className="container mt-5">
      {/* Bouton pour ouvrir la popup */}
      <button className="btn btn-primary" onClick={() => setShowModal(true)}>
        +
      </button>

      {/* Popup Bootstrap */}
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
                <form>
                  <div className="mb-3">
                    <label className="form-label">Type de sinistre</label>
                    <input type="text" className="form-control" placeholder="Ex: Accident, Vol, Incendie..." />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Date du sinistre</label>
                    <input type="date" className="form-control" />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Description</label>
                    <textarea className="form-control" rows="3" placeholder="Décrivez votre sinistre..."></textarea>
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

      {/* Fond semi-transparent pour fermer le modal */}
      {showModal && <div className="modal-backdrop show" onClick={() => setShowModal(false)}></div>}
    </div>
  );
};

export default AddSinistre;