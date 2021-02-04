package problemPokryciaZbiorow;

public abstract class Skladnik {
    protected int pierwszy;
    public abstract int ilePokrywa(boolean[] zajete, int dlugosc);
    public abstract void pokryj(boolean[] zajete, int dlugosc);
}
