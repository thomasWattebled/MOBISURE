import React, { useState } from 'react';

const RegistrationForm = () => {
  const [formData, setFormData] = useState({
    nom: '',
	prenom: '',
    mail: '',
    mdp: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  
    fetch('http://localhost:8080/users/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.text())
      .then((data) => {
        alert(data); // Affiche la réponse du backend
      })
      .catch((error) => {
        console.error('Erreur :', error);
      });
  };
  

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h2 className="card-title text-center">Inscription</h2>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="nom" className="form-label">
                    Nom :
                  </label>
                  <input
                    type="text"
                    id="nom"
                    name="nom"
                    value={formData.nom}
                    onChange={handleChange}
                    className="form-control"
                    placeholder="Entrez votre nom"
                    required
                  />
                </div>
				<div className="mb-3">
					<label htmlFor="prenom" className="form-label">
					Prénom :
					</label>
					<input
						type="text"
						id="prenom"
						name="prenom"
						value={formData.prenom}
						onChange={handleChange}
						className="form-control"
						placeholder="Entrez votre prénom"
						required
						/>
				</div>
                <div className="mb-3">
                  <label htmlFor="mail" className="form-label">
                    mail :
                  </label>
                  <input
                    type="mail"
                    id="mail"
                    name="mail"
                    value={formData.mail}
                    onChange={handleChange}
                    className="form-control"
                    placeholder="Entrez votre mail"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="mdp" className="form-label">
                    Mot de passe :
                  </label>
                  <input
                    type="password"
                    id="mdp"
                    name="mdp"
                    value={formData.mdp}
                    onChange={handleChange}
                    className="form-control"
                    placeholder="Entrez votre mot de passe"
                    required
                  />
                </div>
                <button type="submit" className="btn btn-primary w-100">
                  S'inscrire
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegistrationForm;