import React, { useState } from 'react';
import RegistrationFormView from './RegistrationFormView';

const RegistrationForm = () => {
  const [formData, setFormData] = useState({
    nom: '',
    prenom: '',
    mail: '',
    mdp: '',
    confirmMdp: '', // Nouveau champ pour la confirmation du mot de passe
    sexe: '',
    dateNaissance: '',
    adresse: '',
    telephone: '',
  });
  const [isModalVisible, setModalVisible] = useState(false);
  const [error, setError] = useState(''); // Nouveau : Gestion des erreurs

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const validateForm = () => {
    if (!formData.nom || !formData.prenom || !formData.mail || !formData.mdp || !formData.confirmMdp) {
      return 'Tous les champs obligatoires doivent être remplis.';
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(formData.mail)) {
      return 'Veuillez entrer une adresse e-mail valide.';
    }

    if (formData.mdp.length < 6) {
      return 'Le mot de passe doit contenir au moins 6 caractères.';
    }

    if (formData.mdp !== formData.confirmMdp) {
      return 'Les mots de passe ne correspondent pas.';
    }

    return ''; // Pas d'erreur
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Validation avant envoi
    const validationError = validateForm();
    if (validationError) {
      setError(validationError);
      return;
    }

    const formDataWithDate = {
      ...formData,
      date_creation: new Date().toISOString().split('T')[0],
    };

    fetch('http://localhost:8080/users/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formDataWithDate),
    })
      .then((response) => {
        if (response.ok) {
          setModalVisible(true);
        } else {
          return response.text().then((text) => {
            console.log(text);  // Affiche la réponse en cas d'erreur
            throw new Error(text || 'Erreur lors de l’inscription.');
          });
        }
      })
      .catch((err) => {
        setError(err.message);
      });
  };

  return (
    <RegistrationFormView
      formData={formData}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
      isModalVisible={isModalVisible}
      setModalVisible={setModalVisible}
      error={error} // Nouveau : gestion des erreurs
    />
  );
};

export default RegistrationForm;
