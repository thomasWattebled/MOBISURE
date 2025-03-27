package com.example.devis.Enum.Options;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VeloOptions {

	PROTECTION_VANDALISME("Protection contre le vandalisme",8.0),
	ASSISTANCE_CREVAISON("Assistance crevaison",5.0);
	
	private String option;
	private double prix;
	
	private VeloOptions(String option, double prix) {
		this.option = option;
		this.prix = prix;
	}

	@JsonValue
	public String getOption() {
		return option;
	}
	
	public double getPrix() {
		return this.prix;
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
