class AuthService {
	
    async login(credentials) {

		return fetch(
            `http://localhost:8080/auth/login` , 
            {
	        method: 'POST',
            credentials: 'include',
	        headers: {
               
                'Accept': 'application/json',
                'Content-Type': 'application/json',
               //  'Content-Type': 'application/x-www-form-urlencoded', 
	            },
            body: JSON.stringify( {username: credentials.username, password: credentials.password})
	        }	
        )
     }

     /**
      * Method used to logged out a user.
      * Also remove user details from local AuthContext.
      * 
      * @param {AuthContext} authContext - AuthContext obtained with useAuth()
      */
     async logout( authContext ) {

        try {
            await fetch(
                `http://localhost:8080/logout` , 
                {
                method: 'GET',
                credentials: 'include',
                }
            )
        
            // Remove userDetail from AuthContext
            authContext.userLogout()
        }
        catch {
            console.log("Fail to logout user")
        }
     }

     
     /**
      * Return current user string
      */
     async getCurrentUserAsString() {
        return fetch( 
            `http://localhost:8080/user`,
            {
                method: 'GET',
                credentials: 'include',
            }
        )
        .then( response => response.text() )
        .then( data => {
             console.log( "receive user " + data )
            return new Promise( (resolve, reject) => resolve(data) )
        }  )
     }
}

export default new AuthService();

