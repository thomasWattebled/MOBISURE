import React, { useState, useEffect } from 'react';
import AddPeople from './AddPeople.js';
import { WhenUserIsInRole } from '../security/PrivateRoute.js';
import '../../assets/css/updateAssistance.css';

const UpdateAssistance = ({ numDossier }) => {
  const [assistance, setAssistance] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [file, setFile] = useState(null);
  const [uploadMessage, setUploadMessage] = useState('');
  const [documents, setDocuments] = useState([]);
  const [addConseiller, setAddConseiller] = useState(false);
  const [addMedecin, setAddMedecin] = useState(false);
  const [addPart, setAddPart] = useState(false);

  useEffect(() => {
    console.log(numDossier);
    if (numDossier) {
      fetch(`http://localhost:8081/assistance/getByNumDossier/${numDossier}`)
        .then(response => {
          if (!response.ok) {
            throw new Error(`Erreur de récupération des données : ${response.statusText}`);
          }
          return response.json();
        })
        .then(data => {
          setAssistance(data);
          setLoading(false);
        })
        .catch(error => {
          setError(`Erreur : ${error.message}`);
          setLoading(false);
        });

      fetchDocuments(); // Charger les documents au démarrage
    }
  }, [numDossier]);

  const fetchDocuments = async () => {
    try {
      const response = await fetch(`http://localhost:8081/documents/by-dossier/${numDossier}`);
      if (!response.ok) {
        throw new Error(`Erreur lors de la récupération des documents : ${response.statusText}`);
      }
      const data = await response.json();
      setDocuments(data);
    } catch (error) {
      setDocuments([]);
      setError(`Erreur : ${error.message}`);
    }
  };

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleUpload = async (event) => {
    event.preventDefault();

    if (!file) {
      setUploadMessage("Veuillez sélectionner un fichier.");
      return;
    }

    const formData = new FormData();
    formData.append("numDossier", numDossier);
    formData.append("file", file);

    try {
      const response = await fetch("http://localhost:8081/documents/upload", {
        method: "POST",
        body: formData
      });

      if (!response.ok) {
        throw new Error(`Erreur lors de l'ajout du document : ${response.statusText}`);
      }

      setUploadMessage("Document ajouté avec succès !");
      fetchDocuments(); // Recharge les documents après l'upload
    } catch (error) {
      setUploadMessage(`Erreur : ${error.message}`);
    }
  };
  
  const openConseiller = () => {
    setAddConseiller(true);
  };
  
  const closeConseiller = () => {
    setAddConseiller(false);
  };
  
  const openMedecin = () => {
      setAddMedecin(true);
    };
    
    const closeMedecin = () => {
      setAddMedecin(false);
    };
	
	const openPart = () => {
	      setAddPart(true);
	    };
	    
	    const closePart = () => {
	      setAddPart(false);
	    };

  if (loading) {
    return <div>Chargement...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  console.log(assistance);
  return (
    <div>
      <h3>Récapitulatif de votre dossier :</h3>
      
	  <WhenUserIsInRole role="CONSEILLER">
	      <button onClick={openConseiller}>Ajouter un conseiller</button>
		  <button onClick={openMedecin}>Ajouter un médecin</button>
		  <button onClick={openPart}>Ajouter un partenaire</button>
	  </WhenUserIsInRole>
      <table>
        <tbody>
          <tr>
            <td className='att'>Nom et prénom :</td>
            <td>{assistance.nom} {assistance.prenom}</td>
          </tr>
          <tr>
            <td className='att'>Numéro de téléphone :</td>
            <td>{assistance.telephone}</td>
          </tr>
          <tr>
            <td className='att'>Date de la demande d'assistance :</td>
            <td>{new Date(assistance.date).toLocaleDateString()}</td>
          </tr>
          <tr>
            <td className='att'>Type de demande :</td>
            <td>{assistance.type}</td>
          </tr>
		  {(assistance.type === "AUTO" || assistance.type === "ACCIDENT") &&
			<>
			<tr>
				<td className='att'>Ville :</td>
			    <td>{assistance.ville}</td>
			</tr>
			<tr>
				<td className='att'>Rue :</td>
				<td>{assistance.rue}</td>
			</tr>
			</>
		  }
		  {assistance.type === "ACCIDENT" &&
			<>
			<tr>
				<td className='att'>Nombre de bléssés :</td>
				<td>{assistance.nbBlesse}</td>
			</tr>
			</>
		  }
		  
		  {assistance.type === "REMBOURSEMENT" &&
			<tr>
				<td className='att'>Montant du remboursement : </td>
				<td>{assistance.montant} €</td>
			</tr>
		  }
		  {(assistance.type === "REMBOURSEMENT" || assistance.type === "MEDICAL") &&
			<tr>
				<td className='att'>Motif de la demande :</td>
				<td>{assistance.motif}</td>
			</tr>
		  }
          <tr>
            <td className='att'>La demande :</td>
            <td>{assistance.message}</td>
          </tr>
        </tbody>
      </table>
      
      <h3>Ajouter un document :</h3>
      
      <div className="upload-section">
        <input type="file" onChange={handleFileChange} />
        <button id="envoi" type="submit" onClick={handleUpload}>Envoyer</button>
      </div>
      
      {uploadMessage && <p>{uploadMessage}</p>}

      {/* Liste des documents */}
      <h3>Documents liés :</h3>
      <div className="documents-section">
        {documents.length > 0 ? (
          <ul>
            {documents.map((doc) => (
              <li key={doc.id}>
                <span>{doc.name}</span>
                <a
                  href={`http://localhost:8081/documents/download/${doc.id}`}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  Télécharger
                </a>
              </li>
            ))}
          </ul>
        ) : (
          <p className="message">Aucun document disponible.</p>
        )}
      </div>
      
      {addConseiller && (
        <div className="modal">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title">Ajout d'un conseiller</h5>
              <button type="button" onClick={closeConseiller}>×</button>
            </div>
            <div className="modal-body">
              <AddPeople role={"conseiller"} numDossier={numDossier} />
            </div>
            <div className="modal-footer">
              <button type="button" onClick={closeConseiller}>
                Fermer
              </button>
            </div>
          </div>
        </div>
      )}
	  
	  {addMedecin && (
	          <div className="modal">
	            <div className="modal-content">
	              <div className="modal-header">
	                <h5 className="modal-title">Ajout d'un médecin</h5>
	                <button type="button" onClick={closeMedecin}>×</button>
	              </div>
	              <div className="modal-body">
	                <AddPeople role={"medecin"} numDossier={numDossier} />
	              </div>
	              <div className="modal-footer">
	                <button type="button" onClick={closeMedecin}>
	                  Fermer
	                </button>
	              </div>
	            </div>
	          </div>
	        )}
			
			{addPart && (
				          <div className="modal">
				            <div className="modal-content">
				              <div className="modal-header">
				                <h5 className="modal-title">Ajout d'un Partenaire</h5>
				                <button type="button" onClick={closePart}>×</button>
				              </div>
				              <div className="modal-body">
				                <AddPeople role={"partenaire"} numDossier={numDossier} />
				              </div>
				              <div className="modal-footer">
				                <button type="button" onClick={closePart}>
				                  Fermer
				                </button>
				              </div>
				            </div>
				          </div>
				        )}
    </div>
  );
};


export default UpdateAssistance;

