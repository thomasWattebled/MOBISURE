import React from 'react';
import FormField from './FormField';

const RegistrationFormView = ({ formData, handleChange, handleSubmit }) => {
  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h2 className="card-title text-center">Inscription</h2>
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
                        checked={formData.sexe === 'Homme'}
                        onChange={handleChange}
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
                        checked={formData.sexe === 'Femme'}
                        onChange={handleChange}
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
                  value={formData.nom}
                  placeholder="Entrez votre nom"
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="prenom"
                  label="Prénom :"
                  type="text"
                  name="prenom"
                  value={formData.prenom}
                  placeholder="Entrez votre prénom"
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="mail"
                  label="Mail :"
                  type="email"
                  name="mail"
                  value={formData.mail}
                  placeholder="Entrez votre mail"
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="mdp"
                  label="Mot de passe :"
                  type="password"
                  name="mdp"
                  value={formData.mdp}
                  placeholder="Entrez votre mot de passe"
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="dateNaissance"
                  label="Date de naissance :"
                  type="date"
                  name="dateNaissance"
                  value={formData.dateNaissance}
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="adresse"
                  label="Adresse :"
                  type="text"
                  name="adresse"
                  value={formData.adresse}
                  placeholder="Entrez votre adresse"
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="telephone"
                  label="Numéro de téléphone :"
                  type="tel"
                  name="telephone"
                  value={formData.telephone}
                  placeholder="Entrez votre numéro de téléphone"
                  handleChange={handleChange}
                  pattern="[0-9]{10}"
                  required
                />
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

export default RegistrationFormView;
