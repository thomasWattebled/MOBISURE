import React, { useState } from 'react';
import {  Link } from 'react-router-dom';
import FormField from '../register/FormField';
import userService from '../../services/userService';

import 'bootstrap/dist/css/bootstrap.min.css';

const MdpForm  = () => {
	
	const [formData, setFormData] = useState({
	    mail: '',
	    mdp: '',
	    date: '',
	  });
	  
	  const handleChange = (e) => {
	      const { name, value } = e.target;
	      setFormData({ ...formData, [name]: value });
	    };
		
		const handleSubmit = (e) => {
		    e.preventDefault();
		   	userService.fetchUserChangeMdp(formData);
		  };
	
	  
	  return (
	      <div className="container mt-5">
	        <div className="row justify-content-center">
	          <div className="col-md-6">
	            <div className="card">
	              <div className="card-body">
	                <h2 className="card-title text-center">Réinitialisation du mot de passe</h2>
	                <form onSubmit={handleSubmit}>
					<FormField
						id="dateNaissance"
					    label="Date de naissance :"
					    type="date"
					    name="date"
					    value={formData.date}
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
	                  <button type="submit" className="btn btn-primary w-100">
	                    Valider
	                  </button>
	                </form>
	              </div>
	            </div>
	          </div>
	        </div>
	        
	      </div>
	    );
}

export default MdpForm;