import java.util.Scanner;

public class Aufgabe2_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String num = String.format("%8s", Integer.toBinaryString(154)).replace(' ', '0');
        System.out.println("Anfangszustand: " + num);
        System.out.print("Wähle Schalter (1-8): ");
        int schalter = input.nextInt();
        input.close();

        //Shift 1 bit to the schalter position and then flip the bit using xor (^ operator)
        String result = String.format("%8s", Integer.toBinaryString(154 ^ (1 << schalter-1))).replace(' ', '0');
        System.out.println("Endzustand: " + result);
    }
}
