import React, { useState, useEffect } from 'react';
import UserService from '../../services/userService';
import { useAuth } from '../auth/AuthContext';

const MyContrat = () => {
	
	const [user, setUser] = useState(null);
	const [contratList, setContratList] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	
	const { getUser } = useAuth();
	const userDetails = getUser();
	
	useEffect(() => {
		   
		   UserService.fetchUserByEmail(userDetails.unsername).then(data => {
		     setUser(data);
		   });

		   setLoading(false);
		 }, []);
		 
	const fakeContracts = [
		     { id: "12345", type: "Santé", expiration: "2025-06-15" },
		     { id: "67890", type: "Auto", expiration: "2024-12-31" },
		     { id: "11223", type: "Habitation", expiration: "2026-03-20" },
		   ];
		 
	return (
		<div className="my-assistance-container">
			<h1 className="my-assistance-title">Vos contrats</h1>
			<table className="my-assistance-table">
				<thead>
					<tr>
						<td>Numéro du contrat</td>
						<td>Type de contrat</td>
						<td>Date d'expiration</td>
					</tr>
				</thead>
				<tbody>
					{fakeContracts.map((contract) => (
				    	<tr key={contract.id}>
				        	<td>{contract.id}</td>
				             <td>{contract.type}</td>
				             <td>{contract.expiration}</td>
				       	</tr>
				    ))}
				</tbody>
			</table>
		</div>
	);
	
}


export default MyContrat;