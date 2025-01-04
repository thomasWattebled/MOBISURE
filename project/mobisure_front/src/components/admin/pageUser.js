import { useState, useEffect } from 'react';
import adminService from '../../services/adminService';

function UserRow({ user, onRoleChange }) {
	
	const handleRoleChange = (role) => {
	    const updatedRoles = user.roles.includes(role)
	      ? user.roles.filter(r => r !== role) // Retirer le rôle
	      : [...user.roles, role]; // Ajouter le rôle

	    onRoleChange(user.id, updatedRoles);
	  };
	
  return (
    <tr>
      <td>{user.nom}</td>
      <td>{user.prenom}</td>
	  <td>
	  	<input 
			type="checkbox"
			checked={user.roles.includes('USER')}
			onChange={() => handleRoleChange('USER')}
		/>
	  </td>
	  <td>
	  	<input 
	  		type="checkbox"
	  		checked={user.roles.includes('ADMIN')}
			onChange={() => handleRoleChange('ADMIN')}
	  	/>
	  </td>
	  
    </tr>
  );
}

function UserTable({ users, onRoleChange }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Nom</th>
          <th>Prénom</th>
		  <th>Rôle : USER</th>
		  <th>Rôle : ADMIN</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user, index) => (
          <UserRow key={user.id || index} user={user} onRoleChange={onRoleChange} />
        ))}
      </tbody>
    </table>
  );
}

export default function PageUser() {
  const [listeUsers, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    adminService.fetchAllUsers().then(data => {
      setUsers(data);
      setLoading(false);
    });
  }, []);
  
  const handleRoleChange = (userId, updateRoles) => {
	setUsers(majUser =>
		majUser.map(user =>
			user.id === userId ? { ...user, roles: updateRoles } : user
		)
	);
	adminService.updateUserRoles(userId, updateRoles);
  }

  if (loading) {
    return <p>Loading ...</p>;
  }

  return (
    <div>
      <UserTable users={listeUsers} onRoleChange={handleRoleChange} />
    </div>
  );
}
