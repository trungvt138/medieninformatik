package Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    public String name;
    private List<Pokemon> allMyPokemons;
    public Pokemon activePokemon;
    public Inventory inventory;

    public Trainer(String name) {
        this.name = name;
        this.allMyPokemons = new ArrayList<>();
        this.inventory = new Inventory(this, 10);
        inventory.add(new Pokeball(1, inventory));
    }

    public boolean catchPokemon(Pokemon pokemon) {
        if (pokemon.myTrainer != null) {
            System.out.printf("Pokemon.Trainer %s: You can't catch other's pokemon\n", this.name);
            return false;
        }
        if (inventory.search("Pokemon.Pokeball") != null) {
            if (inventory.use(new Pokeball(1, inventory))) {
                allMyPokemons.add(pokemon);
                pokemon.myTrainer = this;
                System.out.printf("Pokemon.Trainer %s: %s was caught!\n", this.name, pokemon.getName());
                if (this.activePokemon == null) {
                    callPokemon();
                }
                return true;
            }
        }
        return false;
    }

    void callPokemon() {
        Pokemon pokemon = allMyPokemons.getFirst();
        if (pokemon.getHealth() > 0) {
            activePokemon = pokemon;
        }
        pokemon.speak();
    }

    public void fight(Trainer enemy) {
        if (isReadyToFight() && enemy.isReadyToFight()) {
            this.activePokemon.doDamage(enemy.activePokemon);

            if(!enemy.isReadyToFight()) {
                System.out.printf("Pokemon.Trainer %s: You won Pokemon.Trainer %s!\n", name, enemy.name);
            }
        } else {
            System.out.printf("Pokemon.Trainer %s: You lost Pokemon.Trainer %s!\n", name, enemy.name);
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
