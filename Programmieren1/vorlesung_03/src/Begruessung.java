import java.util.Scanner;

public class Begruessung {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Ihr Vorname: ");
        String vorname = input.nextLine();
        System.out.print("Ihr Nachname: ");
        String nachname = input.nextLine();
        System.out.print("Ihr Geschlecht (m/w): ");
        char geschlecht = input.next().charAt(0);
        System.out.print("Darf ich Sie duzen? (j/n): ");
        char choice = input.next().charAt(0);

        if (choice == 'j') {
            System.out.printf("Hallo %s, ich heisse Dich willkommen!", vorname);
        } else {
            if (geschlecht == 'm') {
                System.out.printf("Guten Tag Herr %s, ich heisse Sie willkommen!", nachname);
            } else {
                System.out.printf("Guten Tag Frau %s, ich heisse Sie willkommen!", nachname);
            }
        }
    }
}
