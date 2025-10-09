import java.util.Scanner;

public class Aufgabe2_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final int min = 0;
        final int max = 100;
        //min
        int minRange = (int)(Math.random() * (max - min + 1) + min);
        int maxRange = (int)(Math.random() * (max - minRange + 1) + minRange);

        System.out.printf("Geben Sie eine beliebige Ganzzahl zwischen %d und %d ein: ",minRange, maxRange);
        int number = input.nextInt();
        input.close();
        System.out.println(number >= minRange && number <= maxRange);
    }
}
