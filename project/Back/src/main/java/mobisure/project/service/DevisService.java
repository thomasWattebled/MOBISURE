package mobisure.project.service;

import org.springframework.stereotype.Service;
import mobisure.project.entity.Voyage;

@Service
public class DevisService {

    public double calculerDevis(Voyage voyage) {
        double coutBaseKm = voyage.isProfessionnel() ? 40.0 : 50.0;
        double coutBaseCO2 = voyage.isProfessionnel() ? 40.0 : 50.0;

        // Compute multipliers
        double multiplicateurKm = 1 + ((voyage.getDistance() - 1000) / 1000.0) * 0.05;
        double multiplicateurCO2 = 1 + (voyage.getEmissionCO2() - 1) * 0.05;

        // Compute base cost
        double prixDistance = coutBaseKm * multiplicateurKm;
        double prixCO2 = coutBaseCO2 * multiplicateurCO2;

        // Compute total price based on travel duration
        double prixTotal = (prixDistance + prixCO2) * voyage.getDureeSemaines();

        // Apply extra multiplier for non-business trips
        if (!voyage.isProfessionnel()) {
            prixTotal *= calculerMultiplicateurVoyageurs(voyage.getNombreVoyageurs());
        }

        return prixTotal;
    }

    private double calculerMultiplicateurVoyageurs(int nombreVoyageurs) {
        double multiplicateur = 1.0;
        for (int i = 2; i <= nombreVoyageurs; i++) {
            if (i <= 3) {
                multiplicateur *= 1.05;
            } else if (i <= 5) {
                multiplicateur *= 1.1;
            } else if (i == 6) {
                multiplicateur *= 1.1;
            } else {
                multiplicateur *= 1.25;
            }
        }
        return multiplicateur;
    }
}
