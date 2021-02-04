package Wyborcy;

import Kandydaci.Kandydat;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ElektoratPartyjny extends Elektorat {
    public Kandydat oddajGlos(List<Kandydat> kandydaci) {
        List<Kandydat> kandydaciPartii = new LinkedList<>();
        for (Kandydat k : kandydaci) {
            if (k.podajPartie().equals(nazwaPartii)) {
                kandydaciPartii.add(k);
            }
        }
        Random wybierz = new Random();
        int doWyboru = kandydaciPartii.size() - 1;
        if (doWyboru >= 0) {
            return kandydaciPartii.get(wybierz.nextInt(doWyboru));
        } else {
            System.out.println("Elektorat partyjny nie znalazł kandydata");
            return null;
        }
    }

    public ElektoratPartyjny(Scanner wejscie, String im, String naz, int num) {
        imie = im;
        nazwisko = naz;
        numerOkregu = num;
        nazwaPartii = wejscie.next();
    }

    private ElektoratPartyjny(String im, String naz, int num, String partia) {
        imie = im;
        nazwisko = naz;
        numerOkregu = num;
        nazwaPartii = partia;
    }

    public Wyborca kopiuj() {
        return new ElektoratPartyjny(String.valueOf(imie), String.valueOf(nazwisko), numerOkregu, String.valueOf(nazwaPartii));
    }
}
