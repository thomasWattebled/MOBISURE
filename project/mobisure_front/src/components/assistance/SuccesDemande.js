import React from 'react';
import  {useNavigate}  from 'react-router-dom';

const SuccessDemande = ({ show, onClose }) => {
  const navigate = useNavigate();  // Utilisation de useNavigate pour la navigation

  const handleClose = () => {
    onClose();  // Fermer la modal
    navigate('/home');  // Rediriger vers /home
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
              onClick={handleClose}  // Appel de handleClose ici
              aria-label="Close"
            ></button>
          </div>
          <div className="modal-body">
            Votre demande d'assistance a bien été transmise.
          </div>
          <div className="modal-footer">
            <button
              type="button"
              className="btn btn-primary"
              onClick={handleClose}  // Appel de handleClose ici
            >
              Fermer
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SuccessDemande;
