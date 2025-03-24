package com.example.devis.entity.vehicule;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.Options.VeloOptions;
import com.example.devis.entity.Assurance;

@Document
public class Velo extends Assurance{

	private Motorisation electrique;
	private Set<VeloOptions> options;
	
	public Velo() {}

	public Velo(Long clientId,Motorisation electrique, Set<VeloOptions> options) {
		
		super(clientId,TypeAssurance.VELO);
		this.electrique = electrique;
		this.options = options;
	}

	public Motorisation getElectrique() {
		return electrique;
	}

	public void setElectrique(Motorisation electrique) {
		this.electrique = electrique;
	}

	public Set<VeloOptions> getOptions() {
		return options;
	}

	public void setOptions(Set<VeloOptions> options) {
		this.options = options;
	}
	
}
