package Partie;

import Bajtocja.Okreg;
import Wyborcy.Wyborca;

import java.util.List;

public class Rozrzutna extends Partia {
    public Rozrzutna(String n, int b) {
        nazwa = n;
        budzet = b;
    }

    public boolean wykonajDzialanie(List<Okreg> okregiWyborcze, List<int[]> dostepneDzialnia) {
        int najwyzszyKoszt = Integer.MIN_VALUE;
        int[] najdrozszeDzialanie = null;
        Wyborca najdrozszyWyborca = null;
        int aktualnyKoszt;
        for (Okreg o: okregiWyborcze) {
            for (Wyborca w: o.podajWyborcow()) {
                for (int[] t: dostepneDzialnia) {
                    aktualnyKoszt = w.kosztDzialania(t);
                    if (aktualnyKoszt > 0 && aktualnyKoszt > najwyzszyKoszt && budzet - aktualnyKoszt > 0) {
                        najdrozszyWyborca = w;
                        najwyzszyKoszt = aktualnyKoszt;
                        najdrozszeDzialanie = t;
                    }
                }
            }
        }
        if (najdrozszyWyborca != null && najdrozszeDzialanie != null) {
            najdrozszyWyborca.wykonajDzialanie(najdrozszeDzialanie);
            budzet -= najwyzszyKoszt;
            return true;
        } else {
            return false;
        }
    }

    public Partia kopiuj() {
        return new Rozrzutna(String.valueOf(nazwa), budzet);
    }
}
