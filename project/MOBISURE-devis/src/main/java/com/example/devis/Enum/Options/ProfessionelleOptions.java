package com.example.devis.Enum.Options;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProfessionelleOptions {

	DOCUMENTS("Perte de documents"),
	MATERIEL("Matériel pro couvert"),
	JURIDIQUE("Assistance juridique à l’étranger");
	
	private String option;

	private ProfessionelleOptions(String option) {
		this.option = option;
	}
	
	@JsonValue
    public String getOption() {
        return option;
    }
	
	@JsonCreator
    public static ProfessionelleOptions fromString(String value) {
        for (ProfessionelleOptions options : ProfessionelleOptions.values()) {
            if (options.option.equalsIgnoreCase(value)) {
                return options;
            }
        }
        throw new IllegalArgumentException("Option inconnue : " + value);
    }
	
}
