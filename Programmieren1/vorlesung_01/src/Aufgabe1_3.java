import java.util.Scanner;

public class Aufgabe1_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Geben Sie die erste Fließkommazahl ein: ");
        float ersteZahl = sc.nextFloat();
        System.out.print("Geben Sie die zweite Fließkommazahl ein: ");
        float zweiteZahl = sc.nextFloat();
        sc.close();
        float result = ersteZahl / zweiteZahl;
        System.out.printf("Die Division der beiden Zahlen ergibt %.2f", result);
    }
}
