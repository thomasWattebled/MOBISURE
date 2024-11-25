import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../service/AuthService';

import LoginForm from './LoginForm';

export const LoginComponent = () => {
   
    const navigate = useNavigate();

    const [message, setMessage] = useState('');

    const handleLogin = async (username, password) => {
        try {
            const userData = await AuthService.login({ username, password });
            console.log('Login successful:', userData);
           
            navigate('/home'); // Navigate to the protected home page
        } catch (error) {
            console.error('Login failed:', error.message);
            setMessage(error.message || 'Invalid username or password.');
        }
    };

    return <LoginForm handleLogin={handleLogin} message={message} />;
};

export default LoginComponent;
