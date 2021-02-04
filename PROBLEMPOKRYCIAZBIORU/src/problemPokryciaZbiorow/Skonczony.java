package problemPokryciaZbiorow;

public class Skonczony extends Skladnik {
    private int roznica;
    private int koniec;

    public Skonczony(int p, int r, int k) {
        pierwszy = p;
        roznica = r;
        koniec = k;
    }

    public int ilePokrywa(boolean[] zajete, int dlugosc) {
        int wynik = 0;
        if (pierwszy <= dlugosc && -koniec >= 1) {
            int i = pierwszy - 1;
            while (i < -koniec && i < dlugosc) {
                if (i >= 0 && !zajete[i]) {
                    wynik++;
                }
                i -= roznica;
            }
        }
        return wynik;
    }

    public void pokryj(boolean[] zajete, int dlugosc) {
        if (pierwszy <= dlugosc && -koniec >= 1) {
            int i = pierwszy - 1;
            while (i < -koniec && i < dlugosc) {
                if (i >= 0) {
                    zajete[i] = true;
                }
                i -= roznica;
            }
        }
    }
}
