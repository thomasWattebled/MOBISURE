package com.example.devis.request;

import java.util.Set;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.Options.VeloOptions;

public class VeloRequest {

	private Long clientId;
    private TypeAssurance type;
    private Motorisation motorisation;
    private Set<VeloOptions> options;
    
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
	public Set<VeloOptions> getOptions() {
		return options;
	}
	public void setOptions(Set<VeloOptions> options) {
		this.options = options;
	}
	
}
