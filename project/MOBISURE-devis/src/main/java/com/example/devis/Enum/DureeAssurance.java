package com.example.devis.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DureeAssurance {
    SEMAINE("1 semaine"),
    DEUX_SEMAINES("2 semaines"),
    UN_MOIS("1 mois"),
    SIX_MOIS("6 mois"),
    UN_AN("1 an");

	
	private final String label;

    DureeAssurance(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static DureeAssurance fromString(String value) {
        for (DureeAssurance duree : DureeAssurance.values()) {
            if (duree.label.equalsIgnoreCase(value)) {
                return duree;
            }
        }
        throw new IllegalArgumentException("Durée inconnue : " + value);
    }
	
}
    
