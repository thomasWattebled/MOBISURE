import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

class AuthService {
    constructor() {
        this.axiosInstance = axios.create({
            baseURL: API_BASE_URL,
            withCredentials: true, // Enable cookies for session handling
            headers: {
                'Content-Type': 'application/json',
            },
        });
    }

    // Centralized error handling
    handleError(error) {
        const message = error.response?.data?.message || error.message || 'An error occurred';
        console.error('API Error:', message);
        throw new Error(message);
    }

    // Login method
    async login(credentials) {
        try {
            console.log('Sending login credentials:', credentials);
            const response = await this.axiosInstance.post('/auth/login', credentials);
            console.log('Login successful:', response.data);
            return response.data;
        } catch (error) {
            console.error('Login failed:', error.response?.data?.message || error.message);
            this.handleError(error);
        }
    }

    // Logout method
    async logout() {
        try {
            await this.axiosInstance.post('/auth/logout');
            console.log('Logout successful');
        } catch (error) {
            console.error('Logout failed:', error.message);
            this.handleError(error);
        }
    }
    

    // Get current user
    async getCurrentUser() {
        try {
            const response = await this.axiosInstance.get('/auth/user');
            return response.data;
        } catch (error) {
            this.handleError(error);
        }
    }
}

export default new AuthService();
