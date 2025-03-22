import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import PaymentForm from "../components/payment/PaymentForm";
import contratService from '../services/contratService';
import { useNavigate } from "react-router-dom";

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
  
  const labels = {
    marque: "Marque",
    modele: "Modèle",
    motorisation: "Motorisation",
    fabrication: "Année de fabrication",
    utilisation: "Utilisation",
    duree: "Durée",
	type : "Votre type d'assurance",
	paysdepart : "Le pays de départ",
	paysArrive : "Le pays de destination",
	datedepart : "La date de départ",
	dateArrive : "La date d'arrivé",
	nbPersonnes : "Le nombre de voyageurs"
  };

  return (
    <div>
      <h2>Récapitulatif de la demande</h2>
	  
	  {Object.entries(formData)
	    .filter(([key]) => key !== "clientId") // Exclut clientId
	    .map(([key, value]) => (
	      <p key={key}>
	        <strong>{labels[key] || key} :</strong> {value}
	      </p>
	    ))}

      {/* Bouton pour afficher le formulaire de paiement */}
      <button onClick={() => setIsOpen(true)}>
        Passer au règlement
      </button>

      {isOpen && (
        <PaymentForm price={price} setStatusPayment={setStatusPayment} onClose={() => setIsOpen(false)} />
      )}
    </div>
  );
};

export default Recapitulatif;

