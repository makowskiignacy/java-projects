package problemPokryciaZbiorow;

import java.util.List;

public abstract class Heurystyka {
    protected List<Zbior> zbiory;
    protected Zapytanie zapytanie;
    protected boolean[] pokryte;
    public abstract void pokryjZbior();
}
