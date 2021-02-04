package problemPokryciaZbiorow;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dokladna extends Heurystyka {
    public Dokladna(List<Zbior> zbr, Zapytanie zap) {
        zbiory = zbr;
        zapytanie = zap;
        pokryte = new boolean[-zap.getKoniec()];
        for (boolean b : pokryte) {
            b = false;
        }
    }

    private boolean czyPokryte(boolean[] pola) {
        boolean wynik = true;
        for (boolean b : pola) {
            wynik = wynik && b;
        }
        return wynik;
    }

    private List<Integer> kopiujListe(List<Integer> lista) {
        List<Integer> nowa = new LinkedList<>();
        for (int i : lista) {
            nowa.add(i);
        }
        return nowa;
    }

    private void wyczyscPokryte() {
        for (boolean b : pokryte) {
            b = false;
        }
    }

    private List<Integer> minimum(List<Integer> piorytet, List<Integer> druga) {
        if (piorytet == null && druga == null) {
            return null;
        } else if (druga == null) {
            this.wyczyscPokryte();
            Zbior aktualny;
            for (int i : piorytet) {
                aktualny = zbiory.get(i);
                aktualny.pokryj(pokryte, -zapytanie.getKoniec());
            }
            if (this.czyPokryte(pokryte)){
                return piorytet;
            } else {
                return null;
            }
        } else if (piorytet == null) {
            this.wyczyscPokryte();
            Zbior aktualny;
            for (int i : druga) {
                aktualny = zbiory.get(i);
                aktualny.pokryj(pokryte, -zapytanie.getKoniec());
            }
            if (this.czyPokryte(pokryte)) {
                return druga;
            } else {
                return null;
            }
        } else {
            this.wyczyscPokryte();
            Zbior aktualny;
            for (int i : piorytet) {
                aktualny = zbiory.get(i);
                aktualny.pokryj(pokryte, -zapytanie.getKoniec());
            }
            boolean czyPiorytet = this.czyPokryte(pokryte);
            this.wyczyscPokryte();
            for (int i : druga) {
                aktualny = zbiory.get(i);
                aktualny.pokryj(pokryte, -zapytanie.getKoniec());
            }
            boolean czyDruga = this.czyPokryte(pokryte);
            if (!czyDruga && !czyPiorytet) {
                return null;
            } else if (!czyDruga) {
                return piorytet;
            } else if (!czyPiorytet) {
                return druga;
            } else {
                if (druga.size() < piorytet.size()) {
                    return druga;
                } else {
                    return piorytet;
                }
            }
        }
    }

    private List<Integer> wynikPokrycia(List<Integer> indeksyWynikow, List<Integer> listaIndeksow, boolean[] zajete, int n) {
        if (this.czyPokryte(zajete)) {
            return indeksyWynikow;
        } else if (listaIndeksow.size() == 0) {
            return null;
        } else {
            int aktualnyIndeks = listaIndeksow.get(0);
            listaIndeksow.remove(0);
            List<Integer> indeksyWynikowZ = this.kopiujListe(indeksyWynikow);
            indeksyWynikowZ.add(aktualnyIndeks);
            List<Integer> indeksyWynikowBez = this.kopiujListe(indeksyWynikow);
            List<Integer> listaIndeksowZ = this.kopiujListe(listaIndeksow);
            List<Integer> listaIndeksowBez = this.kopiujListe(listaIndeksow);
            boolean[] zajeteZ = new boolean[n];
            boolean[] zajeteBez = new boolean[n];
            System.arraycopy(zajete, 0, zajeteZ, 0, n);
            Zbior aktualnyZbior = zbiory.get(aktualnyIndeks);
            aktualnyZbior.pokryj(zajeteZ, n);
            System.arraycopy(zajete, 0, zajeteBez, 0, n);
            List<Integer> wynikZ = this.wynikPokrycia(indeksyWynikowZ, listaIndeksowZ, zajeteZ, n);
            List<Integer> wynikBez = this.wynikPokrycia(indeksyWynikowBez, listaIndeksowBez, zajeteBez, n);
            return minimum(wynikZ, wynikBez);
        }
    }
    public void pokryjZbior() {
        List<Integer> indeksy = new LinkedList<>();
        for (int i = 0; i < zbiory.size(); i++) {
            indeksy.add(i);
        }
        List<Integer> wynik = this.wynikPokrycia(new LinkedList<Integer>(), indeksy, pokryte, -zapytanie.getKoniec());
        if (wynik != null) {
            Collections.sort(wynik);
            int dlugosc = wynik.size();
            for (int j = 0; j < dlugosc; j++) {
                System.out.print(wynik.get(j) + 1);
                if (j != dlugosc - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        } else {
            System.out.print(0);
            System.out.println();
        }

    }
}
