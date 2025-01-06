const SERVER_URL = "http://localhost:8080";

class adminService {
	
	async fetchAllUsers(){
		return fetch(SERVER_URL + '/users', {
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
	
}

export default new adminService();