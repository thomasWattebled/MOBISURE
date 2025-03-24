package com.example.devis.Enum.Options;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VoitureOptions {

	ASSISTANCE_ZERO_KM("Assistance zéro km"),
	VEHICULE_REMPLACEMENT("Véhicule de remplacement"),
	BRIS_DE_GLACE("Bris de glace");
	
	private final String option;

	private VoitureOptions(String option) {
		this.option = option;
	}
	
	@JsonValue
    public String getOption() {
        return option;
    }
	
	@JsonCreator
    public static VoitureOptions fromString(String value) {
        for (VoitureOptions options : VoitureOptions.values()) {
            if (options.option.equalsIgnoreCase(value)) {
                return options;
            }
        }
        throw new IllegalArgumentException("Option inconnue : " + value);
    }
	
}
