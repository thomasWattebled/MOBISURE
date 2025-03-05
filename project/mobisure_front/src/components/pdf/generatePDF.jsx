import React, { useState, useEffect } from 'react';
import { useAuth } from '../auth/AuthContext';
import UserService from '../../services/userService';
import jsPDF from 'jspdf';

const GeneratePDF = ({ data, config, fileName = 'recapitulatif.pdf' }) => {
  const { getUser } = useAuth(); // Récupération de l'utilisateur via AuthContext
  const userDetails = getUser();
  const [user, setUser] = useState(null); // État pour les données utilisateur

  useEffect(() => {
    if (userDetails && userDetails.username) {
      UserService.fetchUserByEmail(userDetails.username).then((data) => {
        setUser(data);
        console.log(data);
      });
    }
  }, [userDetails]);

  const generatePDF = () => {
    const doc = new jsPDF();

    let yOffset = 10;

    if (user) {
      // Ajouter les informations de l'utilisateur
      doc.text(`Nom: ${user.nom}`, 10, yOffset);
      yOffset += 10;
      doc.text(`Prénom: ${user.prenom}`, 10, yOffset);
      yOffset += 10;
      doc.text(`Email: ${user.mail}`, 10, yOffset);
      yOffset += 20;
    }

    // Ajouter les données du formulaire
    config.forEach((item) => {
      const { label, key, type = 'text' } = item;
      if (type === 'text') {
        doc.text(`${label}: ${data[key]}`, 10, yOffset);
        yOffset += 10;
      } else if (type === 'image' && data[key] instanceof File) {
        const reader = new FileReader();
        reader.onload = (event) => {
          const imgData = event.target.result;
          doc.addImage(imgData, 'JPEG', 10, yOffset, 50, 50);
          yOffset += 60;
          doc.save(fileName);
        };
        reader.readAsDataURL(data[key]);
      }
    });

    // Si aucune image n'est présente, sauvegarder le PDF immédiatement
    if (!config.some((item) => item.type === 'image' && data[item.key] instanceof File)) {
      doc.save(fileName);
    }
  };

  return (
    <button type="button" onClick={generatePDF}>
      Générer PDF
    </button>
  );
};

export default GeneratePDF;