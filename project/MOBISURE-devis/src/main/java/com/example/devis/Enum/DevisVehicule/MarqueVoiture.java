package com.example.devis.Enum.DevisVehicule;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MarqueVoiture {
	
	// Véhicules haut de gamme (facteur 1.2)
    BMW, MERCEDES, FORD, AUDI,

    // Véhicules intermédiaires (facteur 1.1)
    VOLKSWAGEN, TOYOTA, NISSAN, HONDA,

    // Véhicules standards (facteur 1.0)
    RENAULT, PEUGEOT;
	
	@JsonCreator
    public static MarqueVoiture fromString(String value) {
        return MarqueVoiture.valueOf(value.toUpperCase());
    }
	
}
