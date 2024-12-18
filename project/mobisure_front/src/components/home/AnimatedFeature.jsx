import React from 'react';
import '../../assets/css/AnimatedFeature.css';
import Gif1 from '../../assets/gif/giphy1.gif';
import Gif2 from '../../assets/gif/giphy2.gif';
import Gif3 from '../../assets/gif/giphy3.gif';
import Gif4 from '../../assets/gif/giphy4.gif';

const AnimatedFeature = () => {
  const features = [
    { gif: Gif1, title: "Voyagez à vélo", description: "Des solutions adaptées pour vos trajets écologiques." },
    { gif: Gif2, title: "Conduite sereine", description: "Une couverture complète pour vos déplacements en voiture." },
    { gif: Gif3, title: "Prêt pour l’aventure", description: "Assurez vos biens lors de vos escapades." },
    { gif: Gif4, title: "Bagages protégés", description: "Une assistance garantie pour vos bagages perdus ou endommagés." },
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
