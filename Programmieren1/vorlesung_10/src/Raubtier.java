public abstract class Raubtier extends Tier {
    public Raubtier(String name, int alter, double gewicht) {
        super(name, alter, gewicht);
    }

    public abstract void jagen();
}
