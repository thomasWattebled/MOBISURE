// AssuranceVoyage.js
import React from 'react';
import voyageImg from '../images/assurance_voyage.jpg'; // Remplacez par votre chemin d'image

const AssuranceVoyage = () => {
  return (
    <div className="plan-card">
      <button className="plan-button">
        <img
          src={voyageImg}
          alt="Assurance Voyage"
          className="plan-image"
        />
        <div className="plan-description">
          <h3>Assurance Voyage</h3>
          <p>
            Voyagez sereinement avec nos solutions complètes :
            <ul>
              <li>Assistance médicale et rapatriement.</li>
              <li>Couverture des bagages en cas de perte ou vol.</li>
              <li>Annulation de voyage avec remboursement.</li>
              <li>Protection contre les retards et interruptions.</li>
            </ul>
          </p>
        </div>
      </button>
    </div>
  );
};

export default AssuranceVoyage;
