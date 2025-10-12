import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String name;
    private int lvl;
    private int exp;
    public Type type;
    private int health;
    private List<Attack> allAttacks;
    public Trainer trainer;

    public Pokemon(String name, Type type, int hp) {
        this.name = name;
        this.type = type;
        this.health = hp;
        allAttacks = new ArrayList<>();
        allAttacks.add(new Attack("Tacke", 10, Type.NORMAL));
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void addHealth(int value) {
        this.health += value;
    }

    void takeDamage(int damage) {
        this.health -= damage;
    }

    public void doDamage(Pokemon other) {
        Attack randomAttack = this.allAttacks.get((int) (Math.random() * allAttacks.size()));
        other.takeDamage(randomAttack.damage);
    }

    public String toString() {
        return this.name + " " + this.type + " " + this.lvl + " " + this.exp;
    }

    private class Attack {
        private String name;
        private int damage;
        private Type type;

        public Attack(String name, int damage, Type type) {
            this.name = name;
            this.damage = damage;
            this.type = type;
        }

        public String toString() {
            return this.name + " " + this.type + " " + this.damage;
        }
    }
}
