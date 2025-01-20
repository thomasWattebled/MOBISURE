class AuthService {
    /**
     * Authenticates a user and retrieves a token.
     * @param {Object} credentials - The user's login credentials (username and password).
     * @returns {Promise<Object>} - The response object or an error.
     */
    async login(credentials) {
        try {
            const response = await fetch(`http://localhost:8080/auth/login`, {
                method: 'POST',
                credentials: 'include',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: credentials.username,
                    password: credentials.password,
                }),
            });

            if (!response.ok) {
                throw new Error('Failed to login. Please check your credentials.');
            }

            return await response.json();
        } catch (error) {
            console.error('Error during login:', error.message);
            throw error;
        }
    }

    /**
     * Logs out the user by calling the logout endpoint and clearing the context.
     * @param {Object} authContext - The AuthContext to clear user details.
     */
    async logout(authContext) {
        try {
            const response = await fetch(`http://localhost:8080/logout`, {
                method: 'GET',
                credentials: 'include',
            });

            if (!response.ok) {
                throw new Error('Failed to logout.');
            }

            authContext.userLogout();
        } catch (error) {
            console.error('Error during logout:', error.message);
        }
    }

    /**
     * Retrieves the current authenticated user's details as a string.
     * @returns {Promise<string>} - The user details or an error.
     */
    async getCurrentUserAsString() {
        try {
            const response = await fetch(`http://localhost:8080/user`, {
                method: 'GET',
                credentials: 'include',
            });

            if (!response.ok) {
                throw new Error('Failed to fetch user details.');
            }

            const data = await response.text();
            console.log('Received user:', data);
            return data;
        } catch (error) {
            console.error('Error fetching user details:', error.message);
            throw error;
        }
    }

    /**
     * Retrieves the current authenticated user's details as an object.
     * @returns {Promise<Object>} - The user details parsed as JSON or an error.
     */
    async getCurrentUser() {
        try {
            const response = await fetch(`http://localhost:8080/user`, {
                method: 'GET',
                credentials: 'include',
            });

            if (!response.ok) {
                throw new Error('Failed to fetch user details.');
            }

            const data = await response.json();
            console.log('Received user as JSON:', data);
            return data;
        } catch (error) {
            console.error('Error fetching user details as JSON:', error.message);
            throw error;
        }
    }
}

// Export a singleton instance of AuthService
const authServiceInstance = new AuthService();
export default authServiceInstance;
