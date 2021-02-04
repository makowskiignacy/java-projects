package ZamianaGlosow;

import Kandydaci.Kandydat;
import Partie.Partia;

import java.util.ArrayList;
import java.util.List;

public class HareaNiemeyera extends Metoda {
    List<Float> wspolcznynnkiKomitetow;
    public List<Kandydat> zamienGlosy(List<Kandydat> kandydaci, List<Partia> partie, int liczbaMandatow) {
        this.inicjalizujMetoda(kandydaci, partie, liczbaMandatow);
        wspolcznynnkiKomitetow = new ArrayList<>(liczbaPartii);
        int wszystkieGlosy = 0;
        for (int glosy : glosyPartii) {
            wszystkieGlosy += glosy;
        }
        for (int i = 0; i < liczbaPartii; i++) {
            wspolcznynnkiKomitetow.add((float)glosyPartii.get(i)*liczbaMandatow/wszystkieGlosy);
        }
        float czyMandat;
        for (int i = 0; i < liczbaPartii; i++) {
            for (int j = 0; j < liczbaMandatow; j++) {
                if (wspolcznynnkiKomitetow.get(i) > 1) {
                    wspolcznynnkiKomitetow.add(i, (float)-1);
                    czyMandat = 1;
                } else if (wspolcznynnkiKomitetow.get(i) > 0) {
                    czyMandat = wspolcznynnkiKomitetow.get(i);
                    wspolcznynnkiKomitetow.add(i, (float)-1);
                } else {
                    czyMandat = 0;
                }
                ilorazyKandydatow.add(new IlorazWyborczy(kandydaciPartii.get(i).get(j), czyMandat));
            }
        }
        return this.zwrocNajlepszychKandydatow();
    }

    public String toString() {
        return "Metoda Hare’a-Niemeyera\n";
    }
}
