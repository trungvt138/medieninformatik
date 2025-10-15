package Pokemon;

public class Bulbasaur extends Pokemon {
    protected Bulbasaur(String name, int hp) {
        super(name, Type.GRASS);
    }

    public Bulbasaur() {
        this("Pokemon.Bulbasaur", 40);
        addAttack(new Attack("Vine Whip", 15, Type.GRASS));
    }

    @Override
    public void speak() {
        System.out.println("Bulba");
    }
}
