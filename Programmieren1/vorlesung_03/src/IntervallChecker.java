import java.util.Scanner;

public class IntervallChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Geben Sie eine Ganzzahl innerhalb des Intervalls [10,20] ein: ");
        int ganz = sc.nextInt();
        String ergebnis = "";
        if (ganz > 20) {
            ergebnis ="OBERHALB";
        } else if (ganz < 10) {
            ergebnis ="UNTERHALB";
        } else {
            ergebnis ="INNERHALB";
        }
        System.out.printf("%d liegt %s des Intervalls [10,20]", ganz, ergebnis);
    }
}
