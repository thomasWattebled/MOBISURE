import React from 'react';
import AddSinistre from '../components/sinistre/addSinistre';
import SinistreList from '../components/sinistre/SinistreList';

const Sinistre = () => (
  <div className="container mt-5">
    <h1>Gestion des sinistre</h1>
    <SinistreList/>
    <AddSinistre/>
  </div>
);

export default Sinistre;