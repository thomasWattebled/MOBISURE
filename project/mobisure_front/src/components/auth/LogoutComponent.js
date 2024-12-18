import React from 'react';
import { Navigate } from 'react-router-dom';
import AuthService from '../../services/AuthService';
import { useAuth } from './AuthContext';

/**
 * A component used to perform logout.
 * 
 * This component performs the logout, and then redirects to the home page.
 * 
 * @returns JSX
 */
const LogoutComponent = () => {
   const Auth = useAuth();
   AuthService.logout(Auth);

   return <Navigate to="/" />;
};

export default LogoutComponent; 
