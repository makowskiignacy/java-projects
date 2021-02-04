package Partie;

import Bajtocja.Okreg;

import java.util.List;

public abstract class Partia {
    protected String nazwa;
    protected int budzet;
    protected int liczbaMandatow = 0;

    public String podajNazwe() {
        return nazwa;
    }

    //Pozwala dodać parti mandat
    public void nadajMandat() {
        liczbaMandatow++;
    }

    public String toString() {
        return "Partia: " + nazwa + " zdobyła " + liczbaMandatow + " mandatów";
    }

    //Wykonuje na odpowiednim kandydacie działanie zgodnie z przyjętą strategią
    /*
    Wykonuje na odpowiednim kandydacie działanie zgodnie z przyjętą strategią
    Zwraca pradę jeśli działanie zostało wykonane,
    fałsz jeśli nie znalazła działania do wykonania lub skończył się budżet
    (partie najpierw wybierają działanie a następnie sprawdzają koszt)
     */
    public abstract boolean wykonajDzialanie(List<Okreg> okregiWyborcze, List<int[]> dostepneDzialnia);

    public static Partia stworzPartie(String nazwa, int budzet, char strategia) {
        switch (strategia) {
            case 'R':
                return new Rozrzutna(nazwa, budzet);
            case 'S':
                return new Skromna(nazwa, budzet);
            case 'W':
                return new Wlasna(nazwa, budzet);
            case 'Z':
                return new Zachlanna(nazwa, budzet);
            default:
                return null;
        }
    }

    public abstract Partia kopiuj();
}
