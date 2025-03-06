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

    // Simulate form submission
    console.log('Form submitted:', formDataWithDate);
    setModalVisible(true);


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