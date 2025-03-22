package mobisure.project.Enum;

public enum DureeAssurance {
    SEMAINE(1.4),
    DEUX_SEMAINES(1.3),
    UN_MOIS(1.2),
    SIX_MOIS(1.1),
    UN_AN(1.0);

    private final double facteur;

    DureeAssurance(double facteur) {
        this.facteur = facteur;
    }

    public double getFacteur() {
        return facteur;
    }
}
