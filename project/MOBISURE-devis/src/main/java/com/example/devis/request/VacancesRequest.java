package com.example.devis.request;

import java.util.Date;
import java.util.Set;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVoyage.NombreVoyageurs;
import com.example.devis.Enum.Options.VacanceOptions;

public class VacancesRequest {

	private Long clientId;
    private TypeAssurance type;
    private String paysDepart;
    private String paysArrive;
    private String villeDepart;
    private String villeArrive;
	private Date dateDepart;
	private Date dateArrive;
	private int transport;
	private double distance;
	private double co2;
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
