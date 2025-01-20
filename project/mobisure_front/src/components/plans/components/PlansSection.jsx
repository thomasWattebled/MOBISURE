import React from 'react';
import '../style/PlansSection.css';
import Carousel from '../../home/Carousel';
import AssuranceVehicule from './AssuranceVehicule';
import AssuranceVoyage from './AssuranceVoyage';
const PlansSection = () => {
  const reforestationData = [
    { image: require("../images/reforestation1.jpg"),
      description: "Les forêts absorbent près de 2,6 milliards de tonnes de CO2 par an, réduisant les impacts du changement climatique.",   
    },
    { image: require("../images/reforestation2.jpg"),
      description: "Replanter des arbres peut restaurer la biodiversité, créant des habitats pour des millions d'espèces.",
    },
    { image: require("../images/reforestation3.jpg"),
      description: "Chaque année, 10 millions d'hectares de forêts sont perdus. La reforestation peut inverser cette tendance.",
    }];

  return (

    <div className="page-container">
    {/* Carrousel de reforestation */}
    <div className="reforestation-section">
      <Carousel images={reforestationData} visibleCount={1} interval={5000} />
    </div>

    <div className="plans-container">
      <AssuranceVehicule/>
      <AssuranceVoyage/>
    </div>
    </div>
  );
};



export default PlansSection;