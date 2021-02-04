package problemPokryciaZbiorow;

public class Element extends Skladnik {
    public Element(int p) {
        pierwszy = p;
    }

    public String toString() {
        return "{" + pierwszy + "}";
    }

    public int ilePokrywa(boolean[] zajete, int dlugosc) {
        int wynik = 0;
        if (pierwszy >= 1 && pierwszy <= dlugosc) {
            if(!zajete[pierwszy - 1]){
                wynik++;
            }
        }
        return wynik;
    }

    public void pokryj(boolean[] zajete, int dlugosc) {
        if (pierwszy >= 1 && pierwszy <= dlugosc) {
            zajete[pierwszy - 1] = true;
        }
    }
}
