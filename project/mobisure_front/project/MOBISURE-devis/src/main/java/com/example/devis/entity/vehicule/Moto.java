package com.example.devis.entity.vehicule;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.devis.Enum.DureeAssurance;
import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.MarqueMoto;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.DevisVehicule.UtilisationVehicule;
import com.example.devis.entity.Assurance;

@Document
public class Moto extends Assurance{

	//Manque le modèle
	private MarqueMoto marque;
	private Motorisation electrique;
	private Long fabrication;
	private UtilisationVehicule utilisation;
	private DureeAssurance duree;
	private String modele;
	private String plaque;
	
	public Moto() {}

	public Moto(Long clientId,MarqueMoto marque, Motorisation electrique, Long fabrication, UtilisationVehicule utilisation,
			DureeAssurance duree,String modele,String plaque) {
		
		super(clientId,TypeAssurance.MOTO);
		this.marque = marque;
		this.electrique = electrique;
		this.fabrication = fabrication;
		this.utilisation = utilisation;
		this.duree = duree;
		this.modele = modele;
		this.plaque = plaque;
	}

	public MarqueMoto getMarque() {
		return marque;
	}

	public void setMarque(MarqueMoto marque) {
		this.marque = marque;
	}

	public Motorisation getElectrique() {
		return electrique;
	}

	public void setElectrique(Motorisation electrique) {
		this.electrique = electrique;
	}

	public Long getFabrication() {
		return fabrication;
	}

	public void setFabrication(Long fabrication) {
		this.fabrication = fabrication;
	}

	public UtilisationVehicule getUtilisation() {
		return utilisation;
	}

	public void setUtilisation(UtilisationVehicule utilisation) {
		this.utilisation = utilisation;
	}

	public DureeAssurance getDuree() {
		return duree;
	}

	public void setDuree(DureeAssurance duree) {
		this.duree = duree;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getPlaque() {
		return plaque;
	}

	public void setPlaque(String plaque) {
		this.plaque = plaque;
	}
	
	
	
	
}
