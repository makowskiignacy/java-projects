package problemPokryciaZbiorow;

import java.util.LinkedList;
import java.util.List;

public class Zbior {
    private List<Skladnik> listaSkladnikow;

    Zbior() {
        listaSkladnikow = new LinkedList<Skladnik>();
    }
    void dodajSkladnik(Skladnik s) {
        listaSkladnikow.add(s);
    }
    
    public int ilePokrywa(boolean[] zajete, int dlugosc) {
        int wynik = 0;
        boolean[] pomocniczaZajete = new boolean[dlugosc];
        System.arraycopy(zajete, 0, pomocniczaZajete, 0, dlugosc);
        for (Skladnik s : listaSkladnikow) {
            wynik += s.ilePokrywa(pomocniczaZajete, dlugosc);
            s.pokryj(pomocniczaZajete, dlugosc);
        }
        return wynik;
    }

    public void pokryj(boolean[] zajete, int dlugosc) {
        for (Skladnik s : listaSkladnikow) {
            s.pokryj(zajete, dlugosc);
        }
    }
}
