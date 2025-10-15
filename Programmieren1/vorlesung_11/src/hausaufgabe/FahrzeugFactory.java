package hausaufgabe;

import java.util.Scanner;

public class FahrzeugFactory {
    public void createFahrzeug() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Fahrzeugtyp (Auto/Motorrad/Lkw): ");
        String fahrzeugTyp = sc.nextLine();
        try {
            if (!(fahrzeugTyp.equals("Auto") || fahrzeugTyp.equals("Motorrad") || fahrzeugTyp.equals("Lkw"))) {
                throw new Exception("Ungueltige Fahrzeugtyp! Bitte noch einmal eingeben!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public static void main(String[] args) {
        FahrzeugFactory fahrzeugFactory = new FahrzeugFactory();
    }
}
