package ZamianaGlosow;

import Kandydaci.Kandydat;
import Partie.Partia;

import java.util.List;

public class SainteLague extends Metoda {
    public List<Kandydat> zamienGlosy(List<Kandydat> k, List<Partia> p, int m) {
        this.inicjalizujMetoda(k, p, m);
        Kandydat tymczasowy;
        for (int i = 0; i < liczbaPartii; i++) {
            for (int j = 0; j < liczbaMandatow; j++) {
                tymczasowy = kandydaciPartii.get(i).get(j);
                ilorazyKandydatow.add(new IlorazWyborczy(tymczasowy, glosyPartii.get(i)/(float)(2*j +1)));
            }
        }
        return this.zwrocNajlepszychKandydatow();
    }

    public String toString() {
        return "Metoda Sainte-Laguë\n";
    }
}
