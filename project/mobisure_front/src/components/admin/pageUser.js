import { useState, useEffect } from 'react';
import adminService from '../../services/adminService';
import '../../assets/css/pageUser.css';

function UserRow({ user, onRoleChange, fonctionDelete }) {
	
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
	  <td>
	  	<input 
	  		type="checkbox"
	  	  	checked={user.roles.includes('PARTENAIRE')}
	  		onChange={() => handleRoleChange('PARTENAIRE')}
	  	/>
	  </td>
	  <td>
	  	<input 
	  		type="checkbox"
	  	  	checked={user.roles.includes('MEDECIN')}
	  		onChange={() => handleRoleChange('MEDECIN')}
	  	/>
	  </td>
	  <td>
	  	<button id="btn-delete" type="button" onClick={() => fonctionDelete(user.id)}>Supprimer</button>
	  </td>

	  
    </tr>
  );
}

function UserTable({ users, onRoleChange, fonctionDelete }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Nom</th>
          <th>Prénom</th>
		  <th>Rôle : USER</th>
		  <th>Rôle : ADMIN</th>
		  <th>Rôle : PARTENAIRE</th>
		  <th>Rôle : MEDECIN</th>
		  <th>Supprimer</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user, index) => (
          <UserRow 
		  	key={user.id || index} 
			user={user} 
			onRoleChange={onRoleChange} 
			fonctionDelete = {fonctionDelete}
			/>
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
  
  const deleteUser = async (id) => {
	adminService.deleteById(id).then(() => {
		let updateUsers = [...listeUsers].filter(user => user.id !== id);
		setUsers(updateUsers);
	});
  }

  if (loading) {
    return <p>Loading ...</p>;
  }

  return (
    <div>
      <UserTable 
	  	users={listeUsers} 
		onRoleChange={handleRoleChange} 
		fonctionDelete = {deleteUser}
		/>
    </div>
  );
}
