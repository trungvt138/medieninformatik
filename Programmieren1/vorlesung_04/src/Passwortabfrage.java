import java.util.Scanner;

public class Passwortabfrage {
    public static void main(String[] args) {
        final String passwort = "Java";
        Scanner input = new Scanner(System.in);
        int count = 1;
        do {
            System.out.printf("Passwort (%d. Versuch): ", count++);
            if (input.nextLine().equals(passwort)) {
                System.out.println("Zutritt  gewährt!");
                break;
            }
        } while (count <= 3);
        input.close();

        if (count > 3) {
            System.out.println("Zutritt verweigert");
        }
    }
}
