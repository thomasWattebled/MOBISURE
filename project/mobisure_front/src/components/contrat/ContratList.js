import React, { useState, useEffect } from "react";
import UserService from "../../services/userService";
import { useAuth } from "../auth/AuthContext";
import contratService from "../../services/contratService";
import { 
  generatePDFVoitureMoto, 
  generatePDFProfessionnelle, 
  generatePDFVelo, 
  generateVacances 
} from "../pdf/ContratPDF";

const ContratList = () => {
  const [contratList, setContratList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [pdfLoading, setPdfLoading] = useState(false);
  const [error, setError] = useState(null);
  const serviceContrat = new contratService();
  const { getUser } = useAuth();
  const userDetails = getUser();

  useEffect(() => {
    const fetchContrats = async () => {
      try {
        setLoading(true);
        const data = await serviceContrat.getAllContrat();
        setContratList(Array.isArray(data) ? data : []);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchContrats();
  }, []);

  const handleGeneratePDF = async (contrat) => {
    try {
      setPdfLoading(true);
      
      // Charge les données du client spécifique à ce contrat
      const clientData = await UserService.fetchUserById(contrat.clientId);
      
      if (!clientData) {
        throw new Error("Client non trouvé");
      }

      switch(contrat.type) {
        case "VOITURE":
        case "MOTO":
          generatePDFVoitureMoto(contrat, clientData);
          break;
        case "VELO":
          generatePDFVelo(contrat, clientData);
          break;
        case "VACANCES":
          generateVacances(contrat, clientData);
          break;
        case "PROFESSIONNELLE":
          generatePDFProfessionnelle(contrat, clientData);
          break;
        default:
          console.error("Type de contrat non supporté");
      }
    } catch (err) {
      console.error("Erreur lors de la génération du PDF:", err);
      alert("Une erreur est survenue lors de la génération du PDF");
    } finally {
      setPdfLoading(false);
    }
  };

  if (loading) return <div className="text-center my-5">Chargement des contrats...</div>;
  if (error) return <div className="alert alert-danger">Erreur : {error.message}</div>;

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
                    <button className="btn btn-primary" onClick={() => handleGeneratePDF(contract)}>
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
