package com.example.devis.entity.voyage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.Options.ProfessionelleOptions;
import com.example.devis.entity.Assurance;

@Document
public class Professionnelle extends Assurance {

	private String entreprise;
	private String paysDepart;
    private String paysArrive;
    private String villeDepart;
    private String villeArrive;
	private Date dateDepart;
	private Date dateArrive;
	private int transport;
	private double distance;
	private double co2;
	private long nbSemaine;
	private Set<ProfessionelleOptions> options;
	
	public Professionnelle() {}

	public Professionnelle(Long clientId,String paysDepart, String paysArrive, String villeDepart, String villeArrive, String entreprise, Date dateDepart, Date dateArrive, Set<ProfessionelleOptions> options,int transport, double distance,double co2) {
		
		super(clientId,TypeAssurance.PROFESSIONNELLE);
		
		this.paysDepart = paysDepart;
		this.paysArrive = paysArrive;
		this.villeDepart = villeDepart;
		this.villeArrive = villeArrive;
		this.entreprise = entreprise;
		this.dateDepart = dateDepart;
		this.dateArrive = dateArrive;
		this.options = options;
		this.transport = transport;
		this.distance = distance;
		this.co2 = co2;
		this.nbSemaine = getNombreDeSemaines(this.dateDepart,this.dateArrive);
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
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

	public Set<ProfessionelleOptions> getOptions() {
		return options;
	}

	public void setOptions(Set<ProfessionelleOptions> options) {
		this.options = options;
	}

	public int getTransport() {
		return transport;
	}

	public void setTransport(int transport) {
		this.transport = transport;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public long getNombreDeSemaines(Date dateDepart,Date dateArrive) {
	    // Convertir Date en LocalDate
	    LocalDate depart = dateDepart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate arrive = dateArrive.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	    // Calculer la différence en semaines
	    return ChronoUnit.WEEKS.between(depart, arrive);
	}

	public long getNbSemaine() {
		return nbSemaine;
	}

	public void setNbSemaine(long nbSemaine) {
		this.nbSemaine = nbSemaine;
	}

	public double getCo2() {
		return co2;
	}

	public void setCo2(double co2) {
		this.co2 = co2;
	}

	public String getPaysDepart() {
		return paysDepart;
	}

	public void setPaysDepart(String paysDepart) {
		this.paysDepart = paysDepart;
	}

	public String getPaysArrive() {
		return paysArrive;
	}

	public void setPaysArrive(String paysArrive) {
		this.paysArrive = paysArrive;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrive() {
		return villeArrive;
	}

	public void setVilleArrive(String villeArrive) {
		this.villeArrive = villeArrive;
	}
	
	
	
}
