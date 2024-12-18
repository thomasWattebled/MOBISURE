import React from 'react';
import '../../assets/css/Testimonial.css';
const Testimonial = () => (
  <section className="testimonial-section py-5">
    <div className="container">
      <h3 className="text-center text-primary mb-4">Ce que disent nos clients</h3>
      <div className="row">
        <div className="col-md-4">
          <div className="testimonial">
            <p>"Une expérience exceptionnelle ! Rapide, fiable et efficace. Merci AssurMob !"</p>
            <strong>- Sarah K.</strong>
          </div>
        </div>
        <div className="col-md-4">
          <div className="testimonial">
            <p>"Le support est incroyable. Toujours disponible et réactif en cas de besoin."</p>
            <strong>- Julien M.</strong>
          </div>
        </div>
        <div className="col-md-4">
          <div className="testimonial">
            <p>"Je recommande à 100%. L’assurance voyage n’a jamais été aussi simple."</p>
            <strong>- Clara B.</strong>
          </div>
        </div>
      </div>
    </div>
  </section>
);

export default Testimonial;
