import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Mittelwert {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Anzahl der Messwerte: ");
        int n = input.nextInt();
        float[] arr = new float[n];
        float result = 0;

        for (int i = 0; i < n; i++) {
            System.out.printf("%d. Messwert: ", i+1);
            arr[i] = input.nextFloat();
            result += arr[i];
        }
        input.close();
        result /= n;
        System.out.printf("Mittelwert: %.2f", result);
    }
}