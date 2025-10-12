public class Charmander extends Pokemon {
    protected Charmander() {
        super("Charmander", Type.FIRE, 40);
        addAttack(new Attack("Ember", 15, Type.FIRE));
    }

    public void takeDamage(Attack attack) {
        switch (attack.getType()) {
            case FIRE, GRASS, ICE, STEEL, BUG, FAIRY:
                super.takeDamage(attack.getDamage()/2);
                System.out.println("It was not very effective...");
                break;
            case WATER, GROUND, ROCK:
                super.takeDamage(attack.getDamage()*2);
                System.out.println("It was super effective!");
                break;
            default:
                super.takeDamage(attack.getDamage());
                break;
        }
    }
}
