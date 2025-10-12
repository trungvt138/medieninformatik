public class Ivysaur extends Bulbasaur {
    private String name;
    public Ivysaur() {
        super("Ivysaur", 60);
        addAttack(new Attack("Razor Leaf", 20, Type.GRASS));
    }
}
