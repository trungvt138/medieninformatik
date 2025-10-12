//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Pokemon p1 = new Pokemon("Charmander", Type.FIRE, 40);
        Pokemon p2 = new Pokemon("Squirtle", Type.WATER, 40);
        Trainer t1 = new Trainer("Trung");
        Trainer t2 = new Trainer("Hanh");
        t1.catchPokemon(p1);
        t2.catchPokemon(p2);
        while (t1.isReadyToFight() && t2.isReadyToFight()) {
            t1.fight(t2);
            t2.fight(t1);
        }
    }
}