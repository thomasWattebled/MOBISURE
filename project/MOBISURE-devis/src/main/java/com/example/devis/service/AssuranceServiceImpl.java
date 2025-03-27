package com.example.devis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devis.Enum.DevisVoyage.NombreVoyageurs;
import com.example.devis.Enum.Options.MotoOptions;
import com.example.devis.Enum.Options.ProfessionelleOptions;
import com.example.devis.Enum.Options.VacanceOptions;
import com.example.devis.Enum.Options.VeloOptions;
import com.example.devis.Enum.Options.VoitureOptions;
import com.example.devis.entity.Assurance;
import com.example.devis.entity.vehicule.Moto;
import com.example.devis.entity.vehicule.Velo;
import com.example.devis.entity.vehicule.Voiture;
import com.example.devis.entity.voyage.Professionnelle;
import com.example.devis.entity.voyage.Vacances;
import com.example.devis.repository.AssuranceRepository;

@Service
public class AssuranceServiceImpl implements AssuranceService{
	
	@Autowired
	private AssuranceRepository repo;

	@Override
	public double createVoitureDevis(Voiture assurance) {
		
		double multipliccateur = 0 ;
		double prix_total = 15;
		
		switch(assurance.getMarque()) {
			case BMW,MERCEDES,FORD,AUDI -> multipliccateur = 1.2;
			case VOLKSWAGEN, TOYOTA, NISSAN, HONDA -> multipliccateur = 1.1;
			case  RENAULT,PEUGEOT -> multipliccateur = 1;
		}
		
		switch(assurance.getElectrique()) {
			case ELECTRIQUE -> multipliccateur *= 0.85;
			case THERMIQUE -> multipliccateur *= 1;
		}
		
		int nombreMois = 1;
		switch(assurance.getDuree()) {
			case SEMAINE ->  {multipliccateur *= 1.4;}
			case DEUX_SEMAINES -> {multipliccateur *= 1.3; prix_total *= 2;}
			case UN_MOIS -> {multipliccateur *= 1.2; prix_total *= 4;}
			case SIX_MOIS -> {multipliccateur *= 1.1; prix_total *= 24; nombreMois = 6;}
			case UN_AN -> {multipliccateur *=  1; prix_total *= 48; nombreMois = 12;}
		}
		
		if(assurance.getOptions() != null) {
			for(VoitureOptions option : assurance.getOptions()) {
				prix_total += (option.getPrix() * nombreMois);
			}
		}
				
		return prix_total * multipliccateur;
	}

	
	@Override
	public double createMotoDevis(Moto assurance) {
		
		double multipliccateur = 0 ;
		double prix_total = 10;
		
		
		switch(assurance.getMarque()) {
			case DUCATI, HARLEY, INDIAN -> multipliccateur = 1.2;
			case TRIUMPH, KTM -> multipliccateur = 1.1;
			case  YAMAHA, HONDA, KAWASAKI, SUZUKI -> multipliccateur = 1;
		}
		
		switch(assurance.getElectrique()) {
			case ELECTRIQUE -> multipliccateur *= 0.85;
			case THERMIQUE -> multipliccateur *= 1;
		}
	
		int nombreMois = 1;
		switch(assurance.getDuree()) {
			case SEMAINE ->  {multipliccateur *= 1.4;}
			case DEUX_SEMAINES -> {multipliccateur *= 1.3; prix_total *= 2;}
			case UN_MOIS -> {multipliccateur *= 1.2; prix_total *= 4;}
			case SIX_MOIS -> {multipliccateur *= 1.1; prix_total *= 24; nombreMois = 6;}
			case UN_AN -> {multipliccateur *=  1; prix_total *= 48; nombreMois = 12;}
		}
		
		if(assurance.getOptions() != null) {
			for(MotoOptions option : assurance.getOptions()) {
				prix_total += (option.getPrix() * nombreMois);
			}
		}
			
		return prix_total * multipliccateur;
	}

