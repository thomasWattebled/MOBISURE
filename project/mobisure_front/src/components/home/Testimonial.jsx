import React from 'react';
import '../../assets/css/Testimonial.css';

const Testimonial = () => (
  <section className="testimonial-section py-5">
    <div className="container">
      <h3 className="text-center text-primary mb-4">Ce que disent nos clients</h3>
      <div className="row">
        {/* Témoignage de Pierre */}
        <div className="col-md-4">
          <div className="testimonial">
            <p>
              "Lors de mon voyage au parc national Lauca, j’ai enfermé mes clés dans ma voiture
              de location. En 2 heures, un garagiste était là pour m’aider. Service exceptionnel !"
            </p>
            <strong>- Pierre D.</strong>
          </div>
        </div>

        {/* Témoignage de Julie */}
        <div className="col-md-4">
          <div className="testimonial">
            <p>
              "À Montréal, mon vol a été retardé et je n’ai pas pu récupérer les clés de mon
              appartement. L’équipe m’a trouvé une chambre d’hôtel et un taxi en un temps
              record."
            </p>
            <strong>- Julie R.</strong>
          </div>
        </div>

        {/* Témoignage d'Arthur */}
        <div className="col-md-4">
          <div className="testimonial">
            <p>
              "Pendant mes vacances, ma voiture a eu un problème mécanique. Ils m’ont livré
              une voiture de location directement à mon hôtel. Quelle tranquillité d’esprit !"
            </p>
            <strong>- Arthur L.</strong>
          </div>
        </div>
      </div>

      <div className="row mt-4">
        {/* Témoignage de Marius */}
        <div className="col-md-4">
          <div className="testimonial">
            <p>
              "Grâce à leurs rapports et indicateurs, je peux suivre les assistances traitées et
              les habitudes de nos clients. C’est une aide précieuse pour nos décisions."
            </p>
            <strong>- Marius P.</strong>
          </div>
        </div>

        {/* Témoignage de Suzanne */}
        <div className="col-md-4">
          <div className="testimonial">
            <p>
              "Travailler avec l’équipe est un plaisir. J’ai accès aux dossiers des clients, je
              négocie des services optimisés, et je peux suivre mes interventions chaque mois."
            </p>
            <strong>- Suzanne T.</strong>
          </div>
        </div>

        {/* Témoignage d'Hector */}
        <div className="col-md-4">
          <div className="testimonial">
            <p>
              "En tant que médecin-urgentiste, leur soutien est crucial. J’organise des
              rapatriements et reste en contact permanent avec le client et le centre."
            </p>
            <strong>- Dr Hector M.</strong>
          </div>
        </div>
      </div>
    </div>
  </section>
);

export default Testimonial;
