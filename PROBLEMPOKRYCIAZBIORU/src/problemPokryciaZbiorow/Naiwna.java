package problemPokryciaZbiorow;

import java.util.LinkedList;
import java.util.List;

public class Naiwna extends Heurystyka {
    public Naiwna(List<Zbior> zbr, Zapytanie zap) {
        zbiory = zbr;
        zapytanie = zap;
        pokryte = new boolean[-zap.getKoniec()];
        for (boolean b : pokryte) {
            b = false;
        }
    }

    private boolean czyPokryty() {
        boolean wynik = true;
        for (boolean b : pokryte) {
            wynik = wynik && b;
        }
        return wynik;
    }

    public void pokryjZbior() {
        int iloscZbiorow = zbiory.size();
        int i = 0;
        List<Integer> wynik = new LinkedList<>();
        while (i < iloscZbiorow && !this.czyPokryty()) {
            Zbior rozpatrywany = zbiory.get(i);
            if (rozpatrywany.ilePokrywa(pokryte, -zapytanie.getKoniec()) > 0) {
                rozpatrywany.pokryj(pokryte, -zapytanie.getKoniec());
                wynik.add(i+1);
            }
            i++;
        }
        if (this.czyPokryty()) {
            int dlugosc = wynik.size();
            for (int j = 0; j < dlugosc; j++) {
                System.out.print(wynik.get(j));
                if (j != dlugosc - 1) {
                    System.out.print(" ");
                }
            }
        } else {
            System.out.print(0);
        }
        System.out.println();
    }
}
