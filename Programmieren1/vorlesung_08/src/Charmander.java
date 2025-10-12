public class Charmander extends Pokemon {
    protected Charmander() {
        super("Charmander", Type.FIRE, 40);
        addAttack(new Attack("Ember", 15, Type.FIRE));
    }
}
