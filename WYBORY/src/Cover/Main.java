package Cover;

import Bajtocja.Bajtocja;
import Wczytanie.Wczytaj;

public class Main {
    /*
    Obsługa interfejsu:
    Klasa Wczytaj służy do wczytania danych potrzebnych do przeprowadzenia symulacji
    Z tych samych danych kożystają kolejne symujacje
    Do wczytania danych należy użyć metody wczytajBajtocje()
    Klasa Bajtocja jest symulatorem, tworzy się ją podając pierwszą literę metody przeliczania głosu i zainicjowany obiekt Wczytaj
    Żeby przeprowadzić symulaję należy użyć metody przeprowadzWybory()
     */
    public static void main(String[] args) {
        Wczytaj daneWejsciowe = new Wczytaj();
        daneWejsciowe.wczytajBajtocje();

        Bajtocja wyboryDHonta = new Bajtocja('D', daneWejsciowe);
        wyboryDHonta.przeprowadzWybory();

        Bajtocja wyboryHareaNiemayera = new Bajtocja('H', daneWejsciowe);
        wyboryHareaNiemayera.przeprowadzWybory();

        Bajtocja wyborySainteLague = new Bajtocja('S', daneWejsciowe);
        wyborySainteLague.przeprowadzWybory();
    }
}
