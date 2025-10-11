import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class AbschreibeTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final String muster = "Keep It SimpeL sTupiD";
        System.out.print("Bitte Tippen Sie - " + muster + ": ");
        String tippen = input.nextLine();
        input.close();
        if (tippen.equals(muster)) {
            System.out.println("Die Eingabe war korrekt!");
        } else  {
            System.out.println("Das Eingabe war leider nicht korrekt!");
        }
    }
}