import React, { useState, useEffect } from "react";
import UserService from "../../services/userService";
import { useAuth } from "../auth/AuthContext";
import contratService from "../../services/contratService";
import { 
  generatePDFVelo, 
  generatePDFVoitureMoto, 
  generateVacances, 
  generatePDFProfessionnelle 
} from "../pdf/ContratPDF";

const MyContrat = () => {
  const [contratList, setContratList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const { getUser } = useAuth();
  const userDetails = getUser();
  const serviceContrat = new contratService();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const currentUser = await UserService.fetchUserByEmail(userDetails.unsername);
        const contrats = await serviceContrat.getMyContrat(currentUser.id);
        setContratList(Array.isArray(contrats) ? contrats : []);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [userDetails.username]);

  const handleGeneratePDF = async (contrat) => {
    try {
      setLoading(true);
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
      console.error("Erreur génération PDF:", err);
      alert("Erreur lors de la génération du PDF");
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <div>Chargement en cours...</div>;
  if (error) return <div>Erreur: {error.message}</div>;

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
                    <button onClick={() => handleGeneratePDF(contract)}>
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

export default MyContrat;
