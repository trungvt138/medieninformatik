import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        String name = input.nextLine();
//        double geschwindigkeit = input.nextDouble();
//        input.close();
        Pferd jolly = new Pferd("Jolly Jumper", 1.0);
        System.out.println(jolly.name);
        System.out.println(jolly.geschwindigkeit);
        System.out.println(jolly.strecke);
        System.out.println(jolly.hunger);

        Pferd wendy = new Pferd("Wendy", 1.3);
        jolly.traben();
        wendy.traben();
    }
}
