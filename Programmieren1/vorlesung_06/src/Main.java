import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        double geschwindigkeit = input.nextDouble();
        double strecke = input.nextDouble();
        boolean hunger = input.nextBoolean();

        Pferd p = new Pferd(name,geschwindigkeit,strecke,hunger);
    }
}
