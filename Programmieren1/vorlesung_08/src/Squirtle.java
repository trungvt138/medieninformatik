public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", Type.WATER, 40);
        addAttack(new Attack("Water Gun", 15, Type.WATER));
    }
}
