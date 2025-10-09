import java.util.Scanner;

public class Hausafugabe2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        String name = input.nextLine();
        System.out.println("Nachname: ");
        String nachname = input.nextLine();
        System.out.println("Geschlecht: ");
        String geschlecht = input.nextLine();
        System.out.println("Geburtsdatum: ");
        String geburtsdatum = input.nextLine();
        System.out.println("Beruf: ");
        String beruf = input.nextLine();
        input.close();

        StringBuilder sb = new StringBuilder(name);
        sb.append(" ");
        sb.append(nachname);
        sb.append("; ");
        sb.append(geschlecht);
        sb.append("; geb. ");
        sb.append(geburtsdatum);
        sb.append("; ");
        sb.append(beruf);

        System.out.println(sb.toString());
    }
}
