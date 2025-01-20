import React from 'react';

const SuccessModal = ({ show, onClose }) => {
  const handleSignIn = () => {
    window.location.href = '/login'; 
  };

  return (
    <div
      className={`modal fade ${show ? 'show d-block' : ''}`}
      tabIndex="-1"
      style={{ display: show ? 'block' : 'none', backgroundColor: 'rgba(0,0,0,0.5)' }}
      aria-labelledby="successModalLabel"
      aria-hidden={!show}
    >
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="successModalLabel">
              Succès !
            </h5>
            <button
              type="button"
              className="btn-close"
              onClick={onClose}
              aria-label="Close"
            ></button>
          </div>
          <div className="modal-body">
            Votre compte a été créé avec succès !
          </div>
          <div className="modal-footer">
            <button className="btn btn-secondary" onClick={onClose}>
              Fermer
            </button>
            <button className="btn btn-primary" onClick={handleSignIn}>
              Se connecter
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SuccessModal;
