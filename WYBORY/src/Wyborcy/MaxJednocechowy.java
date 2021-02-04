package Wyborcy;

import Kandydaci.Kandydat;

import java.util.List;
import java.util.Scanner;

public class MaxJednocechowy extends Jednocechowy {
    public Kandydat oddajGlos(List<Kandydat> kandydaci) {
        Kandydat najlepszy = null;
        int najlepszaWartoscCechy = Integer.MIN_VALUE;
        int aktualnaWartoscCechy;
        for (Kandydat k: kandydaci) {
            aktualnaWartoscCechy = k.podajCechy().get(numerCechy - 1);
            if (aktualnaWartoscCechy > najlepszaWartoscCechy) {
                najlepszaWartoscCechy = aktualnaWartoscCechy;
                najlepszy = k;
            }
        }
        if (najlepszy == null) {
            System.out.println("Elektorat max jednocechowy nie znalazł kandydata");
        }
        return najlepszy;
    }

    public MaxJednocechowy(Scanner wejscie, String im, String naz, int num) {
        imie = im;
        nazwisko = naz;
        numerOkregu = num;
        numerCechy = wejscie.nextInt();
    }

    private MaxJednocechowy(String im, String naz, int num, int cech) {
        imie = im;
        nazwisko = naz;
        numerOkregu = num;
        numerCechy = cech;
    }

    public Wyborca kopiuj() {
        return new MaxJednocechowy(String.valueOf(imie), String.valueOf(nazwisko), numerOkregu, numerCechy);
    }
}
