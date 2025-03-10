import React, { useState, useEffect } from "react";
import { useAuth } from "../auth/AuthContext";
import UserService from "../../services/userService";
import jsPDF from "jspdf";
import "jspdf-autotable"; // Pour les tableaux
import logo from "../../logo192.png";

<<<<<<< HEAD
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
=======
const GeneratePDF = ({ data, config, fileName = "recapitulatif.pdf" }) => {
  const { getUser } = useAuth();
  const userDetails = getUser();
  const [user, setUser] = useState(null);

  useEffect(() => {
    UserService.fetchUserByEmail(userDetails.unsername).then((data) => {
      setUser(data);
    });
  }, []);
>>>>>>> 594bc65 (generation pdf)

  const generatePDF = () => {
    const doc = new jsPDF();
    let yOffset = 20;

<<<<<<< HEAD
    let yOffset = 10;

    if (user) {
      // Ajouter les informations de l'utilisateur
=======
    // Ajout du logo et titre
    doc.addImage(logo, "PNG", 80, 10, 50, 20);
    yOffset += 30;
    doc.setFontSize(18);
    doc.text("Rapport de Sinistre", 70, yOffset);
    yOffset += 10;
    doc.setFontSize(12);

    // Section utilisateur
    doc.setFont("helvetica", "bold");
    doc.text("Informations de l'utilisateur", 10, yOffset);
    doc.setFont("helvetica", "normal");
    yOffset += 10;

    if (user) {
>>>>>>> 594bc65 (generation pdf)
      doc.text(`Nom: ${user.nom}`, 10, yOffset);
      yOffset += 10;
      doc.text(`Prénom: ${user.prenom}`, 10, yOffset);
      yOffset += 10;
      doc.text(`Email: ${user.mail}`, 10, yOffset);
<<<<<<< HEAD
      yOffset += 20;
=======
      yOffset += 15;
    }

    // Section sinistre
    doc.setFont("helvetica", "bold");
    doc.text("Détails du sinistre", 10, yOffset);
    doc.setFont("helvetica", "normal");
    yOffset += 10;

    const sinistreDetails = [
      ["Type de sinistre", data.type || "N/A"],
      ["Date du sinistre", data.date || "N/A"],
      ["Description", data.description || "N/A"],
    ];

    if (data.categorieSinistre === "Véhicule") {
      sinistreDetails.push(
        ["Immatriculation", data.immatriculation || "N/A"],
        ["Marque", data.marque || "N/A"],
        ["Modèle", data.modele || "N/A"],
        ["Responsable", data.responsable || "N/A"]
      );
    }

    if (data.categorieSinistre === "Santé") {
      sinistreDetails.push(
        ["Nature de la blessure", data.natureBlessure || "N/A"],
        ["Hôpital", data.hopital || "N/A"],
        ["Médecin", data.medecin || "N/A"]
      );
>>>>>>> 594bc65 (generation pdf)
    }

    config.forEach((item) => {
<<<<<<< HEAD
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
=======
      const { label, key, type = "text" } = item;
>>>>>>> 594bc65 (generation pdf)

      if (type === "text") {
        doc.text(`${label}: ${data[key] || "N/A"}`, 10, yOffset);
        yOffset += 10;
      }
    });

    // Gestion des images centrées
    config.forEach((item) => {
      if (item.type === "image" && data[item.key] instanceof File) {
        const reader = new FileReader();
        reader.onload = (event) => {
          const imgData = event.target.result;
          const imgWidth = 100;
          const pageWidth = doc.internal.pageSize.width;
          const xPosition = (pageWidth - imgWidth) / 2;
          doc.addImage(imgData, "JPEG", xPosition, yOffset, imgWidth, 75);
          yOffset += 85;
          doc.save(fileName);
        };
        reader.readAsDataURL(data[item.key]);
      }
    });

    // Sauvegarde du PDF si aucune image n'est présente
    if (!config.some((item) => item.type === "image" && data[item.key] instanceof File)) {
      doc.save(fileName);
    }
  };

  return <button onClick={generatePDF}>Générer PDF</button>;
};

export default GeneratePDF;
