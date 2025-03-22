import React, { useState, useEffect } from 'react';

const AddPeople = ({ role, numDossier }) => {
  const [listUser, setListUser] = useState([]);
  const [userAccess, setUserAccess] = useState({}); // Pour stocker l'état des accès des utilisateurs
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [filters, setFilters] = useState({ nom: "", prenom: "", mail: "" });
  const [showFilters, setShowFilters] = useState(false); // État pour afficher/masquer les filtres

  useEffect(() => {
    if (role) {
      fetch(`http://localhost:8080/users/byRole?role=${role}`, {
        method: "GET",
        credentials: "include",
        headers: {
          "Content-Type": "application/json"
        }
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Erreur de récupération des données');
          }
          return response.json();
        })
        .then(data => {
          setListUser(data);
          // Vérifiez les accès des utilisateurs après les avoir récupérés
          data.forEach(user => {
            checkUserAccess(user.id); // Vérifier si chaque utilisateur a un accès
          });
        })
        .catch(error => {
          setError(error.message);
        })
        .finally(() => setLoading(false));
    }
  }, [role]);

  const checkUserAccess = async (idUser) => {
    try {
      const response = await fetch(`http://localhost:8081/access/myFolder?idUser=${idUser}`, {
        method: 'GET',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      if (response.ok) {
        const data = await response.json();
        // Mettez à jour l'état avec le résultat pour cet utilisateur
        setUserAccess(prevState => ({
          ...prevState,
          [idUser]: data.some(item => item.numDossier === numDossier) // Vérifie si l'utilisateur a accès
        }));
      }
    } catch (error) {
      alert("Erreur lors de la vérification de l'accès.");
    }
  };

  const handleAddRemove = async (idPeople) => {
    const isInFolder = userAccess[idPeople];  // Utilisez l'état `userAccess` pour vérifier
    if (isInFolder) {
      // Code pour supprimer l'accès
      removeAccess(idPeople);
    } else {
      // Code pour ajouter l'accès
      addPeople(idPeople);
    }
  };

  const addPeople = async (idPeople) => {
    try {
      await fetch(`http://localhost:8081/access/addAccess?idAssistance=${numDossier}&idUser=${idPeople}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      // Mettez à jour l'état pour l'utilisateur après l'ajout
      setUserAccess(prevState => ({
        ...prevState,
        [idPeople]: true // L'utilisateur a désormais l'accès
      }));
    } catch (error) {
      alert("Erreur");
    }
  };

  const removeAccess = async (idPeople) => {
    try {
      await fetch(`http://localhost:8081/access/removeAccess?idAssistance=${numDossier}&idUser=${idPeople}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      // Mettez à jour l'état pour l'utilisateur après la suppression
      setUserAccess(prevState => ({
        ...prevState,
        [idPeople]: false // L'utilisateur n'a plus l'accès
      }));
    } catch (error) {
      alert("Erreur");
    }
  };

  // Filtrage des utilisateurs selon les critères
  const filteredList = listUser.filter(user =>
    (!filters.nom || user.nom.toLowerCase().includes(filters.nom.toLowerCase())) &&
    (!filters.prenom || user.prenom.toLowerCase().includes(filters.prenom.toLowerCase())) &&
    (!filters.mail || user.mail.toLowerCase().includes(filters.mail.toLowerCase()))
  );

  return (
    <div>
      <button className="btn btn-primary mb-3" onClick={() => setShowFilters(!showFilters)}>
        {showFilters ? "Masquer les filtres" : "Afficher les filtres"}
      </button>
      {showFilters && (
        <div className="mb-3">
          <p>Nom :
            <input
              type="text"
              placeholder="Nom"
              value={filters.nom}
              onChange={(e) => setFilters({ ...filters, nom: e.target.value })}
              className="form-control mb-2"
            />
          </p>
          <p>Prénom :
            <input
              type="text"
              placeholder="Prénom"
              value={filters.prenom}
              onChange={(e) => setFilters({ ...filters, prenom: e.target.value })}
              className="form-control mb-2"
            />
          </p>
          <p>Adresse mail :
            <input
              type="text"
              placeholder="Adresse mail"
              value={filters.mail}
              onChange={(e) => setFilters({ ...filters, mail: e.target.value })}
              className="form-control mb-2"
            />
          </p>
        </div>
      )}
      <table>
        <thead>
          <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Adresse mail</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {filteredList.map(user => (
            <tr key={user.id}>
              <td>{user.nom}</td>
              <td>{user.prenom}</td>
              <td>{user.mail}</td>
              <td>
                <button onClick={() => handleAddRemove(user.id)}>
                  {userAccess[user.id] ? 'Supprimer' : 'Ajouter'}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AddPeople;



