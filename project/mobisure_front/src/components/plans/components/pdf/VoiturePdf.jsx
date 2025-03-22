import React, { useState, useEffect } from "react";
import { jsPDF } from "jspdf";
import contratService from '../../../../services/contratService';

const ContratPDF = ({ numDossier }) => {
  const [contrat, setContrat] = useState(null);
  const service = new contratService();

  useEffect(() => {
 	
	service.getContrat(numDossier,setContrat);
	
  }, [numDossier]);

  const generatePDF = () => {
    if (!contrat) {
      console.error("Aucun contrat disponible");
      return;
    }

    const doc = new jsPDF();

    doc.setFont("helvetica", "bold");
    doc.text("Contrat de Location de Voiture", 20, 20);

    doc.setFont("helvetica", "normal");
    doc.text(`Nom du client : ${contrat.nomClient}`, 20, 40);
    doc.text(`Voiture : ${contrat.modeleVoiture} - ${contrat.immatriculation}`, 20, 50);
    doc.text(`Date de début : ${contrat.dateDebut}`, 20, 60);
    doc.text(`Date de fin : ${contrat.dateFin}`, 20, 70);
    doc.text(`Prix total : ${contrat.prix} €`, 20, 80);

    doc.save("contrat_voiture.pdf");
  };

  return (
    <div>
      <h2>Contrat n° {numDossier}</h2>
      {contrat ? (
        <button onClick={generatePDF}>Télécharger le PDF</button>
      ) : (
        <p>Chargement des données...</p>
      )}
    </div>
  );
};

export default ContratPDF;
