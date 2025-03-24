package com.example.devis.Enum.Options;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VacanceOptions {

	ANNULATION("Annulation toutes causes"),
	BAGGAGE("Bagages assurés"),
	FRAIS_MEDICAUX_ETRANGER("Frais médicaux à l’étranger");
	
	private String option;

	private VacanceOptions(String option) {
		this.option = option;
	}
	
	@JsonValue
	public String getOption() {
		return option;
	}
	
	@JsonCreator
    public static VacanceOptions fromString(String value) {
        for (VacanceOptions options : VacanceOptions.values()) {
            if (options.option.equalsIgnoreCase(value)) {
                return options;
            }
        }
        throw new IllegalArgumentException("Option inconnue : " + value);
    }
	
}
