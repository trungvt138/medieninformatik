public abstract class Tier {
    protected String name;
    protected int alter;
    protected double gewicht;

    public Tier(String name, int alter, double gewicht) {
        this.name = name;
        this.alter = alter;
        this.gewicht = gewicht;
    }

    public abstract void gibLaut();

    public abstract String toString();
}
