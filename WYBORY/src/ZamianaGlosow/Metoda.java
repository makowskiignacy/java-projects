package ZamianaGlosow;

import Kandydaci.Kandydat;
import Partie.Partia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Klasa służąca do zamiany głosów na wyniki wyborów w okręgu wyborczym
public abstract class Metoda {
    protected int liczbaMandatow;
    protected int liczbaPartii;
    protected List<Partia> partie;
    protected List<Integer> glosyPartii;
    protected List<List<Kandydat>> kandydaciPartii;
    //Każda metoda przypisuje, jakiś rodzaj wagi w rakach, której segreguje się kandydatów
    protected List<IlorazWyborczy> ilorazyKandydatow;

    public static Metoda utworzMentode(char rodzajMetody) {
        Metoda wynik;
        switch (rodzajMetody) {
            case 'D':
                wynik = new DHondta();
                break;
            case 'H':
                wynik = new HareaNiemeyera();
                break;
            case 'S':
                wynik = new SainteLague();
                break;
            default:
                wynik = null;
        }
        return wynik;
    }

    protected void inicjalizujMetoda(List<Kandydat> k, List<Partia> p, int m) {
        liczbaMandatow = m;
        liczbaPartii = p.size();
        partie = p;
        glosyPartii = new ArrayList<>(liczbaPartii);
        kandydaciPartii = new ArrayList<>(liczbaPartii);
        ilorazyKandydatow = new ArrayList<>(liczbaMandatow*liczbaPartii);
        int indeks = 0;
        Kandydat tymczasowy;
        for (int i = 0; i < liczbaPartii; i++) {
            glosyPartii.add(0);
            kandydaciPartii.add(new ArrayList<>(liczbaMandatow));
            for (int j = 0; j < liczbaMandatow; j++) {
                tymczasowy = k.get(indeks);
                kandydaciPartii.get(i).add(tymczasowy);
                glosyPartii.set(i, glosyPartii.get(i) + tymczasowy.podajGlosy());
                indeks++;
            }
        }
        for (List<Kandydat> list : kandydaciPartii) {
            Collections.sort(list);
        }
    }

    public abstract List<Kandydat> zamienGlosy(List<Kandydat> kandydaci, List<Partia> partie, int liczbaMandatow);

    //Pomocnicza klasa trzymając kandydata i jego wartość iloraz-kandydaci z najwyższą wygrywają wybory
    static class IlorazWyborczy implements Comparable<IlorazWyborczy> {
        protected Kandydat kandydat;
        protected float iloraz;

        public IlorazWyborczy(Kandydat k, float f) {
            kandydat = k;
            iloraz = f;
        }

        public int compareTo(IlorazWyborczy o) {
            return Float.compare(o.iloraz, iloraz);
        }
    }

    protected List<Kandydat> zwrocNajlepszychKandydatow() {
        //Metoda sort sortuje rosnąca zwaracam, więc kandydatów z najwyższym wynikiem
        Collections.sort(ilorazyKandydatow);
        List<Kandydat> wynik = new ArrayList<>(liczbaMandatow);
        for (int i = liczbaMandatow - 1; i >= 0; i--) {
            wynik.add(ilorazyKandydatow.get(i).kandydat);
        }
        return wynik;
    }

    public abstract String toString();
}
