public class Gorilla extends Tier {
    public Gorilla(String name, int alter, double gewicht) {
        super(name, alter, gewicht);
    }

    @Override
    public void gibLaut() {

    }

    @Override
    public String toString() {
        return "Gorilla " + name;
    }
}
