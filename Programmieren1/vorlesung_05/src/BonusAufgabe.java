import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BonusAufgabe {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("== Namensliste ==");
        System.out.println("1. Namen hinzufuegen");
        System.out.println("2. Namen entfernen");
        System.out.println("3. Namen suchen");
        System.out.println("4. Namen sortieren");
        System.out.println("5. Alle Namen anzeigen");
        System.out.println("6. Beenden");
        int wahl;
        List<String> namen = new ArrayList<>();
        do {
            System.out.print("Ihre Wahl: ");
            wahl = input.nextInt();
            switch (wahl) {
                case 1:
                    System.out.print("Hinzufuegenen Namen: ");
                    String newName = input.next();
                    namen.add(newName);
                    System.out.printf("Namen erfolgreich hinzugefuegt: %s\n", newName);
                    break;
                case 2:
                    System.out.print("Zu entferntenen Namen: ");
                    String removeName = input.next();
                    if(namen.remove(removeName)) {
                        System.out.printf("Namen erfolgreich entfernt: %s\n", removeName);
                    } else {
                        System.out.println("Liste erhaelt diesen Namen nicht");
                    };
                    break;
                case 3:
                    System.out.print("Zu gesuchten Namen: ");
                    String findName = input.next();
                    if(namen.contains(findName)) {
                        System.out.printf("%s steht %d. in der Liste\n", findName, namen.indexOf(findName)+1);
                    } else {
                        System.out.println("Liste erhaelt diesen Namen nicht");
                    };
                    break;
                case 4:
                    namen.sort(null);
                    System.out.println("Namenliste erfolgreich sortiert");
                    break;
                case 5:
                    System.out.print("Alle Namen anzeigen: ");
                    for (String s : namen) {
                        System.out.printf("%s ", s);
                    }
                    System.out.println();
                    break;
                case 6:
                    input.close();
                    break;
                default:
                    System.out.println("Fehler!");
            }

        }  while (wahl != 6);


    }
}
