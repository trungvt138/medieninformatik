package hausaufgabe;

public class Testklasse {
    public static void main(String[] args) {
        Auto a = new Auto("Audi", "Q7", 2025, 0, 4, false);
        Motorrad m = new Motorrad("Yamaha", "YZF-R6R", 2024, 0, false);
        Lkw l = new Lkw("Scania", "S500", 2018, 832000, 18000, 2);

        Fahrzeug[] fahrzeuge = new Fahrzeug[3];
        fahrzeuge[0] = a;
        fahrzeuge[1] = m;
        fahrzeuge[2] = l;

        for (Fahrzeug fahrzeug : fahrzeuge) {
            fahrzeug.start();
            fahrzeug.infoAusgeben();
        }
    }
}
