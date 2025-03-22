package com.example.devis.request;

import java.util.Set;

import com.example.devis.Enum.DureeAssurance;
import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.MarqueVoiture;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.DevisVehicule.UtilisationVehicule;
import com.example.devis.Enum.Options.VoitureOptions;

public class VoitureRequest {

	private Long clientId;
    private TypeAssurance type;
    private Motorisation motorisation;
    private Long fabrication;
    private UtilisationVehicule utilisation;
    private DureeAssurance duree;
    private String modele;
    private MarqueVoiture marque;
    private String plaque;
    private Set<VoitureOptions> options;
    
	public String getPlaque() {
		return plaque;
	}
	public void setPlaque(String plaque) {
		this.plaque = plaque;
	}
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
	public Motorisation getMotorisation() {
		return motorisation;
	}
	public void setMotorisation(Motorisation motorisation) {
		this.motorisation = motorisation;
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
	public MarqueVoiture getMarque() {
		return marque;
	}
	public void setMarque(MarqueVoiture marque) {
		this.marque = marque;
	}
	public Set<VoitureOptions> getOptions() {
		return options;
	}
	public void setOptions(Set<VoitureOptions> options) {
		this.options = options;
	}
	
}
