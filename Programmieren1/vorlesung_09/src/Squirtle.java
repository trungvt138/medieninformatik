public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", Type.WATER);
        addAttack(new Attack("Water Gun", 15, Type.WATER));
    }
}
