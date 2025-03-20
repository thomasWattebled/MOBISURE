import React, { useState, useEffect } from "react";
import UserService from "../../services/userService";
import { useAuth } from "../auth/AuthContext";
import contratService from "../../services/contratService";
import { jsPDF } from "jspdf";

const MyContrat = () => {
  const [user, setUser] = useState(null);
  const [contratList, setContratList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const { getUser } = useAuth();
  const userDetails = getUser();
  const serviceContrat = new contratService();

  useEffect(() => {
    UserService.fetchUserByEmail(userDetails.unsername)
      .then((data) => setUser(data))
      .catch((err) => setError(err))
      .finally(() => setLoading(false));
  }, []);

  useEffect(() => {
    if (user) {
      setLoading(true);
      serviceContrat
        .getMyContrat(user.id)
        .then((data) => {
          setContratList(Array.isArray(data) ? data : []);
        })
        .catch((err) => setError(err))
        .finally(() => setLoading(false));
    }
  }, [user]);

  const generatePDF = (contrat) => {
    const doc = new jsPDF();

    doc.setFont("helvetica", "bold");
    doc.text("Contrat Voiture", 20, 20);

    doc.setFont("helvetica", "normal");
    doc.text(`Numéro de dossier : ${contrat.numDossier}`, 20, 40);
    doc.text(`Voiture : ${contrat.marque} ${contrat.modele} - ${contrat.plaque}`, 20, 60);
    doc.text(`Type de contrat : ${contrat.type}`, 20, 100);

    doc.save(`Contrat_${contrat.numDossier}.pdf`);
  };

  if (loading) return <p>Chargement des contrats...</p>;
  if (error) return <p>Erreur : {error.message}</p>;

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
                  <button onClick={() => generatePDF(contract)}>
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
