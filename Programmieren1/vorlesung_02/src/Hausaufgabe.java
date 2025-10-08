import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Hausaufgabe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Geben Sie die erste Zahl ein: ");
        float ersteZahl = sc.nextFloat();
        System.out.print("Geben Sie die zweite Zahl ein: ");
        float zweiteZahl = sc.nextFloat();
        sc.close();
        System.out.printf("%.1f + %.1f = %.1f\n", ersteZahl, zweiteZahl, ersteZahl + zweiteZahl);
        System.out.printf("%.1f - %.1f = %.1f\n", ersteZahl, zweiteZahl, ersteZahl - zweiteZahl);
        System.out.printf("%.1f * %.1f = %.1f\n", ersteZahl, zweiteZahl, ersteZahl * zweiteZahl);
        System.out.printf("%.1f / %.1f = %.2f\n", ersteZahl, zweiteZahl, ersteZahl / zweiteZahl);
    }
}