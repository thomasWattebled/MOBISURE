const SERVER_URL = "http://localhost:8080";

class adminService {
	
	async fetchAllUsers(){
		return await fetch(SERVER_URL + '/users', {
			credentials: 'include',  // Envoie les cookies d'authentification avec la requête
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(response => response.json());
		
	}
	
	async updateUserRoles(id,role){
		return await fetch(SERVER_URL + '/users/updateRole', {
			method: 'POST',
			credentials: 'include',  // Envoie les cookies d'authentification avec la requête
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
			            userId: id, // L'ID ou les informations de l'utilisateur
			            roles: role   // La liste ou le rôle unique
			        }),
		});
	}
	
	async deleteById(id){
		return await fetch(SERVER_URL + '/users/delete/'+id, {
			method: 'DELETE',
			credentials: 'include',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}

		})
	}
	
	async getUserById(id){
		return await fetch(SERVER_URL + '/users/'+id,{
			credentials: 'include',
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(response => response.json()
	)};
	
	async updateUser(id, userData) {
	  const response = await fetch(`http://localhost:8080/users/${id}`, {
	    method: 'PUT',
	    credentials: 'include',
	    headers: {
	      'Content-Type': 'application/json',
	    },
	    body: JSON.stringify(userData),
	  });

	  // Si la réponse est OK, retourne le JSON, sinon lance une erreur
	  if (response.ok) {
	    return await response.json();  // Retourne le JSON en cas de succès
	  } else {
	    throw new Error('Erreur lors de la mise à jour de l\'utilisateur');  // Si le statut n’est pas OK, lance une erreur
	  }
	}
	
}

export default new adminService();