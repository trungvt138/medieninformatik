public class Charmander extends Pokemon {
    protected Charmander() {
        super("Charmander", Type.FIRE);
        addAttack(new Attack("Ember", 15, Type.FIRE));
    }
}
