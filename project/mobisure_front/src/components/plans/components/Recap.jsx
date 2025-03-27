import React from 'react';
import PropTypes from 'prop-types';

const Recap = ({ selectedPlan, subOptionType }) => {
  return (
	<div>
		<h3>Récapitulatif : </h3>
	    <div className="recap-container">
	      {selectedPlan ? (
	        <div>
	          <p>Vous avez choisi : <strong>{selectedPlan}</strong></p>
	          {subOptionType && <p>Type : <strong>{subOptionType}</strong></p>}
	        </div>
	      ) : (
	        <p>Aucun plan sélectionné.</p>
	      )}
	    </div>
	</div>
  );
};

Recap.propTypes = {
  selectedPlan: PropTypes.string, // Le plan sélectionné (Assurance Véhicule, Assurance Voyage)
  subOptionType: PropTypes.string, // Le type de sous-option sélectionné (Voyage Vacance, Voyage Professionnel)
};

Recap.defaultProps = {
  selectedPlan: null,
  subOptionType: null,
};

export default Recap;
