package hausaufgabe;

public class Lkw extends Fahrzeug {
    private double ladegewicht;
    private int achsen;

    public Lkw(String hersteller, String modell, int baujahr, double kilometerstand, double ladegewicht, int achsen) {
        super(hersteller, modell, baujahr, kilometerstand);
        this.ladegewicht = ladegewicht;
        this.achsen = achsen;
    }

    @Override
    public void start() {
        System.out.printf("LKW %s mit %d Achsen staret den Motor.\n", modell, achsen);
    }

    @Override
    public void infoAusgeben() {
        System.out.println("Hersteller " + hersteller);
        System.out.println("Modell " + modell);
        System.out.println("Baujahr " + baujahr);
        System.out.println("Kilometerstand " + kilometerstand);
        System.out.println("Ladegewicht " + ladegewicht);
        System.out.println("Achsen " + achsen);
    }

    @Override
    public String getFahrzeugTyp() {
        return this.getClass().getName();
    }

    public String toString() {
        return "LKW " + super.toString() + " Ladegewicht " + ladegewicht + " Achsen " + achsen;
    }
}
