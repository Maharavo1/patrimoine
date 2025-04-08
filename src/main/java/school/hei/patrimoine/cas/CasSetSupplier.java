package school.hei.patrimoine.cas;


import school.hei.patrimoine.cas.pro3.TianaCas;
import school.hei.patrimoine.modele.Personne;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static school.hei.patrimoine.modele.Argent.ariary;

public class CasSetSupplier implements Supplier<CasSet> {
  @Override
  public CasSet get() {
    var tiana = new Personne("Tiana");
    var casTiana = new TianaCas(
            LocalDate.of(2025, Month.APRIL, 8),
            LocalDate.of(2026, Month.MARCH, 31),
            Map.of(tiana, 1.0)
    );
    return new CasSet(Set.of(casTiana), ariary(0));
  }
}
