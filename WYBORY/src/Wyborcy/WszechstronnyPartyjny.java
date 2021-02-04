package Wyborcy;

import Kandydaci.Kandydat;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WszechstronnyPartyjny extends Wszechstronny {
    protected String nazwaPartii;

    public WszechstronnyPartyjny(Scanner wejscie, int lCech, String im, String naz, int num) {
        imie = im;
        nazwisko = naz;
        numerOkregu = num;
        ileCech = lCech;
        wagiCech = new LinkedList<>();
        for (int i = 0; i < lCech; i++) {
            wagiCech.add(wejscie.nextInt());
        }
        nazwaPartii = wejscie.next();
    }

    private WszechstronnyPartyjny(String i, String n, int num, List<Integer> wag, int c, String nP) {
        imie = i;
        nazwisko = n;
        numerOkregu = num;
        wagiCech = wag;
        ileCech = c;
        nazwaPartii = nP;
    }

    public Wyborca kopiuj() {
        List<Integer> noweWagi = new LinkedList<>(wagiCech);
        return new WszechstronnyPartyjny(String.valueOf(imie), String.valueOf(nazwisko), numerOkregu, noweWagi, ileCech, String.valueOf(nazwaPartii));
    }

    public Kandydat oddajGlos(List<Kandydat> kandydaci) {
        Kandydat najlepszy = null;
        List<Integer> cechyKandydata;
        int maxSumaWazona = Integer.MIN_VALUE;
        int aktualnaSumaWazona;
        for (Kandydat k : kandydaci) {
            if (k.podajPartie().equals(nazwaPartii)) {
                aktualnaSumaWazona = 0;
                cechyKandydata = k.podajCechy();
                for (int i = 0; i < ileCech; i++) {
                    aktualnaSumaWazona += cechyKandydata.get(i)*wagiCech.get(i);
                }
                if (aktualnaSumaWazona > maxSumaWazona) {
                    najlepszy = k;
                    maxSumaWazona = aktualnaSumaWazona;
                }
            }
        }
        if (najlepszy == null) {
            System.out.println("Elektorat wszechstronny partyjny nie znalazł kandydata");
        }
        return najlepszy;
    }
}
