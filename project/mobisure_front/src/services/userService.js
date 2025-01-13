const SERVER_URL = "http://localhost:8080";

class UserService {
	
  async fetchUserByEmail(mail) {
    return fetch(`${SERVER_URL}/getUserEmail`, {
	  method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
      },
	  body: JSON.stringify({
	  			            email: mail,
	  			            
	  			        }),
    }).then(response => {
      if (!response.ok) {
        throw new Error('Erreur réseau');
      }
      return response.json();
    });
  }
  
  
  async fetchUserChangeMdp(formData) {
  		return fetch(SERVER_URL + '/users/changeMdp', 	{
		  method: 'POST',
	      credentials: 'include',
	      headers: {
	        'Content-Type': 'application/json',
	      },
		  body: JSON.stringify(formData),
	    }).then(response => {
	      if (!response.ok) {
	        throw new Error('Erreur réseau');
	      }
	      return response.json();
	    });
  	} 
  
}

	

export default new UserService();
