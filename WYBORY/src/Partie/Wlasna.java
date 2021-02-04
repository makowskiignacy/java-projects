package Partie;

import Bajtocja.Okreg;
import Wyborcy.Wyborca;

import java.util.List;
import java.util.Random;

public class Wlasna extends Partia {
    private int[] ulubioneDzialanie;
    private boolean czyZadzialalo;
    private Okreg ulubionyOkreg;
    public Wlasna(String n, int b) {
        nazwa = n;
        budzet = b;
        czyZadzialalo = true;
        ulubioneDzialanie = null;
        ulubionyOkreg = null;
    }

    //Partia losuje okręg i działanie jeśli się sprawdziło powtarza je, nie zmienia cech wyborców, którzy już na nią głosują
    public boolean wykonajDzialanie(List<Okreg> okregiWyborcze, List<int[]> dostepneDzialnia) {
        Random generator = new Random();
        if (ulubioneDzialanie == null || !czyZadzialalo || ulubionyOkreg == null) {
            ulubioneDzialanie = dostepneDzialnia.get(generator.nextInt(dostepneDzialnia.size() - 1));
            ulubionyOkreg = okregiWyborcze.get(generator.nextInt(okregiWyborcze.size() - 1));
        }
        Wyborca aktualnyWyborca = ulubionyOkreg.podajWyborcow().get(0);
        int liczbaWyborcow = ulubionyOkreg.podajWyborcow().size();
        int i = 1;
        boolean czySpozaPartii = !aktualnyWyborca.oddajGlos(ulubionyOkreg.podajKandydatow()).podajPartie().equals(nazwa);
        int kosztDzialania = aktualnyWyborca.kosztDzialania(ulubioneDzialanie);
        while (czySpozaPartii && kosztDzialania > 0 && i < liczbaWyborcow) {
            aktualnyWyborca = ulubionyOkreg.podajWyborcow().get(i);
            czySpozaPartii = !aktualnyWyborca.oddajGlos(ulubionyOkreg.podajKandydatow()).podajPartie().equals(nazwa);
            kosztDzialania = aktualnyWyborca.kosztDzialania(ulubioneDzialanie);
            i++;
        }
        if (kosztDzialania > 0 && budzet - kosztDzialania > 0) {
            aktualnyWyborca.wykonajDzialanie(ulubioneDzialanie);
            czyZadzialalo = aktualnyWyborca.oddajGlos(ulubionyOkreg.podajKandydatow()).podajPartie().equals(nazwa);
            return true;
        } else {
            return false;
        }
    }

    public Partia kopiuj() {
        return new Wlasna(String.valueOf(nazwa), budzet);
    }
}
