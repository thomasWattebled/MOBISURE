import React from 'react';
import '../assets/css/PlansSection.css';



const PlansSection = () => {
  return (
    <div className="plans-section">
      <h1>Our Travel Insurance Plans</h1>
      <ul>
        <li>Basic Plan - Essential coverage for short trips.</li>
        <li>Premium Plan - Comprehensive coverage for long-term travel.</li>
        <li>Family Plan - Tailored for families traveling together.</li>
      </ul>
    </div>
  );
};

export default PlansSection;