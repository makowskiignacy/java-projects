package problemPokryciaZbiorow;

public class Dana {
    private Zbior zbior;
    private Zapytanie zapytanie;

    public Dana(Zbior zbr, Zapytanie zap){
        zbior = zbr;
        zapytanie = zap;
    }

    public boolean czyZbior() {
        return  zbior != null;
    }

    public boolean czyZapytanie() {
        return zapytanie != null;
    }

    public Zapytanie getZapytanie() {
        return zapytanie;
    }

    public Zbior getZbior() {
        return zbior;
    }

    public String toString() {
        String wynik = "";
        if (zbior != null) {
            wynik += zbior.toString();
        }
        if (zapytanie != null) {
            wynik += zapytanie.toString();
        }
        return wynik;
    }
}
