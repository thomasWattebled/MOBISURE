import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import PaymentForm from "../components/payment/PaymentForm";
import contratService from '../services/contratService';
import { useNavigate } from "react-router-dom";
import '../assets/css/recapPlan.css';

const Recapitulatif = () => {
  const location = useLocation();
  const formData = location.state?.formData || {}; 
  const [isOpen, setIsOpen] = useState(false);
  const [price, setPrice] = useState(0);
  const [statusPayment, setStatusPayment] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
	
	const service = new contratService();
	
	if(formData.type === "VOITURE"){ service.getDevis("devisVoiture",formData,setPrice); }
	else if(formData.type === "MOTO"){ service.getDevis("devisMoto",formData,setPrice); }
	else if(formData.type === "VELO"){ service.getDevis("devisVelo",formData,setPrice); }
	else if(formData.type === "VACANCES"){ service.getDevis("devisVacances",formData,setPrice); }
	else if(formData.type === "PROFESSIONNELLE"){ service.getDevis("devisProfessionnelle",formData,setPrice); }
	
  },[]); 
  
  useEffect(() => {
	const service = new contratService();
	
	if(formData.type === "VOITURE" && statusPayment===true){ 
		service.createContrat("createVoiture",formData); 
		navigate("/home");
	}
	
	else if(formData.type === "MOTO" && statusPayment===true){ 
		service.createContrat("createMoto",formData); 
		navigate("/home");
	}
		
	else if(formData.type === "VELO" && statusPayment===true){ 
		service.createContrat("createVelo",formData); 
		navigate("/home");
	}
	
	else if(formData.type === "VACANCES" && statusPayment===true){ 
		service.createContrat("createVacances",formData); 
		navigate("/home");
	}
	
	else if(formData.type === "PROFESSIONNELLE" && statusPayment===true){ 
		service.createContrat("createProfessionnelle",formData); 
		navigate("/home");
	}
			
  },[statusPayment]);
  
  let labels = {};
  
  if(formData.type === "VACANCES"){
	labels = {
		type : "Votre type d'assurance",
		paysDepart : "Le pays de départ",
		paysArrive : "Le pays de destination",
		villeDepart: "La ville de départ",
		villeArrive: "La ville d'arrivé",
		dateDepart : "La date de départ",
		dateArrive : "La date d'arrivé",
		nbPersonnes : "Le nombre de voyageurs"
	  };
  }
  
  else if (formData.type === "PROFESSIONNELLE"){
  		labels = {
  				type : "Votre type d'assurance",
  				entreprise : "Votre entreprise",
				paysDepart : "Le pays de départ",
  				paysArrive : "Le pays de destination",
				villeDepart: "La ville de départ",
				villeArrive: "La ville d'arrivé",
  				dateDepart : "La date de départ",
  				dateArrive : "La date d'arrivé",
  			  };
   		}
		
  else if (formData.type === "VELO"){
	labels = {
		type : "Votre type d'assurance",
		motorisation: "Motorisation"
	};
  }
		
  else {
	labels = {
	    marque: "Marque",
	    modele: "Modèle",
	    motorisation: "Motorisation",
	    fabrication: "Année de fabrication",
	    utilisation: "Utilisation",
	    duree: "Durée",
		type : "Votre type d'assurance",
	  };
  }
  
  return (
      <div className="recap-container">
        <div className="recap-card">
          <h2 className="recap-title">Récapitulatif de votre demande</h2>
          
          <div className="recap-details">
            {Object.entries(formData)
              .filter(([key]) => key !== "clientId" && key !== "co2" && key !== "coordDepart" && key !== "coordArrive" && key !== "transport" && key !== "distance")
              .map(([key, value]) => (
                <div key={key} className="recap-item">
                  <span className="recap-label">{labels[key] || key}</span>
                  <span className="recap-value">{value}</span>
                </div>
              ))}
          </div>

          <div className="recap-summary">
            <div className="price-display">
              <span>Montant total :</span>
              <span className="price">{price} €</span>
            </div>
            
            <button 
              className="payment-button"
              onClick={() => setIsOpen(true)}
            >
              Procéder au paiement
            </button>
          </div>
        </div>

        {isOpen && (
          <PaymentForm 
            price={price} 
            setStatusPayment={setStatusPayment} 
            onClose={() => setIsOpen(false)} 
          />
        )}
      </div>
    );
  };

export default Recapitulatif;

