import { jsPDF } from "jspdf";
import logo from "../../logo192.png";

const textIntro = (doc,y,contrat) => {
	// Ajout du logo
	doc.addImage(logo, "PNG", 175, 8, 30, 20);

	// Titre du document
	doc.setFont("helvetica", "bold");
	doc.setFontSize(20);
	
	if(contrat.type === "PROFESSIONNELLE" || contrat.type === "VACANCES"){
		doc.setFontSize(15);
		doc.text(`CONTRAT D’ASSURANCE ${contrat.type}`, 50, 25);
	}
	else {
		doc.text(`CONTRAT D’ASSURANCE ${contrat.type}`, 50, 25);
	}

	// Ligne séparatrice
	doc.setLineWidth(0.5);
	doc.line(20, 30, 190, 30);

	// Introduction
	doc.setFont("helvetica", "italic");
	doc.setFontSize(12);
	
	doc.text(`Nous vous remercions d’avoir souscrit à notre assurance ${contrat.type.toLowerCase()}.`, 20, y);
	y += 5;
	doc.text("Ce document officialise votre contrat et récapitule les informations essentielles de votre couverture.", 20, y);
	y += 5;
	doc.text("Veuillez en prendre connaissance et nous contacter en cas de question.", 20, y);
	y += 10;
	doc.setFont("helvetica", "bold");
	doc.text(`Les informations de votre contrat :`, 20, y);
	doc.setLineWidth(0.5);
	doc.line(20, y+2, 90, y+2);
}

const addFooter = (doc) => {
    const pageHeight = doc.internal.pageSize.height; // Hauteur de la page
    doc.setFontSize(10);
    doc.setTextColor(100);
    doc.text("© 2024 - Assurance AssurMob | Tous droits réservés", 20, pageHeight - 10);
};


const textFin = (doc,user) => {
	
	const pageHeight = doc.internal.pageSize.height; // Hauteur de la page
	let y = pageHeight - 100; // Position à 60 pixels du bas
	
	doc.setFont("helvetica", "bold");
	doc.text("Coordonnées de l’Assurance :", 20, y);
	doc.setLineWidth(0.5);
	doc.line(20, y+2, 82, y+2);
	doc.setFont("helvetica", "normal");
	y += 10;
	doc.text("AssurMob Assurance", 20, y);
	y += 10;
	doc.text("Cité scientifique, 59650 Villeneuve d'Ascq, France", 20, y);
	y += 10;
	doc.text("Téléphone : 03 20 17 59 47", 20, y);
	y += 10;
	doc.text("Email : contact@assurMob.fr", 20, y);
	y += 15;

	doc.setFont("helvetica", "bold");
	doc.text("Signature de l'Assuré :", 20, y);
	doc.line(20, y + 5, 80, y + 5);
	doc.text(`${user.nom}  ${user.prenom}`, 20, y + 15);

	doc.text("Tampon / Signature de l’Assurance :", 120, y);
	doc.line(120, y + 5, 190, y + 5);
	doc.text(`AssurMob`, 120, y + 15);
	
	addFooter(doc);
}

// Styles du PDF
const generatePDFVelo = (contrat, user) => {
	const doc = new jsPDF();
	let y = 40;
	
	textIntro(doc,y,contrat);
	y += 25;
	
	// Informations du contrat
	doc.setFont("helvetica", "normal");
	doc.setFontSize(12);
	y += 12;
	doc.text(`Numéro de dossier : ${contrat.numDossier}`, 20, y);
	y += 12;
	doc.text(`Nom : ${user.nom}`, 20, y);
	y += 12;
	doc.text(`Prénom : ${user.prenom}`, 20, y);
	y += 12;
	doc.text(`Email : ${user.mail}`, 20, y);
	y += 12;
	doc.text(`Téléphone : ${user.telephone}`, 20, y);
	y += 12;
	doc.text(`Motorisation : ${contrat.electrique ? "Électrique" : "Classique"}`, 20, y);
	y += 12;
	doc.text(`Type de contrat : ${contrat.type}`, 20, y);
	y += 12;

	// Options du contrat
	const options = contrat.options.length > 0 ? contrat.options.join(", ") : "Aucune";
	doc.text(`Options : ${options}`, 20, y);
	y += 10;

	textFin(doc,user);

	// Génération du fichier
	doc.save(`Contrat_${contrat.numDossier}.pdf`);
};



// Styles du PDF
const generatePDFVoitureMoto = (contrat, user) => {
	const doc = new jsPDF();
	let y = 40;	
	
	textIntro(doc,y, contrat);
	y += 25;
	
	doc.setFont("helvetica", "normal");
	doc.setFontSize(12);
	y += 15;
	doc.text(`Numéro de dossier : ${contrat.numDossier}`, 20, y);
	y += 8
	doc.text(`Nom : ${user.nom}`, 20, y);
	y += 8
	doc.text(`Prenom : ${user.prenom}`, 20, y);
	y += 8
	doc.text(`Adresse mail : ${user.mail}`, 20, y);
	y += 8
	doc.text(`Numéro de téléphone : ${user.telephone}`, 20, y);
	y += 8;
	doc.text(`Marque : ${contrat.marque}`, 20, y);
	y += 8;
	doc.text(`Modèle : ${contrat.modele}`, 20, y);
	y += 8;
	doc.text(`Motorisation : ${contrat.electrique}`, 20, y);
	y += 8;
	doc.text(`Utilisation : ${contrat.utilisation}`, 20, y);
	y += 8;
	doc.text(`Durée : ${contrat.duree}`, 20, y);
	y += 8;
	doc.text(`Année de fabrication : ${contrat.fabrication}`, 20, y);
	y += 8;
	doc.text(`Plaque d'immatriculation : ${contrat.plaque}`, 20, y);
	y += 8;
	doc.text(`Type de contrat : ${contrat.type}`, 20, y);
	y += 8;

	const options = contrat.options.length > 0 ? contrat.options.join(", ") : "Aucune";
	doc.text(`Options : ${options}`, 20, y);

	y += 20;
	textFin(doc,user);

	doc.save(`Contrat_${contrat.numDossier}.pdf`);
};

const generateVacances = (contrat, user) => {
	const doc = new jsPDF();
	let y = 40;
		
	textIntro(doc,y,contrat);
	y += 30;

	doc.setFont("helvetica", "normal");
	doc.setFontSize(12);
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
	doc.text(`Pays de départ : ${contrat.paysDepart}`, 20, y);
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
	textFin(doc,user);

	doc.save(`Contrat_${contrat.numDossier}.pdf`);
};

const generatePDFProfessionnelle = (contrat, user) => {
	const doc = new jsPDF();
	let y = 40;
			
	textIntro(doc,y,contrat);
	y += 30;

	doc.setFont("helvetica", "normal");
	doc.setFontSize(12);

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
	textFin(doc,user);

	doc.save(`Contrat_${contrat.numDossier}.pdf`);
};


export { generatePDFVelo, generatePDFVoitureMoto, generateVacances, generatePDFProfessionnelle };

