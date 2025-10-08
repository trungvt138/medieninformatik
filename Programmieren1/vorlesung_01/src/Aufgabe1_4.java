import java.util.Scanner;

public class Aufgabe1_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Geben Sie ihren Vornamen ein: ");
        String vorname = sc.next();
        System.out.print("Geben Sie ihren Nachnamen ein: ");
        String nachname = sc.next();
        sc.close();
        System.out.print("Willkommen "+vorname+" "+nachname+"!");
    }
}
