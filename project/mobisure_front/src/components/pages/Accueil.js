import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function Home() {
  return (
    <div className="container text-center mt-5">
      <h1>Bienvenue sur Mobisure</h1>
      <p className="lead">
        Votre solution d'assurance mobile simple et rapide.
      </p>
      <button
        className="btn btn-primary mt-3"
        onClick={() => alert('Bouton cliqué !')}
      >
        Découvrir nos services
      </button>
    </div>
  );
}
