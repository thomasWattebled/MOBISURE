package com.example.devis.service;

import com.example.devis.model.Voyage;
import org.springframework.stereotype.Service;
import com.example.devis.Enum.Motorisation;


@Service
public class DevisService {

    public double calculerDevis(Voyage voyage) {
        double basePrix = 0;

        // 🚗 Facteur selon la marque du véhicule
        switch (voyage.getMarqueVehicule()) {
            case BMW, MERCEDES, FORD, AUDI -> basePrix = 1.2;
            case VOLKSWAGEN, TOYOTA, NISSAN, HONDA -> basePrix = 1.1;
            default -> basePrix = 1.0;
        }

        // 🔌 Facteur selon la motorisation
        if (voyage.getMotorisation() == Motorisation.ELECTRIQUE) {
            basePrix *= 0.85;
        }

        // ⏳ Facteur selon la durée
        switch (voyage.getDureeSemaines()) {
            case 1 -> basePrix *= 1.4;
            case 2 -> basePrix *= 1.3;
            case 4 -> basePrix *= 1.2;
            case 24 -> basePrix *= 1.1;
            case 52 -> basePrix *= 1.0;
        }

        // 🌍 Facteur selon la distance et CO2
        if ("VACANCES".equalsIgnoreCase(voyage.getTypeVoyage())) {
            basePrix *= 1 + ((voyage.getDistanceKm() - 1000) / 1000.0) * 0.1;
            basePrix *= 1 + ((voyage.getEmissionCO2() - 1) * 0.05);
        } else if ("PROFESSIONNEL".equalsIgnoreCase(voyage.getTypeVoyage())) {
            basePrix *= 1 + ((voyage.getDistanceKm() - 1000) / 1000.0) * 0.1;
            basePrix *= 1 + ((voyage.getEmissionCO2() - 1) * 0.05);
        }

        // 👥 Facteur selon le nombre de voyageurs
        if (voyage.getNombreVoyageurs() >= 2 && voyage.getNombreVoyageurs() <= 3) {
            basePrix *= 1 + (1.05 * (voyage.getNombreVoyageurs() - 1));
        } else if (voyage.getNombreVoyageurs() >= 4 && voyage.getNombreVoyageurs() <= 6) {
            basePrix *= 1 + (1.1 * (voyage.getNombreVoyageurs() - 3));
        } else if (voyage.getNombreVoyageurs() > 6) {
            basePrix *= 1 + (1.25 * (voyage.getNombreVoyageurs() - 6));
        }

        return basePrix * 50; // Appliquer le prix de base selon le type de voyage
    }
}
