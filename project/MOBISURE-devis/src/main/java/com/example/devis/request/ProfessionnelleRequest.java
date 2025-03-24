package com.example.devis.request;

import java.util.Date;
import java.util.Set;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.Options.ProfessionelleOptions;

public class ProfessionnelleRequest {

	private Long clientId;
    private TypeAssurance type;
    private String entreprise;
	private String paysArrive;
	private Date dateDepart;
	private Date dateArrive;
	private Set<ProfessionelleOptions> options;
	
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
	public Set<ProfessionelleOptions> getOptions() {
		return options;
	}
	public void setOptions(Set<ProfessionelleOptions> options) {
		this.options = options;
	}
	
	
	
}
