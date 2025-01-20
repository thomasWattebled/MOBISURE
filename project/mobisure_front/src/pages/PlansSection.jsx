import React, { useState } from 'react';
import '../components/plans/style/PlansSection.css';
import Carousel from '../components/home/Carousel';
import SubOptionList from '../components/plans/components/SubOptionList';
import AssuranceVehicule from '../components/plans/components/AssuranceVehicule';
import AssuranceVoyage from '../components/plans/components/AssuranceVoyage';
import voyage_vacance from '../components/plans/images/voyage_vacance.jpg';
import voyage_pro from '../components/plans/images/voyage_professionel.jpg';
import VoyageProfessionnelForm from '../components/plans/components/formulaire/VoyageProForm';
import Recap from '../components/plans/components/Recap';

const PlansSection = () => {
  const reforestationData = [
    { image: require("../components/plans/images/reforestation1.jpg"),
      description: "Les forêts absorbent près de 2,6 milliards de tonnes de CO2 par an, réduisant les impacts du changement climatique.",   
    },
    { image: require("../components/plans/images/reforestation2.jpg"),
      description: "Replanter des arbres peut restaurer la biodiversité, créant des habitats pour des millions d'espèces.",
    },
    { image: require("../components/plans/images/reforestation3.jpg"),
      description: "Chaque année, 10 millions d'hectares de forêts sont perdus. La reforestation peut inverser cette tendance.",
    }];



    const [selectedPlan, setSelectedPlan] = useState(null);
    const [subOptions, setSubOptions] = useState(null);
    const [selectedSubOption, setSelectedSubOption] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [isOptionSelected, setIsOptionSelected] = useState(false);
    
    // Gestion de la sélection des plans principaux
    const handlePlanSelect = (plan) => {
      setSelectedPlan(plan);
  
      if (plan === "Assurance Voyage") {
        setSubOptions([
          { label: "Voyage Vacance", value: voyage_vacance },
          { label: "Voyage Professionnel", value: voyage_pro },
        ]);
        setIsOptionSelected(true);
      } else {
        setSubOptions(null);
      }
    };
  
    // Gestion de la sélection des sous-options
    const handleSubOptionSelect = (subOption) => {
      setSelectedSubOption(subOption);
      setSubOptions(null); 
      //setIsOptionSelected(true);
      if (subOption === "Voyage Professionnel") {
        setShowForm(true); // Affiche le formulaire lorsque "Voyage Professionnel" est sélectionné
      } else {
        setShowForm(false); // Cache le formulaire pour "Voyage Vacance"
      }
    };

    return (
      <div className="page-container">
        {/* Carrousel de reforestation */}
        <div className="reforestation-section">
          <Carousel images={reforestationData} visibleCount={1} interval={5000} />
        </div>
  
        {/* Affichage des plans si aucun formulaire n'est visible */}
        {!showForm && !isOptionSelected ? (
        <div className="plans-container">
          <div onClick={() => handlePlanSelect("Assurance Véhicule")}>
            <AssuranceVehicule />
          </div>
          <div onClick={() => handlePlanSelect("Assurance Voyage")}>
            <AssuranceVoyage />
          </div>
        </div>
      ) : null}

      {/* Affichage des sous-options de voyage si aucune option n'est sélectionnée */}
      {subOptions  && !showForm ? (
        <div className="sub-options-container">
          <SubOptionList
            subOptions={subOptions}
            onSubOptionSelect={handleSubOptionSelect}
          />
        </div>
      ) : null}
  
        {/* Formulaire pour "Voyage Professionnel" */}
        {showForm && (
         <VoyageProfessionnelForm/>
        )}
  
        {/* Récapitulatif à droite */}
        <Recap selectedPlan={selectedPlan} subOptionType={selectedSubOption} />
      </div>
    );
  };



export default PlansSection;