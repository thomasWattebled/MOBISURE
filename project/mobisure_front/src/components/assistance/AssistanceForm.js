import { useState, useEffect } from 'react';
import AssistanceFormView from './AssistanceFormView';
import SuccessDemande from './SuccesDemande';
import { useAuth } from '../auth/AuthContext';
import UserService from '../../services/userService';


const AssistanceForm = () => {
	
	const { getUser } = useAuth();
	const userDetails = getUser();
	const [loading, setLoading] = useState(true);
	const [user, setUser] = useState(null);

	useEffect(() => {
	        setLoading(true);
	        UserService.fetchUserByEmail(userDetails.unsername).then(data => {
	          	setUser(data);
	          	setLoading(false)
	        });
	      },[]);	
		  
  const [formData, setFormData] = useState({
    id_client : '',
	status : 'ATTENTE',
	date : new Date().toISOString(),
	message : '',
	type : '',
	nom : '',
	prenom : '',
	mail : '',
	mdp : '',
	telephone : '',
	ville : '',
	rue : '',
	nbBlesse: null,
	montant : 0,
	motif : ''
  });
  
  // Met à jour formData une fois que l'utilisateur est chargé
    useEffect(() => {
      if (user) {
        setFormData((prevFormData) => ({
          ...prevFormData,
          id_client: user.id, // Ajout de l'ID client
		  nom: user.nom,
		  prenom: user.prenom,
		  mail: user.mail,
		  mdp: user.mdp,
		  telephone: user.telephone
        }));
      }
    }, [user]);
	
	console.log(formData);
  
  const [isModalVisible, setModalVisible] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('http://localhost:8081/assistance/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
    .then((response) => {
      if (response.ok) {
        setModalVisible(true); // Ouvre le modal si la soumission est un succès
      }
      return response.text();
    })
    .then((data) => {
      console.log(data);
    })
    .catch((error) => {
      console.error('Erreur :', error);
    });
  };

  return (
    <AssistanceFormView
      formData={formData}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
      isModalVisible={isModalVisible}
      setModalVisible={setModalVisible} 
    />
  );
};

export default AssistanceForm;
