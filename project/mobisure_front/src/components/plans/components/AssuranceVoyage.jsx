import React, { useState } from 'react';
import voyageImg from '../images/assurance_voyage.jpg'; 
import voyage_vacance from '../images/voyage_vacance.jpg';
import voyage_pro from '../images/voyage_professionel.jpg';

const AssuranceVoyage = () => {
  const [step, setStep] = useState(1); // 1 pour le choix initial, 2 pour les sous-options

  const handleVoyageClick = () => {
    setStep(2); // Passer à l'étape des sous-options
  };

  const handleOptionClick = (option) => {
    alert(`Vous avez choisi : ${option}`);
  };

  return (
    <div className="plan-card">
      {step === 1 ? (
        // Choix initial : Assurance Voyage
        <button className="plan-button" onClick={handleVoyageClick}>
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
      ) : (
        // Étape des sous-options
        <div className="sub-options">
          <h3>Choisissez votre type de voyage :</h3>
          <div className="sub-options-buttons">
            <button
              className="plan-button"
              onClick={() => handleOptionClick('Voyage Vacance')}
            >
              <img
                src={voyage_vacance} 
                alt="Voyage Vacance"
                className="plan-image"
              />
              <div className="plan-description">
                <h3>Voyage Vacance</h3>
                <p>Protection complète pour vos escapades en toute sérénité.</p>
              </div>
            </button>
            <button
              className="plan-button"
              onClick={() => handleOptionClick('Voyage Professionnel')}
            >
              <img
                src={voyage_pro} 
                alt="Voyage Professionnel"
                className="plan-image"
              />
              <div className="plan-description">
                <h3>Voyage Professionnel</h3>
                <p>Assurance adaptée pour vos déplacements professionnels.</p>
              </div>
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default AssuranceVoyage;
