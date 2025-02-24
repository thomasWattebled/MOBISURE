import React from 'react';

const TransportSelection = ({transport, setTransport}) => {

    const handleOptionChange = (event) => {
        setTransport(event.target.value);
    };

    return (
        <div>
        <h2>Choose a transport:</h2>
        <select value={transport} onChange={handleOptionChange}>
            <option value="4">Voiture thermique</option>
            <option value="5">Voiture électrique</option>
            <option value="6">Car thermique</option>
        </select>
        </div>
    );
}

export default TransportSelection;