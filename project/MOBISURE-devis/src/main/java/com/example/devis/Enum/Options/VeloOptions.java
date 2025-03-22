package com.example.devis.Enum.Options;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VeloOptions {

	PROTECTION_VANDALISME("Protection contre le vandalisme"),
	ASSISTANCE_CREVAISON("Assistance crevaison");
	
	private String option;

	private VeloOptions(String option) {
		this.option = option;
	}

	@JsonValue
	public String getOption() {
		return option;
	}
	
	
	@JsonCreator
    public static VeloOptions fromString(String value) {
        for (VeloOptions options : VeloOptions.values()) {
            if (options.option.equalsIgnoreCase(value)) {
                return options;
            }
        }
        throw new IllegalArgumentException("Option inconnue : " + value);
    }
	
}
