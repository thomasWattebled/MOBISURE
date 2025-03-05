import { useState, useEffect } from 'react';
import adminService from '../../services/adminService';
import { useNavigate } from "react-router-dom";
import { useAuth } from '../auth/AuthContext';
import '../../assets/css/pageUser.css';
import UserService from '../../services/userService';
import { WhenUserIsInRole } from "../security/PrivateRoute";

function UserRow({ user, onRoleChange, fonctionDelete, startConversation }) {
  const navigate = useNavigate();

  const handleRoleChange = (role) => {
    const updatedRoles = user.roles.includes(role)
      ? user.roles.filter(r => r !== role)
      : [...user.roles, role];
    onRoleChange(user.id, updatedRoles);
  };

  return (
    <tr>
      <td>{user.nom}</td>
      <td>{user.prenom}</td>
	  <td>{user.mail}</td>
	  <WhenUserIsInRole role="ADMIN">
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
	  	<input
	    	type="checkbox"
	        checked={user.roles.includes('CONSEILLER')}
	        onChange={() => handleRoleChange('CONSEILLER')}
	  	/>
	  </td>
	  </WhenUserIsInRole>
      <td>
        <button id="btn-modifier" type="button" onClick={() => navigate(`/updateClient/${user.id}`)}>Modifier</button>
      </td>
	  <WhenUserIsInRole role="ADMIN">
      <td>
        <button id="btn-delete" type="button" onClick={() => fonctionDelete(user.id)}>Supprimer</button>
      </td>
	  </WhenUserIsInRole>
      <td>
        <button id="btn-conversation" type="button" onClick={() => startConversation(user.id)}>Démarrer une conversation</button>
      </td>
    </tr>
  );
}

function UserTable({ users, onRoleChange, fonctionDelete, startConversation }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Nom</th>
          <th>Prénom</th>
		  <th>Email</th>
		  <WhenUserIsInRole role="ADMIN">
          <th>Rôle : USER</th>
          <th>Rôle : ADMIN</th>
          <th>Rôle : PARTENAIRE</th>
          <th>Rôle : MEDECIN</th>
		  <th>Rôle : Conseiller</th>
		  </WhenUserIsInRole>
          <th>Modifier</th>
		  <WhenUserIsInRole role="ADMIN">
          <th>Supprimer</th>
		  </WhenUserIsInRole>
          <th>Conversation</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user, index) => (
          <UserRow
            key={user.id || index}
            user={user}
            onRoleChange={onRoleChange}
            fonctionDelete={fonctionDelete}
            startConversation={startConversation}
          />
        ))}
      </tbody>
    </table>
  );
}

export default function PageUser() {
  const { getUser } = useAuth();
  const userDetails = getUser();
  const [listeUsers, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState("");
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);
    adminService.fetchAllUsers().then(data => {
      const filteredUsers = data.filter(user => user.mail !== userDetails.unsername);
      setUsers(filteredUsers);
      setLoading(false);
    });
  }, []);
  
  useEffect(() => {
        setLoading(true);
        UserService.fetchUserByEmail(userDetails.unsername).then(data => {
          	setUser(data);
          	setLoading(false)
        });
      },[]);

  const handleRoleChange = (userId, updateRoles) => {
    setUsers(majUser =>
      majUser.map(user =>
        user.id === userId ? { ...user, roles: updateRoles } : user
      )
    );
    adminService.updateUserRoles(userId, updateRoles);
  };

  const deleteUser = async (id) => {
    adminService.deleteById(id).then(() => {
      let updateUsers = [...listeUsers].filter(user => user.id !== id);
      setUsers(updateUsers);
    });
  };

  const checkConversationExists = async (userId) => {
    try {
      const response = await fetch(`http://localhost:8081/checkConversation/${user.id}/${userId}`, {
        credentials: 'include',
      });
      const data = await response.json();
      return data;
    } catch (error) {
      console.error("Erreur lors de la vérification de la conversation :", error);
      return false;
    }
  };

  const startConversation = async (userId) => {
    navigate(`/messagerie/${userId}`);
  };

  const filteredUsers = listeUsers.filter((user) =>
    user.nom.toLowerCase().includes(searchTerm.toLowerCase()) ||
    user.prenom.toLowerCase().includes(searchTerm.toLowerCase()) ||
	user.mail.toLowerCase().includes(searchTerm.toLocaleLowerCase())
  );

  if (loading) {
    return <p>Loading ...</p>;
  }

  return (
    <div>
      <input
        type="text"
        placeholder="Rechercher un utilisateur..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <UserTable
        users={filteredUsers}
        onRoleChange={handleRoleChange}
        fonctionDelete={deleteUser}
        startConversation={startConversation}
      />
    </div>
  );
}