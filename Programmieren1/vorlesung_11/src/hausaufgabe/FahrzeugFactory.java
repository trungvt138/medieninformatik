package hausaufgabe;

import java.util.Scanner;

public class FahrzeugFactory {
    public void createFahrzeug() throws Exception {
        Scanner sc = new Scanner(System.in);
        Fahrzeug f;
        String fahrzeugTyp;
        while (true) {
            System.out.print("Fahrzeugtyp (Auto/Motorrad/Lkw): ");
            fahrzeugTyp = sc.nextLine();
            try {
                if (!(fahrzeugTyp.equals("Auto") || fahrzeugTyp.equals("Motorrad") || fahrzeugTyp.equals("Lkw"))) {
                    throw new Exception("Ungueltige Fahrzeugtyp! Bitte noch einmal eingeben!");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.print("Hersteller: ");
        String hersteller = sc.nextLine();
        System.out.print("Modell: ");
        String modell = sc.nextLine();
        System.out.print("Baujahr: ");
        int baujahr = sc.nextInt();
        System.out.print("Kilometerstand: ");
        double kilometerstand = sc.nextDouble();

        switch (fahrzeugTyp) {
            case "Auto":
                System.out.print("Anzahl der Tueren: ");
                int anzahlTueren = sc.nextInt();
                System.out.print("Ist Elektro: ");
                boolean elektro = sc.nextBoolean();
                f = new Auto(hersteller, modell, baujahr, kilometerstand, anzahlTueren, elektro);
                break;
            case "Motorrad":
                System.out.print("Hat Beiwagen: ");
                boolean hatBeiwagen = sc.nextBoolean();
                f = new Motorrad(hersteller, modell, baujahr, kilometerstand, hatBeiwagen);
                break;
            case "Lkw":
                System.out.print("Ladegewicht: ");
                double ladegewicht = sc.nextDouble();
                System.out.print("Anzahl der Achsen: ");
                int anzahlAchsen = sc.nextInt();
                f = new Lkw(hersteller, modell, baujahr, kilometerstand, ladegewicht, anzahlAchsen);
                break;
            default:
                throw new Exception("Ungueltige Fahrzeugtyp!");
        }
        sc.close();
        System.out.println("Fahrzeug erfolgreich erzeugt!");
        f.infoAusgeben();
    }
    public static void main(String[] args) {
        FahrzeugFactory fahrzeugFactory = new FahrzeugFactory();
        try {
            fahrzeugFactory.createFahrzeug();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
