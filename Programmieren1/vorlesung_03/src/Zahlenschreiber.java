import java.util.Scanner;

public class Zahlenschreiber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Geben Sie eine Ganzzahl zwischen 1 und 5 ein: ");
        int ganz = input.nextInt();
        switch (ganz) {
            case 1:
                System.out.println("Eins");
                break;
            case 2:
                System.out.println("Zwei");
                break;
            case 3:
                System.out.println("Drei");
                break;
            case 4:
                System.out.println("Vier");
                break;
            case 5:
                System.out.println("Fuenf");
                break;
            default:
                System.out.println("Fehler");
        }
    }
}
