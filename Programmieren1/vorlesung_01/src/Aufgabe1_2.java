//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
public class Aufgabe1_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Geben Sie die erste Zahl ein: ");
        int ersteZahl = sc.nextInt();
        System.out.print("Geben Sie die zweite Zahl ein: ");
        int zweiteZahl = sc.nextInt();
        sc.close();
        int result = ersteZahl + zweiteZahl;
        System.out.println("Die SUmme der beiden Zahlen ist " + result);
    }
}