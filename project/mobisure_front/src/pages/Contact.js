import React from 'react';
import '../assets/css/Contact.css';

const Contact = () => (
  <div className="page">
  
  	<h1>Nous contacter ?</h1>
	
	<div className='group'> 
		<h2>1. Par email : </h2>
		<p>Vous pouvez nous contacter à l'adresse mail suivante : contact@assurmob.com </p>
 	</div>
	
	<div className='group'>
		<h2>2. Par téléphone : </h2>
		<p>Vous pouvez contacter l'un de nos conseiller : +123 456 7890 </p>
	</div>
	
	<div className='group'>
		<h2>3. Directement sur le site :</h2>
		<p>Vous pouvez nous envoyer votre mail directement ici :</p>
		
		<form>
		
			<p>Votre adresse mail :</p>
			<input type='mail' name='mail'/>
			
			<p>Le sujet :</p>
			<input type='text' name='sujet'/>
			
			<p>Votre message :</p>
			<textarea id="message" name="messaage" rows="5" cols="33"></textarea>
			
			<button type='submit'>Envoyer</button>
		</form>
	</div>
	
	<div className='group'>
		<h2>4. Sur place :</h2>
		<p>Vous pouvez directement venir dans l'une de nos agence : </p>
    	<li>123 Insurance St, Assurance City</li>
	</div>
		
  </div>
);

export default Contact;
