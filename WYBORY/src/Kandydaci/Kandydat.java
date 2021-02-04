package Kandydaci;

import Partie.Partia;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Kandydat implements Comparable<Kandydat> {
    protected String imie;
    protected String nazwisko;
    protected int numerOkregu;
    protected String nazwaPartii;
    protected int pozycjaNaLiscie;
    protected List<Integer> cechy;
    protected int zebraneGlosy;

    public int compareTo(Kandydat o) {
        return Integer.compare(o.zebraneGlosy, zebraneGlosy);
    }

    public List<Integer> podajCechy() {
        return cechy;
    }

    public String podajPartie() {
        return nazwaPartii;
    }

    //Generator kandydata z wejścia
    public Kandydat(Scanner wejscie, int liczbaCech) {
        imie = wejscie.next();
        nazwisko = wejscie.next();
        numerOkregu = wejscie.nextInt();
        nazwaPartii = wejscie.next();
        pozycjaNaLiscie = wejscie.nextInt();
        cechy = new LinkedList<>();
        zebraneGlosy = 0;
        for (int i = 0; i < liczbaCech; i++) {
            cechy.add(wejscie.nextInt());
        }
    }

    //Pozwala na dodanie głosu kandydatowi
    public void dodajGlos() {
        zebraneGlosy++;
    }

    public int podajGlosy() {
        return zebraneGlosy;
    }

    public int podajPozycjeNaLiscie() {
        return pozycjaNaLiscie;
    }

    public String toStringShort() {
        return imie + " " + nazwisko;
    }

    public String toStringLong() {
        return imie + " " + nazwisko + " " + nazwaPartii + " " + pozycjaNaLiscie + " " + zebraneGlosy;
    }

    public String oglosWynikGlosowania() {
        return imie + " " + nazwisko + " " + pozycjaNaLiscie + " " + zebraneGlosy + "\n";
    }

    private Kandydat(String imi, String naz, int nOk, String nPr, int pNL, List<Integer> ech, int zGl) {
        imie = imi;
        nazwisko = naz;
        numerOkregu = nOk;
        nazwaPartii = nPr;
        pozycjaNaLiscie = pNL;
        cechy = ech;
        zebraneGlosy = zGl;
    }

    public Kandydat kopiuj() {
        List<Integer> noweCechy = new LinkedList<>(cechy);
        return new Kandydat(String.valueOf(imie), String.valueOf(nazwisko), numerOkregu, nazwaPartii, pozycjaNaLiscie, noweCechy, zebraneGlosy);
    }
}
