package problemPokryciaZbiorow;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Wejscie {
    private Scanner skaner;
    public Dana wczytaj() {
        if (!skaner.hasNextInt()) {
            return null;
        } else {
            int pomocniczy = skaner.nextInt();
            if (pomocniczy == 0) {
                return new Dana(new Zbior(), null);//turbo pusty
            } else if (pomocniczy < 0) {
                return new Dana(null, new Zapytanie(pomocniczy, skaner.nextInt()));
            } else {
                Zbior odpowiedz = new Zbior();
                while (pomocniczy != 0) {
                    List<Integer> argumenty = new LinkedList<>();
                    argumenty.add(pomocniczy);
                    if (skaner.hasNextInt()) {
                        pomocniczy = skaner.nextInt();
                    }
                    while (pomocniczy < 0 && skaner.hasNextInt()) {
                        argumenty.add(pomocniczy);
                        pomocniczy = skaner.nextInt();
                    }
                    switch (argumenty.size()) {
                        case 1:
                            odpowiedz.dodajSkladnik(new Element(argumenty.get(0)));
                            break;
                        case 2:
                            odpowiedz.dodajSkladnik(new Nieskonczony(argumenty.get(0), argumenty.get(1)));
                            break;
                        case 3:
                            odpowiedz.dodajSkladnik(new Skonczony(argumenty.get(0), argumenty.get(1), argumenty.get(2)));
                            break;
                        default:
                            System.out.print("Zbyt dużo ujemnych argumentów\n");
                            return null;
                    }
                }
                return new Dana(odpowiedz, null);
            }
        }
    }

    public Wejscie() {
        skaner = new Scanner(System.in);
    }
}
