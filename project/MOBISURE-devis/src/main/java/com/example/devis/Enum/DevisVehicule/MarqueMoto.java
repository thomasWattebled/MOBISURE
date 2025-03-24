package com.example.devis.Enum.DevisVehicule;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MarqueMoto {
	
	// Motos haut de gamme (facteur 1.2)
    DUCATI, HARLEY, INDIAN,

    // Motos intermédiaires (facteur 1.1)
    TRIUMPH, KTM,

    // Motos standards (facteur 1.0)
    YAMAHA, HONDA, KAWASAKI, SUZUKI;
	
	@JsonCreator
    public static MarqueMoto fromString(String value) {
        return MarqueMoto.valueOf(value.toUpperCase());
    }
}
