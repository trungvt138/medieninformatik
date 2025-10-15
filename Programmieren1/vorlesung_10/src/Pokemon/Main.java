package Pokemon;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Pokemon p1 = new Charmander();
        Pokemon p2 = new Squirtle();
        Pokemon p3 = new Bulbasaur();
        Trainer t1 = new Trainer("Trung");
        Trainer t2 = new Trainer("Hanh");
        t1.catchPokemon(p1);
        t2.catchPokemon(p2);
        t1.catchPokemon(p3);
        while (t1.isReadyToFight() && t2.isReadyToFight()) {
            t1.fight(t2);
            t2.fight(t1);
        }
    }
}