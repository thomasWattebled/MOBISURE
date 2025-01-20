// AssuranceVehicule.js
import React from 'react';
import vehiculeImg from '../images/assurance_vehicule.jpg'; // Remplacez par votre chemin d'image

const AssuranceVehicule = () => {
  return (
    <div className="plan-card">
      <button className="plan-button">
        <img
          src={vehiculeImg}
          alt="Assurance Véhicule"
          className="plan-image"
        />
        <div className="plan-description">
          <h3>Assurance Véhicule</h3>
          <p>
            Protégez votre véhicule avec des offres personnalisées :
            <ul>
              <li>Responsabilité civile obligatoire.</li>
              <li>Assurance tous risques pour une couverture maximale.</li>
              <li>Garantie conducteur en cas d'accident.</li>
              <li>Options pour bris de glace, vol et incendie.</li>
            </ul>
          </p>
        </div>
      </button>
    </div>
  );
};

export default AssuranceVehicule;
