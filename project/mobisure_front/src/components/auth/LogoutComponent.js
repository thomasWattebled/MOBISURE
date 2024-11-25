import React, { useState } from 'react';
import { useNavigate, Link, Navigate } from 'react-router-dom';
import AuthService from './AuthService';
import { useAuth } from './AuthContext';

/**
 * A component used to perform logout.
 * 
 * This component perform the logout, and then redirect to home page.
 * 
 * @returns JSX
 */
export const LogoutComponent = () => {

   const Auth = useAuth()

   AuthService.logout(Auth )

   return (
    <Navigate to="/" />
   )

}
