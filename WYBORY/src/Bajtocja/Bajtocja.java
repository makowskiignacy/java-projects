package Bajtocja;

import Partie.Partia;
import Wczytanie.Wczytaj;
import ZamianaGlosow.Metoda;

import java.util.List;

public class Bajtocja {
    protected List<Okreg> podzial;
    protected List<Partia> partie;
    protected List<int[]> dzialania;
    protected char rodzajOredynacji;

    public Bajtocja(char ordynacja, Wczytaj wczytaneDane) {
        podzial = wczytaneDane.podajOkregi();
        partie = wczytaneDane.podajPartie();
        dzialania = wczytaneDane.podajDzialania();
        rodzajOredynacji = ordynacja;
    }

    //Funkcja pomocnicza do przeprowadzenia kampanii
    //Zakładam, że partie mogą wykonać jeden rodzaj działania wielokrotnie
    private void przeprowadzKampanie() {
        boolean kampaniaTrwa = true;
        while (kampaniaTrwa) {
            kampaniaTrwa = false;
            for (Partia p : partie) {
                kampaniaTrwa = kampaniaTrwa || p.wykonajDzialanie(podzial, dzialania);
            }
        }
    }

    //Pomocnicza, przeprowadza głosowanie dla każdego okręgu
    private void przeprowadzGlosowaniePrzeliczanie() {
        for (Okreg o : podzial) {
            o.przeprowadzGlosowanie();
            o.przeliczGlosy(rodzajOredynacji, partie);
        }
    }

    //Główna funkcja służąca do przeprowadzenia symulacji
    public void przeprowadzWybory() {
        Metoda doWypisaniaNazwy = Metoda.utworzMentode(rodzajOredynacji);
        System.out.print(doWypisaniaNazwy.toString());
        this.przeprowadzKampanie();
        this.przeprowadzGlosowaniePrzeliczanie();
        for (Partia p : partie) {
            System.out.println(p.toString());
        }
    }
}
