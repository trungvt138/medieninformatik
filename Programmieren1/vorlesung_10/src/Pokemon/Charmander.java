package Pokemon;

public class Charmander extends Pokemon {
    protected Charmander() {
        super("Pokemon.Charmander", Type.FIRE);
        addAttack(new Attack("Ember", 15, Type.FIRE));
    }

    @Override
    public void speak() {
        System.out.println("Charrr!");
    }
}
