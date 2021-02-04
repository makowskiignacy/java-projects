package Wyborcy;

import Kandydaci.Kandydat;

import java.util.List;
import java.util.Scanner;

public class MinJednocechowyPartyjny extends JednocechowyPartyjany {
    public Kandydat oddajGlos(List<Kandydat> kandydaci) {
        Kandydat najlepszyKandydat = null;
        int najlepszaWartoscCechy = Integer.MAX_VALUE;
        int aktualnaWartoscCechy;
        for (Kandydat k : kandydaci) {
            aktualnaWartoscCechy = k.podajCechy().get(numerCechy - 1);
            if (nazwaPartii.equals(k.podajPartie()) && najlepszaWartoscCechy > aktualnaWartoscCechy) {
                najlepszyKandydat = k;
                najlepszaWartoscCechy = aktualnaWartoscCechy;
            }
        }
        if (najlepszyKandydat == null) {
            System.out.println("Elektorat min jednocechowy partyjny nie znalazł kandydata");
        }
        return najlepszyKandydat;
    }

    public MinJednocechowyPartyjny(Scanner wejscie, String im, String naz, int num) {
        imie = im;
        nazwisko = naz;
        numerOkregu = num;
        numerCechy = wejscie.nextInt();
        nazwaPartii = wejscie.next();
    }

    private MinJednocechowyPartyjny(String im, String naz, int num, int cech, String partia) {
        imie = im;
        nazwisko = naz;
        numerOkregu = num;
        numerCechy = cech;
        nazwaPartii = partia;
    }

    public Wyborca kopiuj() {
        return new MinJednocechowyPartyjny(String.valueOf(imie), String.valueOf(nazwisko), numerOkregu, numerCechy, String.valueOf(nazwaPartii));
    }
}
