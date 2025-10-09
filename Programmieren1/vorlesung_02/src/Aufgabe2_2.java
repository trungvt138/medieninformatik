import java.util.Scanner;

public class Aufgabe2_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Geben Sie eine Temperatur in Grad Celsius ein: ");
        float tempInCelsius = sc.nextFloat();
        sc.close();
        float tempInFahrenheit = tempInCelsius * 1.8f + 32;
        float tempInKelvin = tempInCelsius + 273.15f;
        System.out.printf("%.0f°C entsprechen %.1f°F und %.2fK",tempInCelsius,tempInFahrenheit,tempInKelvin);
    }
}
