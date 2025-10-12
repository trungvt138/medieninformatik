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
    }

    void callPokemon() {
        Pokemon pokemon = allMyPokemons.getFirst();
        if (pokemon.getHealth() > 0) {
            activePokemon = pokemon;
        }
    }


}
