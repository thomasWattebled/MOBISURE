import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import UserService from '../../services/userService';
import { useAuth } from '../auth/AuthContext';

const MyAssistance = () => {
	
	const [user, setUser] = useState(null);
	const [loading, setLoading] = useState(true);
	const { getUser } = useAuth();
	const userDetails = getUser();
	
	useEffect(() => {
	   
	   UserService.fetchUserByEmail(userDetails.unsername).then(data => {
	     setUser(data);
	   });

	   setLoading(false);
	 }, []);
	
	return (
		<div>
			<h1>Vos demandes d'assistances</h1>
		</div>
	);
};

export default MyAssistance;