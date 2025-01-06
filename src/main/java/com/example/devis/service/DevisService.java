package com.example.devis.service;

import org.springframework.stereotype.Service;
import com.example.devis.model.Voyage;

@Service
public class DevisService {

    public double calculerDevis(Voyage voyage) {
        double multiplicateur = switch (voyage.getFormule()) {
            case "Formule 1" -> 1.1;
            case "Formule 2" -> 1.2;
            case "Formule 3" -> 1.3;
            default -> 1.0;
        };
        return voyage.getCoutBase() * multiplicateur * voyage.getNombreVoyageurs();
    }
}
