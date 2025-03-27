import { jsPDF } from "jspdf";

// Styles du PDF
const generatePDFVelo = (contrat, user) => {
	const doc = new jsPDF();
	doc.setFont("helvetica", "bold");
	doc.setFontSize(18);
	doc.text("Contrat d'Assurance Véhicule", 20, 20);
	doc.setFont("helvetica", "normal");
	doc.setFontSize(12);
	let y = 40;
	doc.text(`Numéro de dossier : ${contrat.numDossier}`, 20, y);
	y += 10
	doc.text(`Nom : ${user.nom}`, 20, y);
	y += 10
	doc.text(`Prenom : ${user.prenom}`, 20, y);
	y += 10
	doc.text(`Adresse mail : ${user.mail}`, 20, y);
	y += 10
	doc.text(`Numéro de téléphone : ${user.telephone}`, 20, y);
	y += 10
	doc.text(`Motorisation : ${contrat.electrique}`, 20, y);
	y += 10;
	doc.text(`Type de contrat : ${contrat.type}`, 20, y);
	y += 10;

	const options = contrat.options.length > 0 ? contrat.options.join(", ") : "Aucune";
	doc.text(`Options : ${options}`, 20, y);

	y += 20;
	doc.setFontSize(10);
	doc.setTextColor(100);
	doc.text("© 2024 - Contrat d'Assurance Vélo", 20, y);

	doc.save(`Contrat_${contrat.numDossier}.pdf`);
};

// Styles du PDF
const generatePDFVoitureMoto = (contrat, user) => {
	const doc = new jsPDF();
	doc.setFont("helvetica", "bold");
	doc.setFontSize(18);
	doc.text("Contrat d'Assurance Véhicule", 20, 20);
	doc.setFont("helvetica", "normal");
	doc.setFontSize(12);
	let y = 40;
	doc.text(`Numéro de dossier : ${contrat.numDossier}`, 20, y);
	y += 10
	doc.text(`Nom : ${user.nom}`, 20, y);
	y += 10
	doc.text(`Prenom : ${user.prenom}`, 20, y);
	y += 10
	doc.text(`Adresse mail : ${user.mail}`, 20, y);
	y += 10
	doc.text(`Numéro de téléphone : ${user.telephone}`, 20, y);
	y += 10;
	doc.text(`Marque : ${contrat.marque}`, 20, y);
	y += 10;
	doc.text(`Modèle : ${contrat.modele}`, 20, y);
	y += 10;
	doc.text(`Motorisation : ${contrat.electrique}`, 20, y);
	y += 10;
	doc.text(`Utilisation : ${contrat.utilisation}`, 20, y);
	y += 10;
	doc.text(`Durée : ${contrat.duree}`, 20, y);
	y += 10;
	doc.text(`Année de fabrication : ${contrat.fabrication}`, 20, y);
	y += 10;
	doc.text(`Plaque d'immatriculation : ${contrat.plaque}`, 20, y);
	y += 10;
	doc.text(`Type de contrat : ${contrat.type}`, 20, y);
	y += 10;

	const options = contrat.options.length > 0 ? contrat.options.join(", ") : "Aucune";
	doc.text(`Options : ${options}`, 20, y);

	y += 20;
	doc.setFontSize(10);
	doc.setTextColor(100);
	doc.text("© 2024 - Contrat d'Assurance Véhicule", 20, y);

	doc.save(`Contrat_${contrat.numDossier}.pdf`);
};

const generateVacances = (contrat, user) => {
	const doc = new jsPDF();

	doc.setFont("helvetica", "bold");
	doc.setFontSize(18);
	doc.text("Contrat de Vacances", 20, 20);

	doc.setFont("helvetica", "normal");
	doc.setFontSize(12);

	let y = 40;
	doc.text(`Numéro de dossier : ${contrat.numDossier}`, 20, y);
	y += 10
	doc.text(`Nom : ${user.nom}`, 20, y);
	y += 10
	doc.text(`Prenom : ${user.prenom}`, 20, y);
	y += 10
	doc.text(`Adresse mail : ${user.mail}`, 20, y);
	y += 10
	doc.text(`Numéro de téléphone : ${user.telephone}`, 20, y);
	doc.text(`Pays de départ : ${contrat.paysdepart}`, 20, y);
	y += 10;
	doc.text(`Pays d'arrivée : ${contrat.paysArrive}`, 20, y);
	y += 10;
	doc.text(`Date de départ : ${new Date(contrat.dateDepart).toLocaleDateString()}`, 20, y);
	y += 10;
	doc.text(`Date d'arrivée : ${new Date(contrat.dateArrive).toLocaleDateString()}`, 20, y);
	y += 10;
	doc.text(`Nombre de personnes : ${contrat.nbPersonnes}`, 20, y);
	y += 10;
	doc.text(`Type de contrat : ${contrat.type}`, 20, y);
	y += 10;

	const options = contrat.options.length > 0 ? contrat.options.join(", ") : "Aucune";
	doc.text(`Options : ${options}`, 20, y);

	y += 20;
	doc.setFontSize(10);
	doc.setTextColor(100);
	doc.text("© 2024 - Contrat de Vacances", 20, y);

	doc.save(`Contrat_${contrat.numDossier}.pdf`);
};

const generatePDFProfessionnelle = (contrat, user) => {
	const doc = new jsPDF();

	doc.setFont("helvetica", "bold");
	doc.setFontSize(18);
	doc.text(`Contrat ${contrat.type}`, 20, 20);

	doc.setFont("helvetica", "normal");
	doc.setFontSize(12);

	let y = 40;
	doc.text(`Numéro de dossier : ${contrat.numDossier}`, 20, y);
	y += 10
	doc.text(`Nom : ${user.nom}`, 20, y);
	y += 10
	doc.text(`Prenom : ${user.prenom}`, 20, y);
	y += 10
	doc.text(`Adresse mail : ${user.mail}`, 20, y);
	y += 10
	doc.text(`Numéro de téléphone : ${user.telephone}`, 20, y);
	y += 10;
	doc.text(`Pays d'arrivée : ${contrat.paysArrive}`, 20, y);
	y += 10;
	doc.text(`Date de départ : ${new Date(contrat.dateDepart).toLocaleDateString()}`, 20, y);
	y += 10;
	doc.text(`Date d'arrivée : ${new Date(contrat.dateArrive).toLocaleDateString()}`, 20, y);
	y += 10;

	if (contrat.nbPersonnes) {
		doc.text(`Nombre de personnes : ${contrat.nbPersonnes}`, 20, y);
		y += 10;
	}

	if (contrat.entreprise) {
		doc.text(`Entreprise : ${contrat.entreprise}`, 20, y);
		y += 10;
	}

	doc.text(`Type de contrat : ${contrat.type}`, 20, y);
	y += 10;

	const options = contrat.options.length > 0 ? contrat.options.join(", ") : "Aucune";
	doc.text(`Options : ${options}`, 20, y);

	y += 20;
	doc.setFontSize(10);
	doc.setTextColor(100);
	doc.text(`© 2024 - Contrat ${contrat.type}`, 20, y);

	doc.save(`Contrat_${contrat.numDossier}.pdf`);
};


export { generatePDFVelo, generatePDFVoitureMoto, generateVacances, generatePDFProfessionnelle };

