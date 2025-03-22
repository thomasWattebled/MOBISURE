class AuthService {
    async login(credentials) {
        return fetch(`http://localhost:8080/auth/login`, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username: credentials.username, password: credentials.password }),
        });
    }

    /**
     * Method used to log out a user.
     * Also removes user details from local AuthContext.
     * 
     * @param {AuthContext} authContext - AuthContext obtained with useAuth()
     */
    async logout(authContext) {
        try {
            await fetch(`http://localhost:8080/logout`, {
                method: 'GET',
                credentials: 'include',
            });

            // Remove user details from AuthContext
            authContext.userLogout();
        } catch {
            console.log("Fail to logout user");
        }
    }

    /**
     * Return current user string
     */
    async getCurrentUserAsString() {
        return fetch(`http://localhost:8080/user`, {
            method: 'GET',
            credentials: 'include',
        })
            .then((response) => response.text())
            .then((data) => {
                console.log("Received user " + data);
                return new Promise((resolve) => resolve(data));
            });
    }
}

// Assigne l'instance à une variable avant de l'exporter
const authServiceInstance = new AuthService();
export default authServiceInstance;
