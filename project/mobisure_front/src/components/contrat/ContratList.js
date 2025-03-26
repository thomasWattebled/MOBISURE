import React, { useState, useEffect } from "react";
import contratService from "../../services/contratService";
import { generatePDFVoitureMoto, generatePDFProfessionnelle, generatePDFVelo, generateVacances } from "../pdf/ContratPDF";

const ContratList = () => {
  const [contratList, setContratList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const serviceContrat = new contratService();

  useEffect(() => {
    serviceContrat
      .getAllContrat()
      .then((data) => {
        setContratList(Array.isArray(data) ? data : []);
      })
      .catch((err) => setError(err))
      .finally(() => setLoading(false));
  }, []);
  
  
  
  const generatePDF = (contrat) => {
	if(contrat.type === "VOITURE" || contrat.type === "MOTO"){
		return generatePDFVoitureMoto(contrat);
	}
	if(contrat.type === "VELO"){
		return generatePDFVelo(contrat);
	}
	if(contrat.type === "VACANCES"){
		return generateVacances(contrat);
	}
	if(contrat.type === "PROFESSIONNELLE"){
		return generatePDFProfessionnelle(contrat);
	}
  };

  if (loading) return <p>Chargement des contrats...</p>;
  if (error) return <p>Erreur : {error.message}</p>;
	console.log(contratList);
  return (
    <div className="my-assistance-container">
      <h1 className="my-assistance-title">Vos contrats</h1>
      {contratList.length > 0 ? (
        <table className="my-assistance-table">
          <thead>
            <tr>
              <th>Numéro de dossier</th>
              <th>Type de contrat</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {contratList.map((contract) => (
              <tr key={contract.id}>
                <td>{contract.numDossier}</td>
                <td>{contract.type}</td>
                <td>
                  <button className="btn btn-primary" onClick={() => generatePDF(contract)}>
                    Télécharger PDF
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Aucun contrat trouvé.</p>
      )}
    </div>
  );
};

export default ContratList;
