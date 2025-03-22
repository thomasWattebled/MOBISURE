package com.example.devis.entity.vehicule;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.entity.Assurance;

@Document
public class Velo extends Assurance{

	private Motorisation electrique;
	
	public Velo() {}

	public Velo(Long clientId,Motorisation electrique) {
		
		super(clientId,TypeAssurance.VELO);
		this.electrique = electrique;
	}

	public Motorisation getElectrique() {
		return electrique;
	}

	public void setElectrique(Motorisation electrique) {
		this.electrique = electrique;
	}
	
}
