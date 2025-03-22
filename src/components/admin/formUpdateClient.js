import React, { useState, useEffect } from 'react';
import FormField from '../register/FormField';
import { useParams } from "react-router-dom";
import adminService from '../../services/adminService';
import { useNavigate } from 'react-router-dom';


const FormUpdateClient = () => {
	
	const { id } = useParams();
	const [user, setUser] = useState(null);
	const navigate = useNavigate();
	
	const handleChange = (e) => {
	  const { name, value } = e.target;
	  setFormData({
	    ...formData,
	    [name]: value, // Met à jour le champ spécifique du formulaire
	  });
	};
	
	const handleSubmit = (e) => {
	  e.preventDefault();

	  const updatedUserData = {
	    ...formData, // Données mises à jour du formulaire
	  };

	  // Envoie des données au backend pour mettre à jour l'utilisateur
	  adminService.updateUser(id, updatedUserData)
	    .then((response) => {
	      console.log(response);  // Affiche la réponse du backend (le message de succès)
	      // Gérer la réussite de la mise à jour
	      console.log('Utilisateur mis à jour avec succès');
	      navigate("/pageUser");  // Rediriger après succès
	    })
	    .catch((error) => {
	      // Gérer les erreurs
	      console.error('Erreur lors de la mise à jour :', error.message);  // Affiche le message d'erreur
	    });
	};

	
	const formatDate = (date) => {
	  const d = new Date(date);
	  const year = d.getFullYear();
	  const month = String(d.getMonth() + 1).padStart(2, '0');
	  const day = String(d.getDate()).padStart(2, '0');
	  return `${year}-${month}-${day}`;
	};
	
	const [formData, setFormData] = useState({
	  sexe: "",
	  nom: "",
	  prenom: "",
	  mail: "",
	  dateNaissance: "",
	  adresse: "",
	  telephone: ""
	});
	
	useEffect(() => {
	  adminService.getUserById(id).then((data) => {
	    setUser(data);
	    setFormData({
	      sexe: data.sexe || "",
	      nom: data.nom || "",
	      prenom: data.prenom || "",
	      mail: data.mail || "",
	      dateNaissance: data.dateNaissance || "",
	      adresse: data.adresse || "",
	      telephone: data.telephone || ""
	    });
	  });
	}, [id]);
	
	const updatedUser = {
	      ...user,
	      ...formData, // FormData contient les données du formulaire
	 };
	 
	

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h2 className="card-title text-center">Modification des informations du compte</h2>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label className="form-label">Sexe :</label>
                  <div>
                    <div className="form-check form-check-inline">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="sexe"
                        id="homme"
                        value="Homme"
                        onChange={handleChange}
						checked={formData.sexe === "Homme"}
                      />
                      <label className="form-check-label" htmlFor="homme">
                        Homme
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="sexe"
                        id="femme"
                        value="Femme"
                        onChange={handleChange}
						checked={formData.sexe === "Femme"}
                      />
                      <label className="form-check-label" htmlFor="femme">
                        Femme
                      </label>
                    </div>
                  </div>
                </div>
                <FormField
                  id="nom"
                  label="Nom :"
                  type="text"
                  name="nom"
                  placeholder="Entrez votre nom"
				  value={formData.nom}
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="prenom"
                  label="Prénom :"
                  type="text"
                  name="prenom"
                  placeholder="Entrez votre prénom"
				  value={formData.prenom}
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="mail"
                  label="Mail :"
                  type="email"
                  name="mail"
                  placeholder="Entrez votre mail"
                  handleChange={handleChange}
				  value={formData.mail}
                  required
                />
                <FormField
                  id="dateNaissance"
                  label="Date de naissance :"
                  type="date"
                  name="dateNaissance"
                  handleChange={handleChange}
				  value={user ? formatDate(formData.dateNaissance) : ''}
                  required
                />
                <FormField
                  id="adresse"
                  label="Adresse :"
                  type="text"
                  name="adresse"
                  placeholder="Entrez votre adresse"
                  handleChange={handleChange}
				  value={formData.adresse}
                  required
                />
                <FormField
                  id="telephone"
                  label="Numéro de téléphone :"
                  type="tel"
                  name="telephone"
                  placeholder="Entrez votre numéro de téléphone"
                  handleChange={handleChange}
				  value={formData.telephone}
                  pattern="[0-9]{10}"
                  required
                />
                <button type="submit" className="btn btn-primary w-100">
                  Valider la modification
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FormUpdateClient;
