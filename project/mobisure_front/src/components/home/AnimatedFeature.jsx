import React from 'react';
import '../../assets/css/AnimatedFeature.css';
import Avion from '../../assets/gif/avion.gif';
import Velo from '../../assets/gif/velo.gif';
import Valise from '../../assets/gif/valise.gif';
import Voiture from '../../assets/gif/voiture.gif';

const AnimatedFeature = () => {
  const features = [
    { gif: Velo, title: "Voyagez à vélo", description: "Des solutions adaptées pour vos trajets écologiques." },
    { gif: Voiture, title: "Conduite sereine", description: "Une couverture complète pour vos déplacements en voiture." },
    { gif: Avion, title: "Prêt pour l’aventure", description: "Assurez vos biens lors de vos escapades." },
    { gif: Valise, title: "Bagages protégés", description: "Une assistance garantie pour vos bagages perdus ou endommagés." },
  ];

  return (
    <section className="animated-features-section py-5">
      <div className="container">
        <h3 className="text-center text-primary mb-4">Découvrez nos fonctionnalités</h3>
        <div className="row">
          {features.map((feature, index) => (
            <div key={index} className="col-md-3 text-center mb-4">
              <img src={feature.gif} alt={feature.title} className="img-fluid gif-feature" />
              <h5 className="mt-3">{feature.title}</h5>
              <p>{feature.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default AnimatedFeature;
