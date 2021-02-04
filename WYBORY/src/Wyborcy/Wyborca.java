package Wyborcy;
import Kandydaci.Kandydat;

import java.util.List;
import java.util.Scanner;

public abstract class Wyborca {
    protected String imie;
    protected String nazwisko;
    protected int numerOkregu;

    public int podajNumerOkregu() {
        return numerOkregu;
    }

    public List<Integer> podajCechy() {
        return null;
    }

    //Zwraca kandydata z listy, którego wybierze wyborca
    public abstract Kandydat oddajGlos(List<Kandydat> kandydaci);
    
    public abstract Wyborca kopiuj();

    //Podaje zmianę wektora wag, w przypadku braku takiej możliwości (bazowo), zwraca 0
    public int kosztDzialania(int[] dzialanie) {
        return 0;
    }

    //Jeśli to możliwe (w szczególności wyborca ma jakiekolwiek wagi) mienia wagi wyborcy zgodnie z podanym działaniem
    //W przeciwnym razie zwraca fałsz
    public boolean wykonajDzialanie(int[] dzialanie) {
        return false;
    }

    //Zwraca odpowiedni rodzaj wyborcy z wczytanego z wejścia (scanner)
    public static Wyborca wczytajWyborce(Scanner wejscie, int liczbaCech) {
        String imieTymczasowe = wejscie.next();
        String nazwiskoTymczasowe = wejscie.next();
        int numerTymczasowy = wejscie.nextInt();
        int rodzajWyborcy = wejscie.nextInt();
        switch (rodzajWyborcy) {
            case 1:
                return new ElektoratPartyjny(wejscie, imieTymczasowe, nazwiskoTymczasowe, numerTymczasowy);
            case 2:
                return new ElektoratKandydata(wejscie, imieTymczasowe, nazwiskoTymczasowe, numerTymczasowy);
            case 3:
                return new MinJednocechowy(wejscie, imieTymczasowe, nazwiskoTymczasowe, numerTymczasowy);
            case 4:
                return new MaxJednocechowy(wejscie, imieTymczasowe, nazwiskoTymczasowe, numerTymczasowy);
            case 5:
                return new WszechstronnyDowolny(wejscie, liczbaCech, imieTymczasowe, nazwiskoTymczasowe, numerTymczasowy);
            case 6:
                return new MinJednocechowyPartyjny(wejscie, imieTymczasowe, nazwiskoTymczasowe, numerTymczasowy);
            case 7:
                return new MaxJednocechowyPartyjny(wejscie, imieTymczasowe, nazwiskoTymczasowe, numerTymczasowy);
            case 8:
                return new WszechstronnyPartyjny(wejscie, liczbaCech, imieTymczasowe, nazwiskoTymczasowe, numerTymczasowy);
            default:
                return null;
        }
    }

    public String toString() {
        return imie + " " + nazwisko;
    }
}

