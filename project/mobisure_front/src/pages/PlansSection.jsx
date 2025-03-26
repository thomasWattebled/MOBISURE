import React, { useState, useEffect } from 'react';
import '../components/plans/style/PlansSection.css';
import Carousel from '../components/home/Carousel';
import SubOptionList from '../components/plans/components/SubOptionList';
import AssuranceVehicule from '../components/plans/components/AssuranceVehicule';
import AssuranceMotoForm from '../components/plans/components/formulaire/AssuranceMotoForm';
import voiture from '../components/plans/images/assurance_vehicule.jpg'; 
import moto from '../components/plans/images/moto.jpg'
import velo from '../components/plans/images/velo.jpg'
import AssuranceVoyage from '../components/plans/components/AssuranceVoyage';
import voyage_vacance from '../components/plans/images/voyage_vacance.jpg';
import voyage_pro from '../components/plans/images/voyage_professionel.jpg';
import VoyageProfessionnelForm from '../components/plans/components/formulaire/VoyageProForm';
import VoyageVacanceForm from '../components/plans/components/formulaire/VoyageVacanceForm';
import AssuranceVeloForm from '../components/plans/components/formulaire/AssuranceVeloForm';
import AssuranceVehiculeForm from '../components/plans/components/formulaire/AssuranceVehiculeForm';
import Recap from '../components/plans/components/Recap';
import { useAuth } from '../components/auth/AuthContext';
import UserService from '../services/userService';


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


    const { getUser } = useAuth();
    const userDetails = getUser();
    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState(null);

    const [selectedPlan, setSelectedPlan] = useState(null);
    const [subOptions, setSubOptions] = useState(null);
    const [selectedSubOption, setSelectedSubOption] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [isOptionSelected, setIsOptionSelected] = useState(false);
    const [isModalVisible, setModalVisible] = useState(false);
    
    useEffect(() => {
              setLoading(true);
              UserService.fetchUserByEmail(userDetails.unsername).then(data => {
                  setUser(data);
                  setLoading(false)
              });
            },[]);

            const [userData, setUserData] = useState({
              clientId: "",
              type: ""
            });

    // Met à jour formData une fois que l'utilisateur est chargé
    useEffect(() => {
          if (user) {
            setUserData((prevUserData) => ({
              ...prevUserData,
              clientId: user.id, // Ajout de l'ID client
            }));
          }
        }, [user]);
    
    
    // Gestion de la sélection des plans principaux
    const handlePlanSelect = (plan) => {
      setSelectedPlan(plan);
  
      if (plan === "Assurance Voyage") {
        setSubOptions([
          { label: "Voyage Vacance", value: voyage_vacance },
          { label: "Voyage Professionnel", value: voyage_pro },
        ]);
        setIsOptionSelected(true);
      }else if (plan === "Assurance Véhicule") {
        //setShowForm(true); 
        setSubOptions([
          { label: "Voiture", value: voiture },
          { label: "Moto", value: moto },
          { label: "Velo" , value: velo}
        ]);
        setIsOptionSelected(true);
      }  else {
        setSubOptions(null);
      }
    };

    const handleChange = (e) => {
      const { name, value } = e.target;
      setUserData({ ...userData, [name]: value });
    };
  
    // Gestion de la sélection des sous-options
    const handleSubOptionSelect = (subOption) => {
      setSelectedSubOption(subOption);
      setSubOptions(null); 
      //setIsOptionSelected(true);
        setShowForm(true); 
        setUserData((prevUserData) => ({
		    ...prevUserData,
		    type: changeType(subOption), // Met à jour dynamiquement le type choisi
		  }));
    };
	
	const changeType = (type) => {
		if(type === "Voiture"){ return "VOITURE"; }
		if(type === "Velo"){ return "VELO"; }
		if(type === "Moto"){ return "MOTO"; }
		if(type === "Voyage Vacance"){ return "VACANCES"; }
		if(type === "Voyage Professionnel"){ return "PROFESSIONNELLE"; }
		else{ return type; }
	};

    const handleBack = () => {
      setShowForm(false);
      setSelectedPlan(null);
      setSelectedSubOption(null);
      setIsOptionSelected(false);
      setSubOptions(null);
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

      {subOptions && !showForm ? (
        <div>
        <div className="sub-options-container">
            <SubOptionList
              subOptions={subOptions}
              onSubOptionSelect={handleSubOptionSelect}/>
        </div>
        <button onClick={handleBack} className="back-button">
        Retour
      </button>
      </div>
      ) : null}

  

      {showForm && selectedPlan === "Assurance Véhicule" && selectedSubOption === "Voiture" &&(
        <div>
      <AssuranceVehiculeForm 
      userData={userData}
      setUserData={setUserData}  
      handleChange={handleChange}
      isModalVisible={isModalVisible}
      setModalVisible={setModalVisible} />
      <button onClick={handleBack}   className="back-button">
        Retour
      </button>
      </div>
      )}

{showForm && selectedPlan === "Assurance Véhicule" && selectedSubOption === "Velo" &&(
        <div>
      <AssuranceVeloForm
	  		userData={userData}
        setUserData={setUserData}  
	        handleChange={handleChange}
	        isModalVisible={isModalVisible}
	        setModalVisible={setModalVisible}
	  />
      <button onClick={handleBack} className="back-button">
        Retour
      </button>
      </div>
      )}
        {/* Formulaire pour "Voyage Professionnel" */}
        {showForm && selectedPlan === "Assurance Voyage" && selectedSubOption === "Voyage Professionnel" &&(
          <div>
            <VoyageProfessionnelForm
				userData={userData}
			    setUserData={setUserData}  
			    handleChange={handleChange}
			    isModalVisible={isModalVisible}
			    setModalVisible={setModalVisible}
			/>
            <button onClick={handleBack} className="back-button">
              Retour
            </button>
          </div>
        )}

        {/* Formulaire pour "Assurancz vehicule Moto" */}
                {showForm && selectedPlan === "Assurance Véhicule" && selectedSubOption === "Moto" &&(
          <div>
            <AssuranceMotoForm
				userData={userData}
        		setUserData={setUserData}  
			    handleChange={handleChange}
			    isModalVisible={isModalVisible}
			    setModalVisible={setModalVisible}
			/>
            <button onClick={handleBack} className="back-button">
              Retour
            </button>
          </div>
        )}
      {/* Formulaire pour "Voyage Vacance" */}
      {showForm && selectedPlan === "Assurance Voyage" && selectedSubOption === "Voyage Vacance" && (
        <div>
          <VoyageVacanceForm
		  	userData={userData}
		    setUserData={setUserData}  
		    handleChange={handleChange}
		    isModalVisible={isModalVisible}
		    setModalVisible={setModalVisible}
		  />
          <button onClick={handleBack} className="back-button">
            Retour
          </button>
        </div>
        )}
        {/* Récapitulatif à droite */}
        <Recap selectedPlan={selectedPlan} subOptionType={selectedSubOption} />
      </div>
    );
  };



export default PlansSection;