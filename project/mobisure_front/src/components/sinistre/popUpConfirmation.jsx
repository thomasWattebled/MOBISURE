import React from "react";
import GeneratePDF from "../pdf/generatePDF";

const PopupConfirmation = ({ onClose, formData, pdfConfig }) => {
  return (
    <div className="modal show d-block" tabIndex="-1">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">Confirmation</h5>
            <button
              type="button"
              className="btn-close"
              onClick={onClose}
            ></button>
          </div>
          <div className="modal-body">
            <p>Le sinistre a bien été créé.</p>
            <GeneratePDF
              data={formData}
              config={pdfConfig}
              fileName="recapitulatif_sinistre.pdf"
            />
          </div>
          <div className="modal-footer">
            <button
              type="button"
              className="btn btn-secondary"
              onClick={onClose}
            >
              Fermer
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PopupConfirmation;