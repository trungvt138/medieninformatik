package Pokemon;

public class Ivysaur extends Bulbasaur {
    private String name;
    public Ivysaur() {
        super("Pokemon.Ivysaur", 60);
        addAttack(new Attack("Razor Leaf", 20, Type.GRASS));
    }
}
