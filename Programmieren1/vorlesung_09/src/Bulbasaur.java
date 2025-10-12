public class Bulbasaur extends Pokemon {
    protected Bulbasaur(String name, int hp) {
        super(name, Type.GRASS);
    }

    public Bulbasaur() {
        this("Bulbasaur", 40);
        addAttack(new Attack("Vine Whip", 15, Type.GRASS));
    }
}
