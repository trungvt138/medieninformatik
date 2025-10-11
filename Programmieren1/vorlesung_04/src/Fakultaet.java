import java.util.Scanner;

public class Fakultaet {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Zahl, deren Fakultaet berechnet werden soll: ");
        int n = input.nextInt();
        input.close();
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        System.out.printf("Die Fakultaet der Zahl %d betraegt %d", n, result);

    }
}
