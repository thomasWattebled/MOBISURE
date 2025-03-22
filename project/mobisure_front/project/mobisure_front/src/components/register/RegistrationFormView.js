import React from 'react';
import FormField from './FormField';
import SuccessModal from './SuccessModal';
import '../../assets/css/register.css';
import '../../assets/css/form.css';

const RegistrationFormView = ({ formData, handleChange, handleSubmit, isModalVisible, setModalVisible }) => {

  const handleModalClose = () => {
    setModalVisible(false); // Fermer le modal
  };

  const isFormValid = () => {
    return (
      formData.nom.trim() &&
      formData.prenom.trim() &&
      formData.mail.match(/^\S+@\S+\.\S+$/) &&
      formData.mdp.length >= 6 &&
      formData.dateNaissance &&
      formData.adresse.trim() &&
      formData.telephone.match(/^\d{10}$/) &&
      formData.sexe
    );
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card form-container">
            <div className="card-body">
              <h2 className="card-title text-center">Inscription</h2>
              <form onSubmit={handleSubmit}>
                {/* Sexe */}
                <div className="mb-3">
                  <label className="form-label">Sexe :</label>
                  <div className="d-flex gap-3">
                    <div className="form-check">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="sexe"
                        id="homme"
                        value="Homme"
                        checked={formData.sexe === 'Homme'}
                        onChange={handleChange}
                        required
                      />
                      <label className="form-check-label" htmlFor="homme">Homme</label>
                    </div>
                    <div className="form-check">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="sexe"
                        id="femme"
                        value="Femme"
                        checked={formData.sexe === 'Femme'}
                        onChange={handleChange}
                        required
                      />
                      <label className="form-check-label" htmlFor="femme">Femme</label>
                    </div>
                  </div>
                </div>

                {/* Champs de formulaire */}
                <FormField id="nom" label="Nom :" type="text" name="nom" value={formData.nom} placeholder="Entrez votre nom" handleChange={handleChange} required />
                <FormField id="prenom" label="Prénom :" type="text" name="prenom" value={formData.prenom} placeholder="Entrez votre prénom" handleChange={handleChange} required />
                <FormField id="mail" label="Mail :" type="email" name="mail" value={formData.mail} placeholder="Entrez votre mail" handleChange={handleChange} required pattern="^\S+@\S+\.\S+$" />
                <FormField id="mdp" label="Mot de passe :" type="password" name="mdp" value={formData.mdp} placeholder="Entrez votre mot de passe (6 caractères minimum)" handleChange={handleChange} required minLength="6" />
                <FormField id="dateNaissance" label="Date de naissance :" type="date" name="dateNaissance" value={formData.dateNaissance} handleChange={handleChange} required />
                <FormField id="adresse" label="Adresse :" type="text" name="adresse" value={formData.adresse} placeholder="Entrez votre adresse" handleChange={handleChange} required />
                <FormField id="telephone" label="Numéro de téléphone :" type="tel" name="telephone" value={formData.telephone} placeholder="Entrez votre numéro de téléphone" handleChange={handleChange} required pattern="^\d{10}$" />

                {/* Bouton d'inscription */}
                <button type="submit" className="btn btn-primary w-100" disabled={!isFormValid()}>
                  S'inscrire
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
      <SuccessModal show={isModalVisible} onClose={handleModalClose} />
    </div>
  );
};

export default RegistrationFormView;
