package Partie;

import Bajtocja.Okreg;
import Kandydaci.Kandydat;
import Wyborcy.Wyborca;

import java.util.List;
import java.util.Random;

public class Zachlanna extends Partia {
    public Zachlanna(String n, int b) {
        nazwa = n;
        budzet = b;
    }

    private static int nowaWartoscCechy(int staraWartosc, int dzialanie) {
        int wynik = Math.min(staraWartosc + dzialanie, 100);
        wynik = Math.max(wynik, -100);
        return wynik;
    }

    public boolean wykonajDzialanie(List<Okreg> okregiWyborcze, List<int[]> dostepneDzialnia) {
        Random ktory = new Random();
        Okreg rozpatrywanyOkreg = okregiWyborcze.get(ktory.nextInt(okregiWyborcze.size() - 1));
        int najlepszaZmiana = Integer.MIN_VALUE;
        int[] najlepszedzialanie = null;
        Wyborca najlepszyWyborca = null;
        int aktualnaSumaPrzed;
        int aktualnaSumaPo;
        int wagaWyborcy;
        List<Integer> aktualneCechy;
        for (Wyborca w : rozpatrywanyOkreg.podajWyborcow()) {
            aktualneCechy = w.podajCechy();
            if (aktualneCechy != null) {
                for (int[] dzialanie : dostepneDzialnia) {
                    aktualnaSumaPrzed = 0;
                    aktualnaSumaPo = 0;
                    for (Kandydat k : rozpatrywanyOkreg.podajKandydatow()) {
                        for (int i = 0; i < aktualneCechy.size(); i++) {
                            wagaWyborcy = aktualneCechy.get(i);
                            aktualnaSumaPrzed += k.podajCechy().get(i)*wagaWyborcy;
                            aktualnaSumaPo += k.podajCechy().get(i)*nowaWartoscCechy(wagaWyborcy, dzialanie[i]);
                        }
                        if (aktualnaSumaPo - aktualnaSumaPrzed > najlepszaZmiana) {
                            najlepszyWyborca = w;
                            najlepszedzialanie = dzialanie;
                        }
                    }
                }
            }
        }
        if (najlepszyWyborca != null && najlepszedzialanie != null) {
            int cenaDzialania = najlepszyWyborca.kosztDzialania(najlepszedzialanie);
            if (budzet - cenaDzialania >= 0) {
                najlepszyWyborca.wykonajDzialanie(najlepszedzialanie);
                budzet -= cenaDzialania;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Partia kopiuj() {
        return new Zachlanna(String.valueOf(nazwa), budzet);
    }
}
