import React, { useState } from 'react';
import PropTypes from 'prop-types';
import '../style/PlansSection.css';



const SubOptionList = ({ subOptions, onSubOptionSelect }) => {
  const [showForm, setShowForm] = useState(false);

  const handleSubOptionSelect = (subOption) => {
    onSubOptionSelect(subOption); 
    if (subOption === "Voyage Professionnel") {
      setShowForm(true); 
    } else {
      setShowForm(false); 
    }
  };

  return (
    <div className="sub-options-container-vehicle">
      {subOptions.map((option) => (
    <div key={option.value} onClick={() => handleSubOptionSelect(option.label)}>
      <div className="plan-card">
        <button className="plan-button">
          <img src={option.value} alt={option.label} className="plan-image" />
          <div className="plan-description">
            <h3>{option.label}</h3>
            <p>Choisissez cette option pour des services adaptés à vos besoins.</p>
          </div>
        </button>
      </div>
    </div>
  ))}

    </div>
  );
};

SubOptionList.propTypes = {
  subOptions: PropTypes.arrayOf(
    PropTypes.shape({
      label: PropTypes.string.isRequired,
      value: PropTypes.string.isRequired,
    })
  ).isRequired,
  onSubOptionSelect: PropTypes.func.isRequired,
};

export default SubOptionList;
