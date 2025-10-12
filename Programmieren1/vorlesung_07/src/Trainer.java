import java.util.ArrayList;
import java.util.List;

public class Trainer {
    public String name;
    private List<Pokemon> allMyPokemons;
    public Pokemon activePokemon;

    public Trainer(String name) {
        this.name = name;
        this.allMyPokemons = new ArrayList<>();
    }

    public void catchPokemon(Pokemon pokemon) {
        allMyPokemons.add(pokemon);
        pokemon.trainer = this;
        if (this.activePokemon == null) {
            callPokemon();
        }
    }

    void callPokemon() {
        Pokemon pokemon = allMyPokemons.getFirst();
        if (pokemon.getHealth() > 0) {
            activePokemon = pokemon;
        }
    }

    public void fight(Trainer enemy) {
        if (isReadyToFight() && enemy.isReadyToFight()) {
            this.activePokemon.doDamage(enemy.activePokemon);

            if(!enemy.isReadyToFight()) {
                System.out.printf("Trainer %s: You won Trainer %s!\n", name, enemy.name);
            }
        } else {
            System.out.printf("Trainer %s: You lost Trainer %s!\n", name, enemy.name);
        }
    }

    public boolean isReadyToFight() {
        if (allMyPokemons.isEmpty()) {
            return false;
        }
        for (Pokemon p : allMyPokemons) {
            if (p.getHealth() > 0) {
                return true;
            }
        }
        return false;
    }
}
