package com.example.devis.request;

import java.util.Date;
import java.util.Set;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVoyage.NombreVoyageurs;
import com.example.devis.Enum.Options.VacanceOptions;

public class VacancesRequest {

	private Long clientId;
    private TypeAssurance type;
    private String paysdepart;
	private String paysArrive;
	private Date dateDepart;
	private Date dateArrive;
	private NombreVoyageurs nbPersonnes;
	private Set<VacanceOptions> options;
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public TypeAssurance getType() {
		return type;
	}
	public void setType(TypeAssurance type) {
		this.type = type;
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
