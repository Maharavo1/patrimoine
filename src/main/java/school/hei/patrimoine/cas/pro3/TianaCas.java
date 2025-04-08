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

public class TianaCas extends Cas {

    public TianaCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
        super(ajd, finSimulation, possesseurs);
    }

    @Override
    protected Devise devise() {
        return Devise.MGA;
    }

    @Override
    protected String nom() {
        return "Cas de Tiana";
    }

    @Override
    protected void init() {

    }

    @Override
    protected void suivi() {

    }

    @Override
    public Set<Possession> possessions() {

        var compteBancaire = new Compte("Compte Bancaire", ajd, new Argent(60_000_000, devise()));


        var terrain = new Materiel(
                "Terrain Bâti",
                ajd,
                ajd,
                new Argent(100_000_000, devise()),
                0.10
        );


        new FluxArgent("Dépenses familiales", compteBancaire,
                LocalDate.of(2025, Month.MAY, 1),
                LocalDate.of(2025, Month.DECEMBER, 1),
                1,
                new Argent(-4_000_000, devise()));


        for (int i = 6; i <= 12; i++) {
            new FluxArgent("Dépenses projet", compteBancaire,
                    LocalDate.of(2025, Month.of(i), 5),
                    LocalDate.of(2025, Month.of(i), 5),
                    5,
                    new Argent(-5_000_000, devise()));
        }


        new FluxArgent("Encaissement projet (10%)", compteBancaire,
                LocalDate.of(2025, Month.MAY, 1),
                LocalDate.of(2025, Month.MAY, 1),
                1,
                new Argent(7_000_000, devise()));  // 10% de 70 000 000 Ar

        new FluxArgent("Encaissement projet (90%)", compteBancaire,
                LocalDate.of(2026, Month.JANUARY, 31),
                LocalDate.of(2026, Month.JANUARY, 31),
                31,
                new Argent(63_000_000, devise()));  // 90% restant


        new FluxArgent("Prêt bancaire", compteBancaire,
                LocalDate.of(2025, Month.JULY, 27),
                LocalDate.of(2025, Month.JULY, 27),
                27,
                new Argent(20_000_000, devise()));

        for (int i = 8; i <= 12; i++) {
            new FluxArgent("Remboursement prêt", compteBancaire,
                    LocalDate.of(2025, Month.of(i), 27),
                    LocalDate.of(2025, Month.of(i), 27),
                    27,
                    new Argent(-2_000_000, devise()));
        }

        return Set.of(compteBancaire, terrain);
    }
}
