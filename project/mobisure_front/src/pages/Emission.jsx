import React, {useState} from 'react';
import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from 'react-leaflet'
import markerIconPng from "leaflet/dist/images/marker-icon.png"
import {Icon} from 'leaflet'
import 'leaflet/dist/leaflet.css';
import CoordinateInput from '../components/emission/CoordinateInput'
import coordinateToAddressInstance from '../services/CoordinateToAddressService';
import CalculateEmission from '../components/emission/CalculateEmission';
import TransportSelection from '../components/emission/TransportSelection';

const Emission = () => {
    const [clickPosition, setClickPosition] = useState('');
    const position = [51.505, -0.09];

    const [startCoordinates, setStartCoordinates] = useState('');
    const [endCoordinates, setEndCoordinates] = useState('');

    const [startValue, setStartValue] = useState('');
    const [endValue, setEndValue] = useState('');

    const [startFocused, setStartFocused] = useState(false);
    const [endFocused, setEndFocused] = useState(false);

    const [transport, setTransport] = useState(4);

    const handleStartFocused = () => {
        setStartFocused(true);
        setEndFocused(false);
    }

    const handleEndFocused = () => {
        setEndFocused(true);
        setStartFocused(false);
    }

    const handleCoordinatesChange = (e) => {
        if (startFocused) coordinateToAddressInstance.translateToAddress(e.lat, e.lng).then((value) => {
            setStartValue(value);
            setStartCoordinates(e.lng + "," + e.lat);
        });
        if (endFocused) coordinateToAddressInstance.translateToAddress(e.lat, e.lng).then((value) => {
            setEndValue(value);
            setEndCoordinates(e.lng + "," + e.lat);
        });
    }

    function MapEvents() {
        useMapEvents({
            click(e) {
                setClickPosition(e.latlng);
                handleCoordinatesChange(e.latlng);
            },
        });
        return null;
    }

    return (
        <>
            <MapContainer 
                center={position}
                zoom={10} scrollWheelZoom={false} 
                style={{ height: '400px', width: '100%' }}
            >
                <TileLayer
                    attribution='Map'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {clickPosition && (
                    <Marker
                        position={clickPosition}
                        icon={new Icon({iconUrl: markerIconPng, iconSize: [25, 41], iconAnchor: [12, 41]})}
                    >
                        <Popup>
                            Coordonnées du clic : <br />
                            Latitude: {clickPosition.lat} <br />
                            Longitude: {clickPosition.lng}
                        </Popup>
                    </Marker>
                )}
                <MapEvents />
            </MapContainer>
            <CoordinateInput coordinateName="Départ" coordinateValue={startValue} isFocusedFunct={handleStartFocused} isFocused={startFocused}/>
            <CoordinateInput coordinateName="Arrivée" coordinateValue={endValue} isFocusedFunct={handleEndFocused} isFocused={endFocused}/>
            <TransportSelection transport={transport} setTransport={setTransport}/>
            <CalculateEmission startCoordonate={startCoordinates} endCoordonate={endCoordinates} transport={transport} />
        </>
    )
}

export default Emission;