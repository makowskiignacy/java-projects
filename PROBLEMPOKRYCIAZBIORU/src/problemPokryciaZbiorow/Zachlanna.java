package problemPokryciaZbiorow;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Zachlanna extends Heurystyka {
    public Zachlanna(List<Zbior> zbr, Zapytanie zap) {
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
        int iloscZbirow = zbiory.size();
        List<Integer> listaIndeksow = new LinkedList<>();
        List<Integer> wynik = new LinkedList<>();
        for (int i = 0; i < iloscZbirow; i++) {
            listaIndeksow.add(i);
        }
        int j = 0;
        Zbior aktualnyZbior;
        int najlepszyIndeks;
        int najlepszePokrycie;
        int aktualnePokrycie;
        while (j < iloscZbirow && !this.czyPokryty()) {
            najlepszyIndeks = -1;
            najlepszePokrycie = 0;
            for (int k = 0; k < listaIndeksow.size(); k++) {
                aktualnyZbior = zbiory.get(listaIndeksow.get(k));
                aktualnePokrycie = aktualnyZbior.ilePokrywa(pokryte, -zapytanie.getKoniec());
                if (aktualnePokrycie > najlepszePokrycie) {
                    najlepszePokrycie = aktualnePokrycie;
                    najlepszyIndeks = k;
                }
            }
            if (najlepszyIndeks != -1) {
                aktualnyZbior = zbiory.get(listaIndeksow.get(najlepszyIndeks));
                aktualnyZbior.pokryj(pokryte, -zapytanie.getKoniec());
                wynik.add(listaIndeksow.get(najlepszyIndeks)+1);
                listaIndeksow.remove(najlepszyIndeks);
            }
            j++;
        }
        if (this.czyPokryty()) {
            Collections.sort(wynik);
            int dlugosc = wynik.size();
            for (int l = 0; l < dlugosc; l++) {
                System.out.print(wynik.get(l));
                if (l != dlugosc - 1) {
                    System.out.print(" ");
                }
            }
        } else {
            System.out.print(0);
        }
        System.out.println();
    }
}
