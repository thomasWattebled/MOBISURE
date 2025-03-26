import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import markerIconPng from "leaflet/dist/images/marker-icon.png"
import {Icon} from 'leaflet'
import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from 'react-leaflet'
import CoordinateInput from '../../../emission/CoordinateInput'
import coordinateToAddressInstance from '../../../../services/CoordinateToAddressService';
import calculateEmissionService from '../../../../services/CalculateEmissionService';
import TransportSelection from '../../../emission/TransportSelection';
import '../../style/form.css';

const VoyageProfessionnelForm = ({ userData, setUserData,handleChange, isModalVisible, setModalVisible}) => {
  const navigate = useNavigate();
  const [selectedOptions, setSelectedOptions] = useState(new Set());
  const [clickPosition, setClickPosition] = useState('');
  	const position = [51.505, -0.09];
  	const [startCoordinates, setStartCoordinates] = useState('');
  	const [endCoordinates, setEndCoordinates] = useState('');
  	const [startValue, setStartValue] = useState('');
  	const [endValue, setEndValue] = useState('');
  	const [startFocused, setStartFocused] = useState(false);
  	const [endFocused, setEndFocused] = useState(false);
  	const [transport, setTransport] = useState("1");
  	const [emissions, setEmissions] = useState(-1);

  const [formData, setFormData] = useState({
    entreprise: '',
	paysDepart:"",
	paysArrive:"",
	villeDepart:"",
	villeArrive:"",
	distance: '',
	transport:transport,
    dateDepart: '',
    dateArrive: '',
	co2: '',
    options: []
  });
  
  const calculateDistance = (lat1, lon1, lat2, lon2) => {
  	      const R = 6371; // Rayon de la Terre en km
  	      const dLat = (lat2 - lat1) * (Math.PI / 180);
  	      const dLon = (lon2 - lon1) * (Math.PI / 180);
  	      
  	      const a = 
  	          Math.sin(dLat / 2) * Math.sin(dLat / 2) +
  	          Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) * 
  	          Math.sin(dLon / 2) * Math.sin(dLon / 2);
  	      
  	      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  	      return R * c; // Distance en km
  	  };
	  
	  useEffect(() => {
	  	      if (startCoordinates && endCoordinates) {
	  	          const [startLon, startLat] = startCoordinates.split(',').map(Number);
	  	          const [endLon, endLat] = endCoordinates.split(',').map(Number);
	  	          const distance = calculateDistance(startLat, startLon, endLat, endLon);
	  	          setFormData((prevData) => ({
	  	              ...prevData,
	  	              distance: distance.toFixed(2) // Arrondi à 2 décimales
	  	          }));
	  	      }
	  	  }, [startCoordinates, endCoordinates]);

        useEffect(() => {
          if (userData) {
            setFormData((prevFormData) => ({
              ...prevFormData, 
              ...userData, 
            }));
          }
        }, [userData]);
		
		const handleStartFocused = () => {
			          setStartFocused(true);
			          setEndFocused(false);
			      }
				  
				  const handleEndFocused = () => {
				  	          setEndFocused(true);
				  	          setStartFocused(false);
				  	      }
					
						  const handleCoordinatesChange = (e) => {
						  		      coordinateToAddressInstance.translateToAddress(e.lat, e.lng).then((data) => {
						  		          const { formatted, country, city } = data;
						  		          const newCoordinates = e.lng + "," + e.lat;

						  		          if (startFocused) {
						  		              setStartValue(formatted);
						  		              setStartCoordinates(newCoordinates);
						  		              setFormData((prevData) => ({
						  		                  ...prevData,
						  		                  coordDepart: newCoordinates,
						  		                  villeDepart: city,
						  		                  paysDepart: country
						  		              }));
						  		          }

						  		          if (endFocused) {
						  		              setEndValue(formatted);
						  		              setEndCoordinates(newCoordinates);
						  		              setFormData((prevData) => ({
						  		                  ...prevData,
						  		                  coordArrive: newCoordinates,
						  		                  villeArrive: city,
						  		                  paysArrive: country
						  		              }));
						  		          }
						  		      });
						  		  };
								  
								  useEffect(() => {
								  		    setFormData((prevData) => ({
								  		      ...prevData,
								  		      transport: transport
								  		    }));
								  		  }, [transport]);
								  		  
								  		  useEffect(() => {
								  			setFormData((prevData) => ({
								  				...prevData,
								  				co2: 50  //setEmissions(calculateEmissionService.calculate(formData.startCoordonate, formData.endCoordonate, formData.transport))
								  			}));
								  		  },[formData.coordDepart,formData.coordArrive]);
										  
										  
										  function MapEvents() {
										  			          useMapEvents({
										  			              click(e) {
										  			                  setClickPosition(e.latlng);
										  			                  handleCoordinatesChange(e.latlng);
										  			              },
										  			          });
										  			          return null;
										  			      }

  const optionsDisponibles = [
    "Perte de documents",
    "Matériel pro couvert",
  "Assistance juridique à l’étranger"
  ];

  const handleOptionChange = (option) => {
    setSelectedOptions(prevSet => {
      const newSet = new Set(prevSet);
      if (newSet.has(option)) {
        newSet.delete(option);
      } else {
        newSet.add(option);
      }

      setFormData((prevData) => ({
        ...prevData,
        options: Array.from(newSet)
      }));

      return newSet;
    });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
      e.preventDefault();// Redirige vers la page de récap en passant formData
      navigate("/devis", { state: { formData } });
    };

	console.log(formData);
  return (
    <div className="form-container">
      <h3>Formulaire pour Voyage Professionnel</h3>
      <form onSubmit={handleSubmit}>
	  <div className="form-group">
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
	  	  </div>
        <div className="form-group">
          <label htmlFor="entreprise">Nom de l'entreprise :</label>
          <input
            type="text"
            id="entreprise"
            name="entreprise"
            value={formData.entreprise}
            onChange={handleInputChange}
            required
            placeholder="Entrez le nom de votre entreprise"
          />
        </div>
        <div className="form-group">
          <label htmlFor="dateDepart">Date Depart :</label>
          <input
            type="date"
            id="dateDepart"
            name="dateDepart"
            value={formData.dateDepart}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="dateArrive">Date Retour :</label>
          <input
            type="date"
            id="dateArrive"
            name="dateArrive"
            value={formData.dateArrive}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Options supplémentaires :</label>
          <div className="checkbox-group">
            {optionsDisponibles.map((option) => (
              <div key={option}>
                <input
                  type="checkbox"
                  id={option}
                  name="options"
                  value={option}
                  checked={selectedOptions.has(option)}
                  onChange={() => handleOptionChange(option)}
                />
                <label htmlFor={option}>{option}</label>
              </div>
            ))}
          </div>
        </div>
        <button type="submit">Soumettre</button>
      </form>
    </div>
  );
};

export default VoyageProfessionnelForm;