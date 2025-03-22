import React from 'react';
import '../../assets/css/Features.css';
import journeyImage from '../../assets/image/Journey.png';

const Features = () => (
  <section className="features-section py-5">
    <div className="container">
      <div className="row align-items-center">
        <div className="col-6">
          <h3 className="text-primary">Pourquoi choisir AssurMob ?</h3>
          <p>
            Découvrez pourquoi des milliers de voyageurs nous font confiance pour assurer leurs déplacements. Avec AssurMob, tout est conçu pour simplifier votre expérience.
          </p>
          <ul>
            <li><strong>Facilité d’utilisation :</strong> Une plateforme intuitive pour obtenir votre assurance en quelques minutes.</li>
            <li><strong>Adaptée à vos besoins :</strong> Des options personnalisables pour chaque type de voyage.</li>
            <li><strong>Assistance 24/7 :</strong> Un support dédié pour vous accompagner à tout moment.</li>
          </ul>
        </div>
        <div className="col-6">
          <img className="img-fluid" src={journeyImage} alt="Pourquoi choisir AssurMob" />
        </div>
      </div>
    </div>
  </section>
);

export default Features;
