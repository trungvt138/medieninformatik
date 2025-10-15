public class Elefant extends Tier {
    public Elefant(String name, int alter, double gewicht) {
        super(name, alter, gewicht);
    }

    @Override
    public void gibLaut() {

    }

    @Override
    public String toString() {
        return "Elefant " + name;
    }
}
