import React, { useEffect } from 'react';
import { Navigate } from 'react-router-dom';

export const LogoutComponent = () => {


    useEffect(() => {
        const performLogout = async () => {
            
        
        };
        performLogout();
    });

    return <Navigate to="/" />;
};

export default LogoutComponent;
