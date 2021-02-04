package Bajtocja;

import Kandydaci.Kandydat;
import Partie.Partia;
import Wyborcy.Wyborca;
import ZamianaGlosow.Metoda;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Okreg {
    protected List<Integer> numery;
    protected int liczbaWyborcow;
    protected List<Wyborca> mieszkancy;
    protected List<Kandydat> kandydaci;

    public Okreg(int numer, int liczba, List<Wyborca> listaWyborcow, List<Kandydat> listaKandytow) {
        liczbaWyborcow = liczba;
        mieszkancy = listaWyborcow;
        kandydaci = listaKandytow;
        numery = new LinkedList<>();
        numery.add(numer);
    }

    private Okreg(List<Integer> num, int lWy, List<Wyborca> mesz, List<Kandydat> kan) {
        numery = num;
        liczbaWyborcow = lWy;
        mieszkancy = mesz;
        kandydaci = kan;
    }

     public Okreg kopiuj() {
         List<Integer> noweNumery =  new LinkedList<>(numery);
         List<Wyborca> nowiMieszkancy = new LinkedList<>();
         List<Kandydat> nowiKandydaci = new LinkedList<>();
         for (Wyborca w: mieszkancy) {
             nowiMieszkancy.add(w.kopiuj());
         }
         for (Kandydat k : kandydaci) {
             nowiKandydaci.add(k.kopiuj());
         }
         return new Okreg(noweNumery, liczbaWyborcow, nowiMieszkancy, nowiKandydaci);
     }

    public List<Wyborca> podajWyborcow() {
        return mieszkancy;
    }

    public List<Kandydat> podajKandydatow() {
        return kandydaci;
    }

    public static Okreg wczytajOkreg(int numer, Scanner wejscie) {
        int wyborcy = wejscie.nextInt();
        List<Wyborca> lW = new LinkedList<>();
        List<Kandydat> lK = new LinkedList<>();
        return new Okreg(numer, wyborcy, lW, lK);
    }

    public void wczytajWyborcow(Scanner wejscie, int liczbaCech) {
        for (int i = 0; i < liczbaWyborcow; i++) {
            mieszkancy.add(Wyborca.wczytajWyborce(wejscie, liczbaCech));
        }
    }

    public void wczytajKandydatow(Scanner wejscie, int liczbaPartii, int liczbaCech) {
        int liczbaKandydatow = liczbaWyborcow*liczbaPartii/10;
        for (int i = 0; i < liczbaKandydatow; i++) {
            kandydaci.add(new Kandydat(wejscie, liczbaCech));
        }
    }

    public static Okreg polaczOkregi(Okreg a, Okreg b) {
        a.numery.addAll(b.numery);
        a.kandydaci.addAll(b.kandydaci);
        a.mieszkancy.addAll(b.mieszkancy);
        a.liczbaWyborcow += b.liczbaWyborcow;
        return a;
    }

    //Nadaje głosy kandydatom na których zagłosowali wyborcy
    //Wypisuje każdą parę wyborca -> kandydat (jawność głowsowania)
    public void przeprowadzGlosowanie() {
        System.out.print("Numer okregu ");
        for (int n : numery) {
            System.out.print(n + " ");
        }
        System.out.print("\n");
        Kandydat kartaWyborcza;
        for (Wyborca w : mieszkancy) {
            kartaWyborcza = w.oddajGlos(kandydaci);
            kartaWyborcza.dodajGlos();
            System.out.print(w.toString() + " " + kartaWyborcza.toStringShort() + "\n");
        }
        for (Kandydat k : kandydaci) {
            System.out.print(k.oglosWynikGlosowania());
        }
    }

    //Nadaje poszczególnym partiom mantaty w zależności od wyniku głowsowania
    //Wypisuje wyniki poszczególnych kandydatów
    public void przeliczGlosy(char rodzajOrdynacji, List<Partia> partie) {
        Metoda sposobPrzeliczenia = Metoda.utworzMentode(rodzajOrdynacji);
        List<Kandydat> wynikWyborow = sposobPrzeliczenia.zamienGlosy(kandydaci, partie, liczbaWyborcow/10);
        for (Kandydat k : kandydaci) {
            System.out.println(k.toStringLong());
        }
        for (Kandydat k : wynikWyborow) {
            for (Partia p : partie) {
                if (p.podajNazwe().equals(k.podajPartie())) {
                    p.nadajMandat();
                }
            }
        }
    }


}
