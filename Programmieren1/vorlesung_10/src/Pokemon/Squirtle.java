package Pokemon;

public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Pokemon.Squirtle", Type.WATER);
        addAttack(new Attack("Water Gun", 15, Type.WATER));
    }

    @Override
    public void speak() {
        System.out.println("Squirttt!");
    }
}
