import React, { useState, useEffect } from "react";
import UserService from "../../services/userService";
import { useAuth } from "../auth/AuthContext";
import contratService from "../../services/contratService";

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
      .finally(() => setLoading(false)); // On arrête le chargement une fois la requête terminée
  }, []);

  useEffect(() => {
    if (user) {
      setLoading(true);
      serviceContrat
        .getMyContrat(user.id)
        .then((data) => {
          setContratList(Array.isArray(data) ? data : []); // Vérification de la structure des données      
		 })
        .catch((err) => setError(err))
        .finally(() => setLoading(false));
    }
  }, [user]);


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
            </tr>
          </thead>
          <tbody>
            {contratList.map((contract) => (
              <tr key={contract.id}>
			  	<td>{contract.numDossier}</td>
                <td>{contract.type}</td>
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
