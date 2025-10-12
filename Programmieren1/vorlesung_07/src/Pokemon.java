public class Pokemon {
    private String name;
    private int lvl;
    private int exp;
    public Type type;
    private int health;
    private int attackDamage;

    public Pokemon(String name, Type type, int hp) {
        this.name = name;
        this.type = type;
        this.health = hp;
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
        other.takeDamage(this.attackDamage);
    }

    public String toString() {
        return this.name + " " + this.type + " " + this.lvl + " " + this.exp;
    }


}
