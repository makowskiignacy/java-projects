package Wyborcy;

import Kandydaci.Kandydat;

import java.util.List;
import java.util.Scanner;

public class ElektoratKandydata extends Elektorat {
    protected int pozycjaKandydata;

    public Kandydat oddajGlos(List<Kandydat> kandydaci) {
        for (Kandydat k : kandydaci) {
            if (k.podajPartie().equals(nazwaPartii) && k.podajPozycjeNaLiscie() == pozycjaKandydata) {
                return k;
            }

        }
        System.out.println("Elektorat kandydata nie znalazł kandydata");
        return null;
    }

    public ElektoratKandydata(Scanner wejscie, String im, String naz, int num) {
        imie = im;
        nazwisko = naz;
        numerOkregu = num;
        nazwaPartii = wejscie.next();
        pozycjaKandydata = wejscie.nextInt();
    }

    private ElektoratKandydata(String im, String naz, int num, String partia, int pozycja) {
        imie = im;
        nazwisko = naz;
        numerOkregu = num;
        nazwaPartii = partia;
        pozycjaKandydata = pozycja;
    }

    public Wyborca kopiuj() {
        return new ElektoratKandydata(String.valueOf(imie), String.valueOf(nazwisko), numerOkregu, String.valueOf(nazwaPartii), pozycjaKandydata);
    }
}
