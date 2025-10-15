package hausaufgabe;

public abstract class Fahrzeug {
    protected String hersteller;
    protected String modell;
    protected int baujahr;
    protected double kilometerstand;
    public static int anzahlFahrzeug = 0;

    public Fahrzeug(String hersteller, String modell, int baujahr, double kilometerstand) {
        this.hersteller = hersteller;
        this.modell = modell;
        this.baujahr = baujahr;
        this.kilometerstand = kilometerstand;
        anzahlFahrzeug++;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public double getKilometerstand() {
        return kilometerstand;
    }

    public void setKilometerstand(double kilometerstand) {
        this.kilometerstand = kilometerstand;
    }

    public abstract void start();

    public abstract void infoAusgeben();

    public void fahren(double kilometer) {
        kilometerstand += kilometer;
    }

    public abstract String getFahrzeugTyp();

    public static int getAnzahlFahrzeug() {
        return anzahlFahrzeug;
    }

    @Override
    public String toString() {
        return hersteller + " " + modell + " " + baujahr + " " + kilometerstand;
    }
}
