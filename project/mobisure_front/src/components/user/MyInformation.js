import React, { useState, useEffect } from 'react';
import { useAuth } from '../auth/AuthContext';
import UserService from '../../services/userService';
import { Card, Col, Row, Container } from "react-bootstrap";

const MyInformation = () => {
	
  const { getUser } = useAuth(); // Récupération de l'utilisateur via AuthContext
  const userDetails = getUser(); // Détails de l'utilisateur connecté
  const [loading, setLoading] = useState(true); // État de chargement
  const [user, setUser] = useState(null); // État pour les données utilisateur


  useEffect(() => {
      setLoading(true);
      UserService.fetchUserByEmail(userDetails.unsername).then(data => {
        setUser(data);
        setLoading(false);
		console.log(user);
      });
    },[]);

  // Affichage en cas de chargement
  if (loading) {
    return <p>Chargement des informations utilisateur...</p>;
  }	

  // Affichage des informations utilisateur
  return (
      <Container className="mt-5">
        <h1 className="text-center mb-4">Mes informations</h1>
        <Row>
          <Col md={6} lg={4} className="mx-auto">
            <Card className="shadow-lg border-light rounded">
              <Card.Body>
                <Card.Title className="text-center mb-3"></Card.Title>
                <ul className="list-unstyled">
                  <li className="mb-3">
                    <strong>Nom : </strong>{user.nom}
                  </li>
                  <li className="mb-3">
                    <strong>Prénom : </strong>{user.prenom}
                  </li>
                  <li className="mb-3">
                    <strong>Email : </strong>{user.mail}
                  </li>
                  <li className="mb-3">
                    <strong>Genre : </strong>{user.sexe}
                  </li>
                  <li className="mb-3">
                    <strong>Date de naissance : </strong>{user.dateNaissance}
                  </li>
                  <li className="mb-3">
                    <strong>Téléphone : </strong>{user.telephone}
                  </li>
                </ul>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    );
};

export default MyInformation;
