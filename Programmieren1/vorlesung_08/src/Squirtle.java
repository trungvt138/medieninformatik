public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", Type.WATER, 40);
        addAttack(new Attack("Water Gun", 15, Type.WATER));
    }

    public void takeDamage(Attack attack) {
        switch (attack.getType()) {
            case FIRE, WATER, ICE, STEEL:
                super.takeDamage(attack.getDamage()/2);
                System.out.println("It was not very effective...");
                break;
            case GRASS, ELECTRIC:
                super.takeDamage(attack.getDamage()*2);
                System.out.println("It was super effective!");
                break;
            default:
                super.takeDamage(attack.getDamage());
                break;
        }
    }
}
