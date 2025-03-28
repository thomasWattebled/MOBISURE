package com.example.devis.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TypeAssurance {

	VOITURE,MOTO,VELO,VACANCES,PROFESSIONNELLE;
	
	@JsonCreator
    public static TypeAssurance fromString(String value) {
        return TypeAssurance.valueOf(value.toUpperCase());
    }
	
}
