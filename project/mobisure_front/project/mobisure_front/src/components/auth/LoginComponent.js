import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../../services/AuthService'
import { useAuth } from './AuthContext';
import LoginForm from './LoginForm'; // Assurez-vous que ce composant existe

export const LoginComponent = () => {
    const { userLogin } = useAuth();  // Utilisez userLogin fourni par votre contexte Auth
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);  // État de chargement pour une meilleure UX
    const navigate = useNavigate();

    // Fonction pour gérer la connexion
    const handleLogin = async (username, password) => {
        setLoading(true);  // Active l'indicateur de chargement
        try {
            const response = await AuthService.login({ username, password });

            if (!response.ok) {
                setMessage('Invalid credentials');
                setLoading(false);
                return;
            }

            const userDetails = await response.json();
            
            if (userDetails !== null) {
                userLogin(userDetails);  // Authentification réussie
                setUsername('');  // Réinitialisation des champs
                setPassword('');
                setLoading(false);
                navigate('/home');  // Redirection vers la page d'accueil après connexion
            } else {
                setMessage('Invalid credentials - null details');
                setLoading(false);
            }
        } catch (error) {
            setMessage('Error during login');
            setLoading(false);
            console.error("Login error:", error);
        }
    };

    return (
        <div>
            <LoginForm 
                handleLogin={handleLogin} 
                message={message} 
                username={username} 
                password={password} 
                setUsername={setUsername} 
                setPassword={setPassword} 
                loading={loading} 
            />
        </div>
    );
};

export default LoginComponent;








