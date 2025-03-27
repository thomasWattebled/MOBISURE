import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../../style/form.css";
import { Tooltip, Toast, Popover } from 'bootstrap';

const AssuranceMotoForm = ({ userData, setUserData, isModalVisible, setModalVisible }) => {
	const [selectedMarque, setSelectedMarque] = useState("");
	const [models, setModels] = useState([]);
	const [selectedOptions, setSelectedOptions] = useState(new Set());
	const navigate = useNavigate();
	const [formData, setFormData] = useState({
		marque: "",
		modele: "",
		motorisation: "",
		fabrication: "",
		utilisation: "",
		dure: "",
		plaque: "",
		options: []
	});

	document.querySelectorAll('[data-bs-toggle="tooltip"]').forEach((tooltipNode) => {
		new Tooltip(tooltipNode);
	});

	useEffect(() => {
		if (userData) {
			setFormData((prevFormData) => ({
				...prevFormData,
				...userData,
			}));
		}
	}, [userData]);


	const marques = [
		"Yamaha",
		"Honda",
		"Kawasaki",
		"Suzuki",
		"Ducati",
		"Harley-Davidson",
		"BMW",
		"Triumph",
		"KTM",
		"Indian",
	];

	const optionsDisponibles = [
		"Assistance zéro km",
		"Équipements protégés",
		"Garantie tous risques",
	];

	// Liste des modèles associés à chaque marque
	const modelsByMarque = {
		Yamaha: ["MT-07", "MT-09", "R1", "R6", "FZ-09"],
		Honda: ["CBR600RR", "CB1000R", "Africa Twin", "CB500F", "Rebel 500"],
		Kawasaki: ["Ninja 650", "Z900", "KLR650", "Versys 650", "Vulcan S"],
		Suzuki: ["GSX-R600", "V-Strom 650", "SV650", "Hayabusa", "Bandit 1200"],
		Ducati: ["Monster 1200", "Panigale V2", "Scrambler", "Multistrada 950", "Diavel"],
		"Harley-Davidson": ["Sportster", "Softail", "Road King", "Street Glide", "Iron 883"],
		BMW: ["S1000RR", "R1250GS", "F850GS", "R18", "K1600GTL"],
		Triumph: ["Street Triple", "Bonneville", "Tiger 800", "Rocket 3", "Speed Triple"],
		KTM: ["Duke 390", "RC 390", "Adventure 790", "Duke 890", "Super Duke 1290"],
		Indian: ["Scout", "Chieftain", "Roadmaster", "FTR 1200", "Chief Dark Horse"],
	};

	const durations = ["1 semaine", "2 semaines", "1 mois", "6 mois", "1 an"];

	const handleMarqueChange = (e) => {
		const marque = e.target.value;
		setSelectedMarque(marque);
		setFormData((prevData) => ({
			...prevData,
			marque: marque
		}));
		setModels(modelsByMarque[marque] || []);
	};

	const handleSubmit = (e) => {
		e.preventDefault();
		// Redirige vers la page de récap en passant formData
		navigate("/devis", { state: { formData } });
	};

	const handleChange = (e) => {
		const { name, value } = e.target;
		setFormData((prevData) => ({
			...prevData,
			[name]: value,
		}));
	};

	const handleOptionChange = (option) => {
		setSelectedOptions(prevSet => {
			const newSet = new Set(prevSet);
			if (newSet.has(option)) {
				newSet.delete(option);
			} else {
				newSet.add(option);
			}

			// Mettre à jour formData avec les options sélectionnées
			setFormData((prevData) => ({
				...prevData,
				options: Array.from(newSet) // Convertir le Set en tableau
			}));

			return newSet;
		});
	};

	return (
		<div className="form-container">
			<h2>Formulaire Assurance Moto</h2>

			<form onSubmit={handleSubmit}>
				<div className="form-group">
					<label>
						Marque de moto :
						<select
							name="MarqueMoto"
							value={formData.marque || ""}
							onChange={handleMarqueChange}
							required
						>
							<option value="">Sélectionnez une marque</option>
							{marques.map((marque) => (
								<option key={marque} value={marque}>
									{marque}
								</option>
							))}
						</select>
					</label>
				</div>
				<div className="form-group">
					<label>
						Modèle de moto :
						<select name="modele" value={formData.modele || ""} required disabled={!selectedMarque} onChange={handleChange}>
							<option value="">Sélectionnez un modèle</option>
							{models.map((model) => (
								<option key={model} value={model}>
									{model}
								</option>
							))}
						</select>
					</label>
				</div>
				<div className="form-group">
					<label>
						<p>Électrique ?</p>
					</label>
					<div className="radio-group">
						<input
							type="radio"
							id="oui"
							name="motorisation"
							value="ELECTRIQUE"
							checked={formData.motorisation === "ELECTRIQUE"}
							onChange={handleChange}
						/>
						<label htmlFor="oui">Oui</label>
						<input
							type="radio"
							id="non"
							name="motorisation"
							value="THERMIQUE"
							checked={formData.motorisation === "THERMIQUE"}
							onChange={handleChange}
						/>
						<label htmlFor="non">Non</label>
					</div>
				</div>
				<div className="form-group">
					<label>
						Année de fabrication :
						<input value={formData.fabrication || ""} type="number" name="fabrication" onChange={handleChange} required />
					</label>
				</div>
				<div className="form-group">
					<label>
						Utilisation du véhicule :
						<select name="utilisation" value={formData.utilisation || ""}
							onChange={handleChange} required >
							<option value="">Sélectionnez l'utilisation</option>
							<option value="PERSONNEL">Personnel</option>
							<option value="PROFESSIONNELLE">Professionnel</option>
						</select>
					</label>
				</div>
				<div className="form-group">
					<label>
						Durée :
						<select value={formData.duree || ""} name="duree" onChange={handleChange} required>
							<option value="">Sélectionnez une durée</option>
							{durations.map((duration) => (
								<option key={duration} value={duration}>
									{duration}
								</option>
							))}
						</select>
					</label>
				</div>
				<div className="form-group">
					<label>
						Plaque d'immatriculation :
						<input type="text" name="plaque"
							value={formData.plaque || ""} onChange={handleChange} required />
					</label>
				</div>
				<div className="form-group">
					<label>Options supplémentaires :</label>
					<div className="checkbox-group">
						{optionsDisponibles.map((option) => {
							let prixOption = "pas de prix affiché";

							switch (option) {
								case "Assistance zéro km":
									prixOption = "8€ par mois";
									break;
								case "Équipements protégés":
									prixOption = "5€ par mois";
									break;
								case "Garantie tous risques":
									prixOption = "10€ par mois";
									break;
								default:
									prixOption = "pas de prix affiché";
							}

							return (
								<div key={option}>
									<input
										type="checkbox"
										id={option}
										name="options"
										value={option}
										checked={selectedOptions.has(option)}
										onChange={() => handleOptionChange(option)}
									/>
									<label
										htmlFor={option}
										data-bs-toggle="tooltip"
										title={`Prix de l'option : ${prixOption}`}
									>
										{option}
									</label>
								</div>
							);
						})}
					</div>
				</div>
				<button type="submit">Soumettre</button>
			</form>
		</div>
	);
};

export default AssuranceMotoForm;
