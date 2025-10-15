public class Pinguin extends Tier implements Schwimmfaehig {
    public Pinguin(String name, int alter, double gewicht) {
        super(name, alter, gewicht);
    }

    @Override
    public void gibLaut() {

    }

    @Override
    public String toString() {
        return "Pinguin " + name;
    }

    @Override
    public void schwimmen() {

    }
}
