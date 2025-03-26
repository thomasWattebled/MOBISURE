// ContratService.js

class contratService {

	async getAllContrat(){
		return await fetch(`http://localhost:8082/contrat/all/assurance`,{
			method: "GET",
	    	headers: { "Content-Type": "application/json" },		
		})
		.then((response) => response.json())
		.then((data) => {
			return data;
		})
		.catch((error) => {
			console.error("Erreur :", error);  // En cas d'erreur, l'afficher
		});
	}
	
	// Fonction pour obtenir un devis en envoyant les données du formulaire
	async getDevis(url, formData, setPrice){
	  return await fetch(`http://localhost:8082/contrat/${url}`, {
	    method: "POST",
	    headers: { "Content-Type": "application/json" },
	    body: JSON.stringify(formData),
	  })
	    .then((response) => response.text())  // Attendre la réponse en texte
	    .then((data) => {
	      console.log("Réponse du serveur :", data);
	      setPrice(parseFloat(data).toFixed(2));  // Mettre à jour le prix avec la réponse du serveur
	    })
	    .catch((error) => {
	      console.error("Erreur :", error);  // En cas d'erreur, l'afficher
	    });
	}
	
	
	async createContrat(url, formData){
		  return await fetch(`http://localhost:8082/contrat/${url}`, {
		    method: "POST",
		    headers: { "Content-Type": "application/json" },
		    body: JSON.stringify(formData),
		  })
		    .then((response) => response.text())  // Attendre la réponse en texte
		    .then((data) => {
		      console.log("Réponse du serveur :", data);
		    })
		    .catch((error) => {
		      console.error("Erreur :", error);  // En cas d'erreur, l'afficher
		    });
		}
		
		async getMyContrat(clientId){
				  return await fetch(`http://localhost:8082/contrat/my/assurance/${clientId}`, {
				    method: "GET",
				    headers: { "Content-Type": "application/json" },
				  })
				    .then((response) => response.json())  // Attendre la réponse en json
				    .then((data) => {
					  return data;
				    })
				    .catch((error) => {
				      console.error("Erreur :", error);  // En cas d'erreur, l'afficher
				    });
				}

	async getContrat(numDossier,setContrat){
		return await fetch(`http://localhost:8082/contrat/assurance/dossier${numDossier}`, {
			method: "GET",
			headers: { "Content-Type": "application/json" },
		}).then((response) => response.json())
		.then((data) => { setContrat(data); })
		.catch((error) => {
			console.error("Erreur :", error);  // En cas d'erreur, l'afficher
		});
	}
}

export default contratService;