import React, { useState } from 'react';
import RegistrationFormView from './RegistrationFormView';

const RegistrationForm = () => {
  const [formData, setFormData] = useState({
    nom: '',
	  prenom: '',
    mail: '',
    mdp: '',
    sexe: '',
    dateNaissance: '',
    adresse: '', 
    telephone: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const formDataWithDate = {
      ...formData,
      date_creation: new Date().toISOString().split('T')[0], // Ajout date de la creation du compte pour la bdd
    };
    fetch('http://localhost:8080/users/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formDataWithDate),
    })
      .then((response) => response.text())
      .then((data) => {
        alert(data); // Affiche la réponse du backend
      })
      .catch((error) => {
        console.error('Erreur :', error);
      });
  };
  

  return (
    <RegistrationFormView
      formData={formData}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
    />
  );
};

export default RegistrationForm;