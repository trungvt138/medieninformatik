import java.util.Scanner;

public class NotenChecker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Geben Sie ihre Note ein: ");
        int note = input.nextInt();
        input.close();

        String noteInString = "";

        switch (note) {
            case 1:
                noteInString = "sehr gut";
                break;
            case 2:
                noteInString = "gut";
                break;
            case 3:
                noteInString = "befriedigend";
                break;
            case 4:
                noteInString = "ausreichend";
                break;
            case 5:
                System.out.println("Sie haben nicht bestanden!");
                break;
            default:
                System.out.println("Fehler");
                break;
        }

        if (note < 5 && note > 0) {
            System.out.printf("Sie habe mit der Note %s bestanden!", noteInString);
        }
    }
}
