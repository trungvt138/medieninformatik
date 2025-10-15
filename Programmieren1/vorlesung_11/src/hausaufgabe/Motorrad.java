package hausaufgabe;

public class Motorrad extends Fahrzeug {
    private boolean hatBeiwagen;
    public Motorrad(String hersteller, String modell, int baujahr, double kilometerstand, boolean hatBeiwagen) {
        super(hersteller, modell, baujahr, kilometerstand);
        this.hatBeiwagen = hatBeiwagen;
    }

    @Override
    public void start() {
        System.out.printf("Motorrad %s wird mit Kickstart gestartet.\n", this.modell);
    }

    @Override
    public void infoAusgeben() {
        System.out.println("Hersteller " + hersteller);
        System.out.println("Modell " + modell);
        System.out.println("Baujahr " + baujahr);
        System.out.println("Kilometerstand " + kilometerstand);
        System.out.println("Hat Beiwagen " + (hatBeiwagen ? "ja": "nein"));
    }

    @Override
    public String getFahrzeugTyp() {
        return this.getClass().getName();
    }

    public String toString() {
        return "Motorrad " + super.toString() + " hatBeiwagen " + hatBeiwagen;
    }
}
