package Wczytanie;

import Bajtocja.Okreg;
import Partie.Partia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Wczytaj {
    protected List<Okreg> wczytaneOkregi;
    protected List<Okreg> okregi;
    protected List<Integer> indeksyDoScalenia;
    protected List<Partia> wczytanePartie;
    protected List<int[]> dzialania;

    public List<Okreg> podajOkregi() {
        List<Okreg> noweOkregi = new LinkedList<>();
        for (Okreg o : okregi) {
            noweOkregi.add(o.kopiuj());
        }
        return noweOkregi;
    }

    public List<Partia> podajPartie() {
        List<Partia> nowePartie = new LinkedList<>();
        for (Partia p : wczytanePartie) {
            nowePartie.add(p.kopiuj());
        }
        return nowePartie;
    }

    //Nie jest uwzględniona możliwość modyfikacji działa, w takim przypadku będzie pemanentna dla danego wejścia
    public List<int[]> podajDzialania() {
        return dzialania;
    }

    //Wczytuje z wiersza numery indeksów, które są do scalenia
    private void wczytajDoScalenia(String wiersz) {
        indeksyDoScalenia = new LinkedList<>();
        String zmieniony1 = wiersz.replaceAll("\\(", " ");
        String zmieniony2 = zmieniony1.replaceAll("\\)", " ");
        String zmieniony = zmieniony2.replaceAll(",", " ");
        int o1;
        int o2;
        Scanner tymczasowy = new Scanner(zmieniony);
        int liczbaPar = tymczasowy.nextInt();
        for (int i = 0; i < liczbaPar; i++) {
            o1 = tymczasowy.nextInt();
            o2 = tymczasowy.nextInt();
            if (o1 + 1 == o2) {
                indeksyDoScalenia.add(o1);
            }
        }
    }

    private boolean czyPoprawnyWierszI(int n, int p, int d, int c) {
        boolean wynik;
        wynik = n >= 5 && n <= 100;
        wynik = wynik && p >= 1 && p <= 20;
        wynik = wynik && d >= 1 && d <= 15;
        wynik = wynik && c >= 5 && c <= 100;
        return wynik;
    }

    public void wczytajBajtocje() {
        Scanner glowny = new Scanner(System.in);
        Scanner tymczasowy;
        String wiersz;
        //PIERWSZY WIERSZ
        wiersz = glowny.nextLine();
        tymczasowy = new Scanner(wiersz);
        int n = tymczasowy.nextInt(); //liczba podstawowych okręgów wyborczych
        int p = tymczasowy.nextInt(); //liczba partii
        int d = tymczasowy.nextInt(); //liczba możliwych działań
        int c = tymczasowy.nextInt(); //liczba cech kandydatów
        if (!czyPoprawnyWierszI(n, p, d, c)) {
            System.out.print("Niepoprawne dane wejściowe");
            exit(1);
        }
        //DRUGI WIERSZ
        wiersz = glowny.nextLine();
        wczytajDoScalenia(wiersz);
        //LISTY POMOCNICZE DO WCZYTANIA PARTII
        List<String> nazwyPartii = new LinkedList<>();
        List<Integer> budgetyPartii = new LinkedList<>();
        List<Character> strategiePartii = new LinkedList<>();
        wczytanePartie = new LinkedList<>();
        //TRZECI WIERSZ
        wiersz = glowny.nextLine();
        tymczasowy = new Scanner(wiersz);
        for (int i = 0; i < p; i++) {
            nazwyPartii.add(tymczasowy.next());
        }
        //CZWARTY WIERSZ
        wiersz = glowny.nextLine();
        tymczasowy = new Scanner(wiersz);
        for (int j = 0; j < p; j++) {
            budgetyPartii.add(tymczasowy.nextInt());
        }
        //PIĄTY WIERSZ
        wiersz = glowny.nextLine();
        tymczasowy = new Scanner(wiersz);
        for (int k = 0; k < p; k++) {
            strategiePartii.add(tymczasowy.next().charAt(0));
        }
        for (int l = 0; l < p; l++) {
            wczytanePartie.add(Partia.stworzPartie(nazwyPartii.get(l), budgetyPartii.get(l), strategiePartii.get(l)));
        }
        //SZÓSTY WIERSZ
        wiersz = glowny.nextLine();
        tymczasowy = new Scanner(wiersz);
        wczytaneOkregi = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            wczytaneOkregi.add(Okreg.wczytajOkreg(i + 1, tymczasowy));
        }
        //OPISY POSZCZEGÓLNYCH KANDYDATÓW
        for (Okreg o: wczytaneOkregi) {
            o.wczytajKandydatow(glowny, p, c);
        }
        //OPISY POSZCZEGÓLNYCH WYBORCÓW
        for (Okreg o: wczytaneOkregi) {
            o.wczytajWyborcow(glowny, c);
        }
        //SCALENIE OKREGÓW
        okregi = new LinkedList<>();
        int indeks = 0;
        while (indeks < n) {
            if (indeksyDoScalenia.contains(indeks + 1)) {
                okregi.add(Okreg.polaczOkregi(wczytaneOkregi.get(indeks), wczytaneOkregi.get(indeks + 1)));
                indeks++;
            } else {
                okregi.add(wczytaneOkregi.get(indeks));
            }
            indeks++;
        }
        //MOZLIWE DZIAŁANIA
        dzialania = new ArrayList<>(d);
        for (int i = 0; i < d; i++) {
            int[] posrednia = new int[c];
            for (int j = 0; j < c; j++) {
                posrednia[j] = glowny.nextInt();
            }
            dzialania.add(posrednia);
        }
    }
}
