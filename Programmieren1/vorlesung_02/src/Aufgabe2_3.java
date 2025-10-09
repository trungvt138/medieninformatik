import java.util.Scanner;

public class Aufgabe2_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Geben Sie eine beliebige, gerade Ganzzahl ein: ");
        int beliebige = input.nextInt();
        input.close();
        System.out.println(beliebige/2 == 0);
    }
}
