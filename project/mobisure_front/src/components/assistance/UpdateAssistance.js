import React, { useState, useEffect } from 'react';
import '../../assets/css/updateAssistance.css';

const UpdateAssistance = ({ numDossier }) => {
  const [assistance, setAssistance] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [file, setFile] = useState(null);
  const [uploadMessage, setUploadMessage] = useState('');
  const [documents, setDocuments] = useState([]);

  useEffect(() => {
    if (numDossier) {
      fetch(`http://localhost:8081/assistance/getByNumDossier/${numDossier}`)
        .then(response => {
          if (!response.ok) {
            throw new Error('Erreur de récupération des données');
          }
          return response.json();
        })
        .then(data => {
          setAssistance(data);
          setLoading(false);
        })
        .catch(error => {
          setError(error.message);
          setLoading(false);
        });

      fetchDocuments(); // Charge les documents au chargement du composant
    }
  }, [numDossier]);

  const fetchDocuments = async () => {
    try {
      const response = await fetch(`http://localhost:8081/documents/by-dossier/${numDossier}`);
      if (response.ok) {
        const data = await response.json();
        setDocuments(data);
      } else {
        setDocuments([]);
      }
    } catch (error) {
      setDocuments([]);
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

      if (response.ok) {
        setUploadMessage("Document ajouté avec succès !");
        fetchDocuments(); // Recharge les documents après l'upload
      } else {
        setUploadMessage("Erreur lors de l'ajout du document.");
      }
    } catch (error) {
      setUploadMessage("Erreur réseau. Vérifiez votre connexion.");
    }
  };

  if (loading) {
    return <div>Chargement...</div>;
  }

  if (error) {
    return <div>Erreur : {error}</div>;
  }

  return (
    <div>
      <h3>Récapitulatif de votre dossier :</h3>
	  
	  <button>Ajouter une personne au dossier</button>
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
    </div>
  );
};

export default UpdateAssistance;
