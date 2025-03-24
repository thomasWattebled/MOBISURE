import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import RegistrationFormView from './RegistrationFormView';
import SuccessModal from './SuccessModal';

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
  const [isModalVisible, setModalVisible] = useState(false);
 

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

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
	        setModalVisible(true); // Ouvre le modal si la soumission est un succès
	      }
	      return response.text();
	    })
	    .then((data) => {
	      console.log(data);
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
      isModalVisible={isModalVisible}
      setModalVisible={setModalVisible}
    />
  );
};

export default RegistrationForm;