package school.hei.patrimoine.cas.pro3;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;

public class BakoCas extends Cas {

    public BakoCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
        super(ajd, finSimulation, possesseurs);
    }

    @Override
    protected Devise devise() {
        return Devise.MGA;
    }

    @Override
    protected String nom() {
        return "Cas de Bako";
    }

    @Override
    protected void init() {

    }

    @Override
    protected void suivi() {

    }

    @Override
    public Set<Possession> possessions() {
        var bni = new Compte("BNI", ajd, new Argent(2_000_000, devise()));
        var bmoi = new Compte("BMOI", ajd, new Argent(625_000, devise()));
        var coffre = new Compte("Coffre maison", ajd, new Argent(1_750_000, devise()));

        // Salaire mensuel le 2
        new FluxArgent("Salaire", bni,
                LocalDate.of(2025, Month.MAY, 2),
                LocalDate.of(2025, Month.DECEMBER, 2),
                2,
                new Argent(2_125_000, devise()));

        // Virement épargne le 3
        new FluxArgent("Épargne", bmoi,
                LocalDate.of(2025, Month.MAY, 3),
                LocalDate.of(2025, Month.DECEMBER, 3),
                3,
                new Argent(200_000, devise()),
                bni);

        // Dépenses vie courante le 1
        new FluxArgent("Dépenses courantes", bni,
                LocalDate.of(2025, Month.MAY, 1),
                LocalDate.of(2025, Month.DECEMBER, 1),
                1,
                new Argent(-700_000, devise()));

        // Loyer le 26
        new FluxArgent("Loyer colocation", bni,
                LocalDate.of(2025, Month.APRIL, 26),
                LocalDate.of(2025, Month.DECEMBER, 26),
                26,
                new Argent(-600_000, devise()));

        var laptop = new Materiel(
                "Ordinateur portable",
                ajd,
                ajd,
                new Argent(3_000_000, devise()),
                -0.12
        );
        return Set.of(bni, bmoi, coffre, laptop);

    }
}
