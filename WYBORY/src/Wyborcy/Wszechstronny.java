package Wyborcy;
import java.util.List;

public abstract class Wszechstronny extends Wyborca {
    protected List<Integer> wagiCech;
    protected int ileCech;

    public int kosztDzialania(int[] dzialanie) {
        int suma = 0;
        for (int i = 0; i < ileCech; i++) {
            int staraWartosc = wagiCech.get(i);
            int nowaWartosc = Math.max(-100, staraWartosc + dzialanie[i]);
            nowaWartosc = Math.min(100, nowaWartosc);
            suma += Math.abs(nowaWartosc - staraWartosc);
        }
        return suma;
    }

    public boolean wykonajDzialanie(int[] dzialanie) {
        for (int i = 0; i < ileCech; i++) {
            int staraWartosc = wagiCech.get(i);
            int nowaWartosc = Math.max(-100, staraWartosc + dzialanie[i]);
            nowaWartosc = Math.min(100, nowaWartosc);
            wagiCech.set(i, nowaWartosc);
        }
        return true;
    }

    public List<Integer> podajCechy() {
        return wagiCech;
    }
}
