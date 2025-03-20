package com.example.devis.request;

import com.example.devis.Enum.DureeAssurance;
import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.MarqueMoto;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.DevisVehicule.UtilisationVehicule;

public class MotoRequest {

	private Long clientId;
    private TypeAssurance type;
    private Motorisation motorisation;
    private Long fabrication;
    private UtilisationVehicule utilisation;
    private DureeAssurance duree;
    private String modele;
    private MarqueMoto marque;
    private String plaque;
    
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
	public MarqueMoto getMarque() {
		return marque;
	}
	public void setMarque(MarqueMoto marque) {
		this.marque = marque;
	}
	public Motorisation getMotorisation() {
		return motorisation;
	}
	public void setMotorisation(Motorisation motorisation) {
		this.motorisation = motorisation;
	}
    
}
