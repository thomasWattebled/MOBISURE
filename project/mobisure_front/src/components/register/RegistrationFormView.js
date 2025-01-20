import React, { useState } from 'react';
import { FiEye, FiEyeOff } from 'react-icons/fi'; // Importer les icônes de réacteurs
import FormField from './FormField';
import SuccessModal from './SuccessModal';

const RegistrationFormView = ({
  formData,
  handleChange,
  handleSubmit,
  isModalVisible,
  setModalVisible,
  error,
}) => {
  const [showPassword, setShowPassword] = useState(false); // Pour afficher/cacher le mot de passe
  const [showConfirmPassword, setShowConfirmPassword] = useState(false); // Pour afficher/cacher la confirmation du mot de passe

  const handleModalClose = () => {
    setModalVisible(false);
  };

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const toggleConfirmPasswordVisibility = () => {
    setShowConfirmPassword(!showConfirmPassword);
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h2 className="card-title text-center" style={{ fontWeight: 'bold' }}>
                INSCRIPTION
              </h2>
              {error && <div className="alert alert-danger">{error}</div>}
              <form onSubmit={handleSubmit}>
                {/* Sexe */}
                <div className="mb-3">
                  <label className="form-label" style={{ fontWeight: 'bold', fontSize: '1.1rem', color: 'blue' }}>
                    Sexe :
                  </label>
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

                {/* Champs de formulaire */}
                <FormField
                  id="nom"
                  label={<span style={{ fontWeight: 'bold', color: 'blue' }}>Nom :</span>}
                  type="text"
                  name="nom"
                  value={formData.nom}
                  placeholder="Entrez votre nom"
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="prenom"
                  label={<span style={{ fontWeight: 'bold', color: 'blue' }}>Prénom :</span>}
                  type="text"
                  name="prenom"
                  value={formData.prenom}
                  placeholder="Entrez votre prénom"
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="mail"
                  label={<span style={{ fontWeight: 'bold', color: 'blue' }}>E-mail :</span>}
                  type="email"
                  name="mail"
                  value={formData.mail}
                  placeholder="Entrez votre e-mail"
                  handleChange={handleChange}
                  required
                />
                {/* Mot de passe et confirmation */}
                <div className="mb-3">
                  <label className="form-label" style={{ fontWeight: 'bold', fontSize: '1.1rem', color: 'blue' }}>
                    Mot de passe :
                  </label>
                  <div className="d-flex">
                    <input
                      type={showPassword ? 'text' : 'password'}
                      className="form-control me-2"
                      id="mdp"
                      name="mdp"
                      value={formData.mdp}
                      placeholder="Entrez votre mot de passe"
                      onChange={handleChange}
                      required
                    />
                    <i
                      className="password-toggle"
                      onClick={togglePasswordVisibility}
                    >
                      {showPassword ? <FiEyeOff /> : <FiEye />}
                    </i>
                  </div>
                </div>

                {/* Confirmation du mot de passe */}
                <div className="mb-3">
                  <label className="form-label" style={{ fontWeight: 'bold', fontSize: '1.1rem', color: 'blue' }}>
                    Confirmation du mot de passe :
                  </label>
                  <div className="d-flex">
                    <input
                      type={showConfirmPassword ? 'text' : 'password'}
                      className="form-control me-2"
                      id="confirmMdp"
                      name="confirmMdp"
                      value={formData.confirmMdp}
                      placeholder="Confirmer le mot de passe"
                      onChange={handleChange}
                      required
                    />
                    <i
                      className="password-toggle"
                      onClick={toggleConfirmPasswordVisibility}
                    >
                      {showConfirmPassword ? <FiEyeOff /> : <FiEye />}
                    </i>
                  </div>
                </div>

                {/* Date de naissance (format JJ/MM/AAAA) */}
                <div className="mb-3">
                  <label className="form-label" style={{ fontWeight: 'bold', fontSize: '1.1rem', color: 'blue' }}>
                    Date de naissance :
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    name="dateNaissance"
                    value={formData.dateNaissance}
                    onChange={handleChange}
                    placeholder="JJ/MM/AAAA"
                    maxLength="10"
                    pattern="\d{2}/\d{2}/\d{4}"
                    required
                  />
                </div>

                <FormField
                  id="adresse"
                  label={<span style={{ fontWeight: 'bold', color: 'blue' }}>Adresse :</span>}
                  type="text"
                  name="adresse"
                  value={formData.adresse}
                  placeholder="Entrez votre adresse"
                  handleChange={handleChange}
                  required
                />
                <FormField
                  id="telephone"
                  label={<span style={{ fontWeight: 'bold', color: 'blue' }}>Numéro de téléphone :</span>}
                  type="tel"
                  name="telephone"
                  value={formData.telephone}
                  placeholder="Entrez votre numéro de téléphone"
                  handleChange={handleChange}
                  pattern="[0-9]{10}"
                  required
                />
                <button type="submit" className="btn btn-primary w-100 mt-3">
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
