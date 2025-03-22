import React from 'react';
import '../../assets/css/Pricing.css';
const Pricing = () => (
  <section className="pricing-section py-5">
    <div className="container">
      <h3 className="text-center text-primary mb-4">Nos formules</h3>
      <div className="row">
        <div className="col-md-4">
          <div className="pricing-card">
            <h4>Plan de base</h4>
            <p>Couverture essentielle pour les courts voyages.</p>
            <p><strong>19.99€/mois</strong></p>
          </div>
        </div>
        <div className="col-md-4">
          <div className="pricing-card">
            <h4>Plan premium</h4>
            <p>Couverture complète pour les longs séjours.</p>
            <p><strong>39.99€/mois</strong></p>
          </div>
        </div>
        <div className="col-md-4">
          <div className="pricing-card">
            <h4>Plan familial</h4>
            <p>Adapté aux familles qui voyagent ensemble.</p>
            <p><strong>59.99€/mois</strong></p>
          </div>
        </div>
      </div>
    </div>
  </section>
);

export default Pricing;
