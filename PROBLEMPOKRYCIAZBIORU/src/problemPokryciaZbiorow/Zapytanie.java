package problemPokryciaZbiorow;

public class Zapytanie {
    int koniec;
    int algorytm;

    public int getKoniec() {
        return koniec;
    }

    public int getAlgorytm() {
        return algorytm;
    }

    public Zapytanie(int kon, int alg) {
        koniec = kon;
        algorytm = alg;
    }
}
