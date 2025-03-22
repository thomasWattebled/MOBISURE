package com.example.devis.Enum.DevisVehicule;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UtilisationVehicule {

	PERSONNEL,PROFESSIONNELLE;
	
	@JsonCreator
    public static UtilisationVehicule fromString(String value) {
        return UtilisationVehicule.valueOf(value.toUpperCase());
    }
}
