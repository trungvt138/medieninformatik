package hausaufgabe;

public class Auto extends Fahrzeug {
    private int anzahlTueren;
    private boolean istElektro;

    public Auto(String hersteller, String modell, int baujahr, double kilometerstand, int anzahlTueren, boolean istElektro) {
        super(hersteller, modell, baujahr, kilometerstand);
        this.anzahlTueren = anzahlTueren;
        this.istElektro = istElektro;
    }

    @Override
    public void start() {
        String starter;
        if (istElektro) {
            starter = "Strom";
        }
        else {
            starter = "Zündschlüssel";
        }
        System.out.printf("Das Auto %s startet per %s.\n", modell, starter);
    }

    @Override
    public void infoAusgeben() {
        System.out.println("Hersteller " + hersteller);
        System.out.println("Modell " + modell);
        System.out.println("Baujahr " + baujahr);
        System.out.println("Kilometerstand " + kilometerstand);
        System.out.println("AnzahlTueren " + anzahlTueren);
        System.out.println("Ist Elektro " + (istElektro ? "ja": "nein"));
    }

    @Override
    public String getFahrzeugTyp() {
        return this.getClass().getName();
    }

    @Override
    public String toString() {
        return "Auto " + super.toString() + anzahlTueren + " "  + istElektro;
    }
}
