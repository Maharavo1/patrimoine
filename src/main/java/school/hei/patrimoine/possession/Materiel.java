package school.hei.patrimoine.possession;

import school.hei.patrimoine.NotImplemented;

import java.time.Duration;
import java.time.Instant;

public final class Materiel extends Possession {
  private final double tauxDAppreciationAnnuelle;

  public Materiel(String nom, Instant t, int valeurComptable, double tauxDAppreciationAnnuelle) {
    super(nom, t, valeurComptable);
    this.tauxDAppreciationAnnuelle = tauxDAppreciationAnnuelle;
  }

    @Override
    public int valeurComptableFuture(Instant tFutur) {
        int nombreDeJourDansUneAnnee = 365;
        Duration duration = Duration.between(t,tFutur);
        double differenceEnAnnee = duration.toDays() / nombreDeJourDansUneAnnee ;
        return (int) (tauxDAppreciationAnnuelle * valeurComptable * differenceEnAnnee) + valeurComptable;
    }

  @Override
  public Possession projectionFuture(Instant tFutur) {
    throw new NotImplemented();
  }

}
