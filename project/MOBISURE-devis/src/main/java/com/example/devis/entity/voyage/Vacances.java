package com.example.devis.entity.voyage;

import java.util.Date;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVoyage.NombreVoyageurs;
import com.example.devis.Enum.Options.VacanceOptions;
import com.example.devis.entity.Assurance;

@Document
public class Vacances extends Assurance {

	private String paysdepart;
	private String paysArrive;
	private Date dateDepart;
	private Date dateArrive;
	private NombreVoyageurs nbPersonnes;
	private Set<VacanceOptions> options;
	
	public Vacances() {}

	public Vacances(Long clientId, String paysdepart, String paysArrive, Date dateDepart, Date dateArrive,
			NombreVoyageurs nbPersonnes, Set<VacanceOptions> options) {
		
		super(clientId,TypeAssurance.VACANCES);
		
		this.paysdepart = paysdepart;
		this.paysArrive = paysArrive;
		this.dateDepart = dateDepart;
		this.dateArrive = dateArrive;
		this.nbPersonnes = nbPersonnes;
		this.options = options;
	}

	public String getPaysdepart() {
		return paysdepart;
	}

	public void setPaysdepart(String paysdepart) {
		this.paysdepart = paysdepart;
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

	public NombreVoyageurs getNbPersonnes() {
		return nbPersonnes;
	}

	public void setNbPersonnes(NombreVoyageurs nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	public Set<VacanceOptions> getOptions() {
		return options;
	}

	public void setOptions(Set<VacanceOptions> options) {
		this.options = options;
	}

	
}
