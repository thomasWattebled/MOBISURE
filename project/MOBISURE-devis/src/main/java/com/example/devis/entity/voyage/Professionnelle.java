package com.example.devis.entity.voyage;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.entity.Assurance;

@Document
public class Professionnelle extends Assurance {

	private String entreprise;
	private String paysArrive;
	private Date dateDepart;
	private Date dateArrive;
	
	public Professionnelle() {}

	public Professionnelle(Long clientId, String entreprise, String paysArrive, Date dateDepart, Date dateArrive) {
		
		super(clientId,TypeAssurance.PROFESSIONNELLE);
		
		this.entreprise = entreprise;
		this.paysArrive = paysArrive;
		this.dateDepart = dateDepart;
		this.dateArrive = dateArrive;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String getPaysArrive() {
		return paysArrive;
	}

	public void setPaysArrive(String paysArrive) {
		this.paysArrive = paysArrive;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Date getDateArrive() {
		return dateArrive;
	}

	public void setDateArrive(Date dateArrive) {
		this.dateArrive = dateArrive;
	}

}
