import React, { useEffect, useState } from 'react';
import AddSinistre from '../components/sinistre/addSinistre';
import UserService from '../services/userService';
import { useAuth } from '../components/auth/AuthContext';
import sinistreService from '../services/sinistreService';
import PopupConfirmation from '../components/sinistre/popUpConfirmation';

const Sinistre = () => {
  const [sinistreList, setSinistreList] = useState([]);
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const { getUser } = useAuth();
  const userDetails = getUser();
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);

  const [sinistreData, setSinistreData] = useState({
    // Informations générales
    categorieSinistre: "", // Ajout du champ catégorie
    date: "",
    description: "",
    photo: "",

    // Informations sur l’assuré
    nom: userDetails.nom || "",
    prenom: userDetails.prenom || "",
    email: userDetails.email || "",
    numeroContrat: "",

    // Spécifique au sinistre véhicule
    immatriculation: "",
    marque: "",
    modele: "",
    responsable: "",
    constat: "",

    // Spécifique au sinistre santé
    natureBlessure: "",
    hopital: "",
    medecin: "",
    ordonnance: "",
  });

  const handleCloseConfirmationModal = () => {
    setShowConfirmationModal(false);
    // We reset data
    setSinistreData({
      // Informations générales
      categorieSinistre: "", // Ajout du champ catégorie
      date: "",
      description: "",
      photo: "",
  
      // Informations sur l’assuré
      nom: userDetails.nom || "",
      prenom: userDetails.prenom || "",
      email: userDetails.email || "",
      numeroContrat: "",
  
      // Spécifique au sinistre véhicule
      immatriculation: "",
      marque: "",
      modele: "",
      responsable: "",
      constat: "",
  
      // Spécifique au sinistre santé
      natureBlessure: "",
      hopital: "",
      medecin: "",
      ordonnance: "",
    });
  }

  // Configuration pour le PDF selon la catégorie sélectionnée
  const pdfConfig = [
    { label: "Numéro de contrat", key: "numeroContrat" },
    { label: "Date du sinistre", key: "date" },
    { label: "Description", key: "description" },
    { label: "Photo", key: "photo", type: "image" },

    ...(sinistreData.categorieSinistre === "Véhicule"
      ? [
          { label: "Immatriculation", key: "immatriculation" },
          { label: "Marque", key: "marque" },
          { label: "Modèle", key: "modele" },
          { label: "Responsable", key: "responsable" },
          { label: "Constat", key: "constat", type: "image" },
        ]
      : sinistreData.categorieSinistre === "Santé"
      ? [
          { label: "Nature de la blessure", key: "natureBlessure" },
          { label: "Hôpital", key: "hopital" },
          { label: "Médecin", key: "medecin" },
          { label: "Ordonnance", key: "ordonnance", type: "image" },
        ]
      : []),
  ];

  const handleDeleteSinistre = (sinsitreId) => {
    sinistreService.deleteSinistre(sinsitreId).then(() => {
      refreshData();
    })
  }

  const handleGeneratePdf = (sinistreId, categorieSinistre) => {
    var file = "";
    sinistreService.getSinistreFile(sinistreId).then(response => {
      if (!response.ok) {
        throw new Error('Erreur de récupération des données');
      }
      else {
        response.json().then(data => {
          if (data.length) {
            file = sinistreService.buildFile(data[0]);
            console.log(file);
          }
          if (categorieSinistre === "Santé") {
            sinistreService.getHealthSinistre(sinistreId).then(response => {
              if (!response.ok) {
                throw new Error('Erreur de récupération des données');
              }
              else {
                response.json().then(data => {
                  console.log(data);
                  setSinistreData({
                    ...sinistreData,
                    "categorieSinistre" : categorieSinistre,
                    "date" : data.date,
                    "description" : data.description,
                    "numeroContrat" : data.contractId,
                    "natureBlessure" : data.nature,
                    "hopital" : data.hospital,
                    "ordonnance" : file
                  });
                  setShowConfirmationModal(true);
                });
              }
            });
          } else if (categorieSinistre === "Véhicule") {
            sinistreService.getCarSinistre(sinistreId).then(response => {
              if (!response.ok) {
                throw new Error('Erreur de récupération des données');
              }
              else {
                response.json().then(data => {
                  console.log(data);
                  setSinistreData({
                    ...sinistreData,
                    "categorieSinistre" : categorieSinistre,
                    "date" : data.date,
                    "description" : data.description,
                    "numeroContrat" : data.contractId,
                    "immatriculation" : data.immatriculation,
                    "marque" : data.brand,
                    "modele" : data.modele,
                    "responsable" : data.responsable,
                    "constat" : file
                  });
                  setShowConfirmationModal(true);
                });
              }
            });
          }
        });
      }
    });
  }

  useEffect(() => {
    UserService.fetchUserByEmail(userDetails.unsername).then(data => {
      setUser(data);
      console.log(data);
    });
  }, [userDetails.unsername]);

  const refreshData = () => {
    if (user?.id) {
      sinistreService.fetchAllSinistres(user.id)
      .then(response => {
        if (!response.ok) {
          throw new Error('Erreur de récupération des données');
        }
        else {
          response.json().then(data => {
            setSinistreList(data);
          });
        }
      })
    }
  }

  useEffect(() => {
    refreshData();
  }, [user?.id]);
  
  return(
  <div className="container mt-5">
      <h1>Gestion des sinistre</h1>
      <table>
      <thead>
        <tr>
          <th>Contract ID</th>
          <th>Catégorie</th>
          <th>Date</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {sinistreList.map(sinistre => (
          <tr key={sinistre.id}>
            <td>{sinistre.contractId}</td>
            <td>{sinistre.category}</td>
            <td>{sinistre.date}</td>
            <td>
              <button 
                onClick={() => handleDeleteSinistre(sinistre.id, sinistre.category)}
                style={{ backgroundColor: 'red', color: 'white', border: 'none', padding: '8px 12px', cursor: 'pointer' }}
              >
              Delete
              </button>
              <button
                onClick={() => handleGeneratePdf(sinistre.id, sinistre.category)}
                style={{backgroundColor: 'blue', color: 'white', border: 'none', padding: '8px 12px', cursor: 'pointer' }}
              >
                Générer PDF
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
    <AddSinistre refreshData={refreshData}/>
    {showConfirmationModal && (
        <PopupConfirmation
          onClose={() => handleCloseConfirmationModal()}
          formData={sinistreData}
          pdfConfig={pdfConfig}
        />
    )}
  </div>);
};

export default Sinistre;