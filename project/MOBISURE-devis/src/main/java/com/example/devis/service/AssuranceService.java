package com.example.devis.service;

import java.util.List;

import com.example.devis.entity.Assurance;
import com.example.devis.entity.vehicule.Moto;
import com.example.devis.entity.vehicule.Velo;
import com.example.devis.entity.vehicule.Voiture;
import com.example.devis.entity.voyage.Professionnelle;
import com.example.devis.entity.voyage.Vacances;

public interface AssuranceService {

	public double createVoitureDevis(Voiture assurance);
	public double createMotoDevis(Moto assurance);
	public double createVeloDevis(Velo assurance);
	public double createVacancesDevis(Vacances assurance);
	public double createProfessionnelleDevis(Professionnelle assurance);
	
	public Voiture createContratVoiture(Voiture assurance);
	public Moto createContratMoto(Moto assurance);
	public Velo createContratVelo(Velo assurance);
	public Vacances createContratVacances(Vacances assurance);
	public Professionnelle createContratProfessionnelle(Professionnelle assurance);

	public List<Assurance> getAllAssurance();
	public List<Assurance> getAssuranceByClientId(Long clientId);
}
