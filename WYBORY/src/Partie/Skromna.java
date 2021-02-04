package Partie;

import Bajtocja.Okreg;
import Wyborcy.Wyborca;

import java.util.List;

public class Skromna extends Partia {
    public Skromna(String n, int b) {
        nazwa = n;
        budzet = b;
    }

    public boolean wykonajDzialanie(List<Okreg> okregiWyborcze, List<int[]> dostepneDzialnia) {
        int najtanszyKoszt = Integer.MAX_VALUE;
        int[] najtanszeDzialanie = null;
        Wyborca najtanszyWyborca = null;
        int aktualnyKoszt;
        for (Okreg o: okregiWyborcze) {
            for (Wyborca w: o.podajWyborcow()) {
                for (int[] t: dostepneDzialnia) {
                    aktualnyKoszt = w.kosztDzialania(t);
                    if (aktualnyKoszt > 0 && aktualnyKoszt < najtanszyKoszt && budzet - aktualnyKoszt > 0) {
                        najtanszyWyborca = w;
                        najtanszyKoszt = aktualnyKoszt;
                        najtanszeDzialanie = t;
                    }
                }
            }
        }
        if (najtanszyWyborca != null && najtanszeDzialanie != null) {
            najtanszyWyborca.wykonajDzialanie(najtanszeDzialanie);
            budzet -= najtanszyKoszt;
            return true;
        } else {
            return false;
        }
    }

    public Partia kopiuj() {
        return new Skromna(String.valueOf(nazwa), budzet);
    }
}
