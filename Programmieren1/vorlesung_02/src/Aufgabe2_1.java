import java.util.Scanner;

public class Aufgabe2_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Geben Sie die  beliebige Ganzzahl ein: ");
        int ersteZahl = sc.nextInt();
        System.out.print("Geben Sie die weitere Ganzzahl ein: ");
        int zweiteZahl = sc.nextInt();
        sc.close();
        System.out.printf("Die Absolutwerte sind: %d, %d\n", Math.abs(ersteZahl), Math.abs(zweiteZahl));
        System.out.printf("Die kleinere Zahl ist: %d\n", Math.min(ersteZahl, zweiteZahl));
        System.out.printf("Beide Zahlen multipliziert ergeben: %d\n", ersteZahl * zweiteZahl);
    }
}
