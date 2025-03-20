package com.example.devis.request;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.Motorisation;

public class VeloRequest {

	private Long clientId;
    private TypeAssurance type;
    private Motorisation motorisation;
    
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
	
}
