package com.example.devis.Enum.DevisVehicule;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Motorisation {
    
	ELECTRIQUE,THERMIQUE;
	
	@JsonCreator
    public static Motorisation fromString(String value) {
        return Motorisation.valueOf(value.toUpperCase());
    }
}