	@Override
	public double createVeloDevis(Velo assurance) {
		
		double prix_total = 0;
		
		switch(assurance.getElectrique()) {
			case THERMIQUE -> prix_total = 100;
			case ELECTRIQUE -> prix_total = 150;
		}
			
		if(assurance.getOptions() != null) {
			for(VeloOptions option : assurance.getOptions()) {
				prix_total += (option.getPrix() * 12);
			}
		}
		
		return prix_total;
	}


	@Override
	public Voiture createContratVoiture(Voiture assurance) {
		return repo.save(assurance);
	}


	@Override
	public Moto createContratMoto(Moto assurance) {
		return repo.save(assurance);
	}


	@Override
	public Velo createContratVelo(Velo assurance) {
		return repo.save(assurance);
	}


	@Override
	public List<Assurance> getAllAssurance() {
		return repo.findAll();
	}


	@Override
	public List<Assurance> getAssuranceByClientId(Long clientId) {
		return repo.findByClientId(clientId);
	}
	
	private double calculerMultiplicateurVoyageurs(NombreVoyageurs nombreVoyageurs) {
        double multiplicateur = 1.0;
        
        switch(nombreVoyageurs) {
        	case DEUX_A_TROIS : multiplicateur *= 1.05;
        	case QUATRE_A_SIX : multiplicateur *= 1.1;
        	case PLUS_DE_SIX : multiplicateur *= 1.25;
        }
 
        return multiplicateur;
    }


	@Override
	public double createVacancesDevis(Vacances assurance) {
		
		double prix_distance = 40;
		double prix_co2 = 40;
		
		double multiplicateurKm = 1 + ((assurance.getDistance() - 1000) / 1000.0) * 0.05;
		double multiplicateurCO2 = 1 + (assurance.getCo2() - 1) * 0.05;
		
        prix_distance *= multiplicateurKm;
        prix_co2 *= multiplicateurCO2;
        
        double total = prix_distance + prix_co2;
        
        long nbMois = assurance.getNbSemaine() / 4;
        
        if(nbMois < 1) { nbMois = 1;}
        
        if(assurance.getNbSemaine() >= 1 ) {
        	total *= assurance.getNbSemaine();
        }
        
        if(assurance.getOptions() != null) {
        	for (VacanceOptions option : assurance.getOptions()) {
        		total += (option.getPrix()*nbMois);
        	}
        }
        
        total *= calculerMultiplicateurVoyageurs(assurance.getNbPersonnes());
        
		return total;
	}


	@Override
	public double createProfessionnelleDevis(Professionnelle assurance) {
		
		double prix_distance = 50;
		double prix_co2 = 50;
		double multiplicateurKm = 1 + ((assurance.getDistance() - 1000) / 1000.0) * 0.05;
        
		//Appel a l'api co2
		double multiplicateurCO2 = 1 + (assurance.getCo2() - 1) * 0.05; // a changer !
		
        prix_distance *= multiplicateurKm;
        prix_co2 *= multiplicateurCO2;
        
        double total = prix_distance + prix_co2;
        
        long nbMois = assurance.getNbSemaine() / 4;
        if(nbMois < 1) { nbMois = 1;}
        
        if(assurance.getNbSemaine() >= 1 ) {
        	total *= assurance.getNbSemaine();
        }
        
        if(assurance.getOptions() != null) {
        	for (ProfessionelleOptions option : assurance.getOptions()) {
        		total += (option.getPrix()*nbMois);
        	}
        }

		return total;
	}


	@Override
	public Vacances createContratVacances(Vacances assurance) {
		return repo.save(assurance);
	}


	@Override
	public Professionnelle createContratProfessionnelle(Professionnelle assurance) {
		return repo.save(assurance);
	}


	@Override
	public Assurance getAssuranceByNumDossier(String numDossier) throws Exception {
		Optional<Assurance> assurance = repo.findByNumDossier(numDossier);
		if(assurance.isPresent()) {
			return assurance.get();
		}
		throw new Exception("Assurance non trouvée");
	}
	
	
}
