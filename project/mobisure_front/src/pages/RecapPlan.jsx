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
	if(formData.type === "MOTO"){ service.getDevis("devisMoto",formData,setPrice); }
	if(formData.type === "VELO"){ service.getDevis("devisVelo",formData,setPrice); }
	
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
			
  },[statusPayment]);

  return (
    <div>
      <h2>Récapitulatif de la demande</h2>
      <p><strong>Marque :</strong> {formData.marque}</p>
      <p><strong>Modèle :</strong> {formData.modele}</p>
      <p><strong>Motorisation :</strong> {formData.motorisation}</p>
      <p><strong>Année de fabrication :</strong> {formData.fabrication}</p>
      <p><strong>Utilisation :</strong> {formData.utilisation}</p>
      <p><strong>Durée :</strong> {formData.duree}</p>

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

