package problemPokryciaZbiorow;

import java.util.LinkedList;
import java.util.List;

public class ProblemPokryciaZbiorow {
    Wejscie wejscie;
    List<Zbior> zbiory;

    public ProblemPokryciaZbiorow() {
        wejscie = new Wejscie();
        zbiory = new LinkedList<>();
    }

    private void pokryj(Zapytanie z) {
        switch (z.getAlgorytm()) {
            case 1:
                Dokladna rozwiazanieDokladne = new Dokladna(zbiory, z);
                rozwiazanieDokladne.pokryjZbior();
                break;
            case 2:
                Zachlanna rozwiazanieZachlanne = new Zachlanna(zbiory, z);
                rozwiazanieZachlanne.pokryjZbior();
                break;
            case 3:
                Naiwna rozwiazanieNaiwne = new Naiwna(zbiory, z);
                rozwiazanieNaiwne.pokryjZbior();
                break;
        }
    }

    public void wykonaj() {
        Dana d = wejscie.wczytaj();
        while (d != null) {
            if (d.czyZapytanie()) {
                this.pokryj(d.getZapytanie());
            } else if (d.czyZbior()) {
                zbiory.add(d.getZbior());
            }
            d = wejscie.wczytaj();
        }
    }
}
