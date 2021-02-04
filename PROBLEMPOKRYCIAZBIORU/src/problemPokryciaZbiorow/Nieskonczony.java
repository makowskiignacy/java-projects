package problemPokryciaZbiorow;

public class Nieskonczony extends Skladnik {
    private int roznica;

    public Nieskonczony(int p, int r ) {
        pierwszy = p;
        roznica = r;
    }

    public int ilePokrywa(boolean[] zajete, int n) {
        int wynik = 0;
        if (pierwszy < n) {
            int i = pierwszy - 1;
            while (i < n) {
                if (i >= 0 && !zajete[i]) {
                    wynik++;
                }
                i -= roznica;
            }
        }
        return wynik;
    }

    public void pokryj(boolean[] zajete, int dlugosc) {
        if (pierwszy <= dlugosc) {
            int i = pierwszy - 1;
            while (i < dlugosc) {
                if (i >= 0) {
                    zajete[i] = true;
                }
                i -= roznica;
            }
        }
    }
}
