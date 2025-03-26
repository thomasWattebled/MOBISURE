import React, { useState } from "react";
import { validateCardNumber, validateCVV } from "./verificationCarte";
import "./PaymentForm.css"; 

const PaymentForm = ({ price, setStatusPayment , onClose }) => {
  const [cardNumber, setCardNumber] = useState("");
  const [cardHolder, setCardHolder] = useState("");
  const [cvv, setCvv] = useState("");
  const [message, setMessage] = useState("");
  const [cardNumberError, setCardNumberError] = useState("");
  const [cvvError, setCvvError] = useState("");

  const handleCardNumberChange = (e) => {
    const value = e.target.value;
    setCardNumber(value);
    setCardNumberError(value && !validateCardNumber(value) ? "Numéro invalide" : "");
  };

  const handleCVVChange = (e) => {
    const value = e.target.value;
    setCvv(value);
    setCvvError(value && !validateCVV(value) ? "CVV invalide" : "");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (cardNumberError || cvvError) {
      setMessage("Corrigez les erreurs avant de soumettre.");
      return;
    }

    const paymentRequest = { cardNumber, cardHolder, cvv, amount: price };

    try {
      const response = await fetch("http://localhost:8088/api/tryPayment", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(paymentRequest),
      });

      const result = await response.text();
      console.log(result);
      setMessage(result === "Payment successful!" ? "Paiement réussi !" : "Échec du paiement.");
      if(result === "Payment successful!"){ setStatusPayment(true) }
      onClose();
    } catch (error) {
      setMessage("Erreur lors du paiement.");
    }
  };

  return (
    <div className="overlay">
      <div className="popup">
        <h2>Paiement</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="cardNumber">Numéro de carte :</label>
            <input 
              id="cardNumber" 
              type="text" 
              value={cardNumber} 
              onChange={handleCardNumberChange} 
              required 
            />
            {cardNumberError && <p className="error">{cardNumberError}</p>}
          </div>
          <div>
            <label htmlFor="cardHolder">Nom du titulaire :</label>
            <input 
              id="cardHolder" 
              type="text" 
              value={cardHolder} 
              onChange={(e) => setCardHolder(e.target.value)} 
              required 
            />
          </div>
          <div>
            <label htmlFor="cvv">CVV :</label>
            <input 
              id="cvv" 
              type="text" 
              value={cvv} 
              onChange={handleCVVChange} 
              required 
            />
            {cvvError && <p className="error">{cvvError}</p>}
          </div>
          <div>
            <label htmlFor="amount">Montant :</label>
            <input 
              id="amount" 
              type="number" 
              value={price} 
              readOnly 
            />
          </div>
          <button type="submit">Valider</button>
          <button type="button" onClick={onClose} className="close-button">Annuler</button>
        </form>
        {message && <p>{message}</p>}
      </div>
    </div>
  );
};

export default PaymentForm;