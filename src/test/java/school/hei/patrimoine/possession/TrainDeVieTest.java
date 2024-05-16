package school.hei.patrimoine.possession;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainDeVieTest {
  @Test
  void train_de_vie_est_finance_par_compte_courant() {
    var au13mai24 = Instant.parse("2024-05-13T00:00:00.00Z");
    var compteCourant = new Argent("Compte courant", au13mai24, 600_000);

    var aLOuvertureDeHEI = Instant.parse("2021-10-26T00:00:00.00Z");
    var aLaDiplomation = Instant.parse("2024-12-26T00:00:00.00Z");
    var vieEstudiantine = new TrainDeVie(
        "Ma super(?) vie d'etudiant",
        500_000,
        aLOuvertureDeHEI,
        aLaDiplomation,
        compteCourant,
        1);

    var test = Instant.parse("2022-12-12T00:00:00.00Z");
    assertEquals(
            100_000,
            vieEstudiantine.projectionFuture(test).getFinancePar().getValeurComptable());
    //TODO: assert something useful
  }

  @Test
  void un_train_de_vie_financé_par_argent() {
    var au13mai24 = Instant.parse("2024-05-13T00:00:00.00Z");
    var financeur = new Argent("Espèces", au13mai24, 400_000);

    var dateDeDebut = Instant.parse("2023-10-26T00:00:00.00Z");
    var dateDeFin = Instant.parse("2024-10-26T00:00:00.00Z");
    var depensesMensueles = 4500;
    var dateDePonction = 1;
    var trainDeVie = new TrainDeVie("vie courante",depensesMensueles,dateDeDebut,dateDeFin, financeur, dateDePonction);
    assertEquals(financeur, trainDeVie.getFinancePar());
    assertEquals(depensesMensueles, trainDeVie.getDepensesMensuelle());
    assertEquals(dateDeDebut, trainDeVie.getDebut());
    assertEquals(dateDeFin, trainDeVie.getFin());
    assertEquals(dateDePonction, trainDeVie.getDateDePonction());
  }
}