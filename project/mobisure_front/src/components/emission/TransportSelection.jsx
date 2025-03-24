import React from 'react';

const TransportSelection = ({transport, setTransport}) => {

    const handleOptionChange = (event) => {
        setTransport(event.target.value);
    };

    return (
        <div>
        <h2>Choose a transport:</h2>
        <select value={transport} onChange={handleOptionChange}>
            <option value="1">Avion</option>
            <option value="4">Voiture thermique</option>
            <option value="5">Voiture électrique</option>
            <option value="6">Autocar thermique</option>
            <option value="8">Vélo à assistance électrique</option>
            <option value="9">Bus thermique</option>
            <option value="12">Scooter ou moto légère thermique</option>
            <option value="13">Moto thermique</option>
            <option value="16">Bus électrique</option>
            <option value="17">Trotinette électrique</option>
            <option value="21">Bus GNV</option>
            <option value="24">Covoiturage thermique</option>
            <option value="28">Covoiturage électrique</option>
        </select>
        </div>
    );
}

export default TransportSelection;