import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String name;
    private int lvl;
    private int exp;
    public Type type;
    private int health;
    private List<Attack> allAttacks;
    public Trainer myTrainer;

    protected Pokemon(String name, Type type) {
        this.name = name;
        this.type = type;
        this.health = 40;
        allAttacks = new ArrayList<>();
        allAttacks.add(new Attack("Tackle", 10, Type.NORMAL));
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    protected void setLevel(int lvl) {
        this.lvl = lvl;
    }

    public int getLevel() {
        return lvl;
    }

    public int getExp() {
        return exp;
    }

    public List<Attack> getAllAttacks() {
        return allAttacks;
    }

    public void addExp(int exp) {
        this.exp += exp;
    }

    private void checkLevelUp() {

    }

    protected void addAttack(Attack atk) {
        this.allAttacks.add(atk);
    }

    public void addHealth(int value) {
        this.health += value;
    }

    void takeDamage(int damage) {
        this.health -= damage;
        if (health < 0) {
            this.health = 0;
        }
        System.out.printf("Trainer %s: %s took %d dmg, HP left: %d\n",this.myTrainer.name, this.name, damage, this.health);
    }

    public void doDamage(Pokemon other) {
        if (other != null) {
            if (health > 0 && other.health > 0) {
                Attack randomAttack = this.allAttacks.get((int) (Math.random() * allAttacks.size()));
                System.out.printf("Trainer %s: %s used %s!\n",this.myTrainer.name, this.name, randomAttack.name);
                other.takeDamage(randomAttack.calcDamage(other));
            }
        }

    }

    public String toString() {
        return this.name + " " + this.type + " " + this.lvl + " " + this.exp;
    }

    public class Attack {
        protected String name;
        protected int damage;
        protected Type type;
        protected List<Type> effective =  new ArrayList<>();
        protected List<Type> resistant =  new ArrayList<>();
        protected List<Type> effectless =  new ArrayList<>();

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDamage() {
            return damage;
        }

        public void setDamage(int damage) {
            this.damage = damage;
        }

        public Attack(String name, int damage, Type type) {
            this.name = name;
            this.damage = damage;
            this.type = type;

            switch (type) {
                case NORMAL:
                    resistant.addAll(List.of(Type.ROCK, Type.STEEL));
                    effectless.add(Type.GHOST);
                    break;
                case FIRE:
                    effective.addAll(List.of(Type.GRASS, Type.ICE, Type.BUG, Type.STEEL));
                    resistant.addAll(List.of(Type.WATER, Type.FIRE, Type.ROCK, Type.DRAGON));
                    break;
                case WATER:
                    effective.addAll(List.of(Type.FIRE, Type.GROUND, Type.ROCK));
                    resistant.addAll(List.of(Type.WATER, Type.GRASS, Type.DRAGON));
                    break;
                case GRASS:
                    effective.addAll(List.of(Type.WATER, Type.GROUND, Type.ROCK));
                    resistant.addAll(List.of(Type.FIRE, Type.GRASS, Type.DRAGON, Type.POISON, Type.FLYING, Type.BUG, Type.STEEL));
                    break;
                case ELECTRIC:
                    effective.addAll(List.of(Type.WATER, Type.STEEL, Type.FLYING));
                    resistant.addAll(List.of(Type.GRASS, Type.ELECTRIC, Type.DRAGON));
                    effectless.add(Type.GROUND);
                    break;
                case ICE:
                    effective.addAll(List.of(Type.GROUND, Type.GRASS, Type.FLYING, Type.DRAGON));
                    resistant.addAll(List.of(Type.FIRE, Type.WATER, Type.ICE, Type.STEEL));
                    break;
                case FIGHTING:
                    effective.addAll(List.of(Type.NORMAL, Type.ROCK, Type.STEEL, Type.DARK, Type.ICE));
                    resistant.addAll(List.of(Type.POISON, Type.FLYING, Type.PSYCHIC, Type.BUG, Type.FAIRY));
                    effectless.add(Type.GHOST);
                    break;
                case POISON:
                    effective.addAll(List.of(Type.GRASS, Type.FAIRY));
                    resistant.addAll(List.of(Type.POISON, Type.GROUND, Type.PSYCHIC, Type.ROCK, Type.GHOST));
                    effectless.add(Type.STEEL);
                    break;
                case GROUND:
                    effective.addAll(List.of(Type.FIRE, Type.ROCK, Type.ELECTRIC, Type.POISON, Type.STEEL));
                    resistant.addAll(List.of(Type.GRASS, Type.BUG));
                    effectless.add(Type.FLYING);
                    break;
                case FLYING:
                    effective.addAll(List.of(Type.GRASS, Type.FIGHTING, Type.BUG));
                    resistant.addAll(List.of(Type.ELECTRIC, Type.ROCK, Type.STEEL));
                    break;
                case PSYCHIC:
                    effective.addAll(List.of(Type.FIGHTING, Type.POISON));
                    resistant.addAll(List.of(Type.PSYCHIC, Type.STEEL));
                    effectless.add(Type.DARK);
                    break;
                case BUG:
                    effective.addAll(List.of(Type.GRASS, Type.PSYCHIC, Type.DARK));
                    resistant.addAll(List.of(Type.FIRE, Type.FIGHTING, Type.POISON, Type.FLYING, Type.GHOST, Type.STEEL, Type.FAIRY));
                    break;
                case ROCK:
                    effective.addAll(List.of(Type.FIRE, Type.ICE, Type.FLYING, Type.BUG));
                    resistant.addAll(List.of(Type.STEEL, Type.FIGHTING, Type.GROUND));
                    break;
                case STEEL:
                    effective.addAll(List.of(Type.ICE, Type.ROCK, Type.FAIRY));
                    resistant.addAll(List.of(Type.FIRE, Type.WATER, Type.ELECTRIC, Type.STEEL));
                    break;
                case DRAGON:
                    effective.add(Type.DRAGON);
                    resistant.add(Type.STEEL);
                    effectless.add(Type.FAIRY);
                    break;
                case GHOST:
                    effective.addAll(List.of(Type.PSYCHIC, Type.GHOST));
                    resistant.add(Type.DARK);
                    effectless.add(Type.NORMAL);
                    break;
                case DARK:
                    effective.addAll(List.of(Type.PSYCHIC, Type.GHOST));
                    resistant.addAll(List.of(Type.FIGHTING, Type.DARK, Type.FAIRY));
                    break;
                case FAIRY:
                    effective.addAll(List.of(Type.FIGHTING, Type.DRAGON, Type.DARK));
                    resistant.addAll(List.of(Type.FIRE, Type.POISON, Type.STEEL));
                    break;
            }
        }

        public int calcDamage(Pokemon other) {
            int calcDamage = damage;
            Type defender = other.type;
            if (effective.contains(defender)) {
                calcDamage *= 2;
                System.out.println("It was super effective!");
            }
            else if (resistant.contains(defender)) {
                calcDamage /= 2;
                System.out.println("It was not very effective...");
            }
            else if (effectless.contains(defender)) {
                calcDamage = 0;
                System.out.println("It doesn't effect " + other.getName());
            }
            return calcDamage;
        }

        public String toString() {
            return this.name + " " + this.type + " " + this.damage;
        }
    }

    public class Grass_Attack extends Attack {
        public Grass_Attack(String name, int damage) {
            super(name, damage, Type.GRASS);
            effective.addAll(List.of(Type.WATER, Type.GROUND, Type.ROCK));
            resistant.addAll(List.of(Type.FIRE, Type.GRASS, Type.DRAGON, Type.POISON, Type.FLYING, Type.BUG, Type.STEEL));
        }
    }

    public class Fire_Attack extends Attack {
        public Fire_Attack(String name, int damage) {
            super(name, damage, Type.FIRE);
            effective.addAll(List.of(Type.GRASS, Type.ICE, Type.BUG, Type.STEEL));
            resistant.addAll(List.of(Type.WATER, Type.FIRE, Type.ROCK, Type.DRAGON));
        }
    }

    public class Water_Attack extends Attack {
        public Water_Attack(String name, int damage) {
            super(name, damage, Type.WATER);
            effective.addAll(List.of(Type.FIRE, Type.GROUND, Type.ROCK));
            resistant.addAll(List.of(Type.WATER, Type.GRASS, Type.DRAGON));
        }
    }

    public class Normal_Attack extends Attack {
        public Normal_Attack(String name, int damage) {
            super(name, damage, Type.NORMAL);
            resistant.addAll(List.of(Type.ROCK, Type.STEEL));
            effectless.add(Type.GHOST);
        }
    }

    public class Electric_Attack extends Attack {
        public Electric_Attack(String name, int damage) {
            super(name, damage, Type.ELECTRIC);
            effective.addAll(List.of(Type.WATER, Type.STEEL, Type.FLYING));
            resistant.addAll(List.of(Type.GRASS, Type.ELECTRIC, Type.DRAGON));
            effectless.add(Type.GROUND);
        }
    }

    public class Ice_Attack extends Attack {
        public Ice_Attack(String name, int damage) {
            super(name, damage, Type.ICE);
            effective.addAll(List.of(Type.GROUND, Type.GRASS, Type.FLYING, Type.DRAGON));
            resistant.addAll(List.of(Type.FIRE, Type.WATER, Type.ICE, Type.STEEL));
        }
    }

    public class Fighting_Attack extends Attack {
        public Fighting_Attack(String name, int damage) {
            super(name, damage, Type.FIGHTING);
            effective.addAll(List.of(Type.NORMAL, Type.ROCK, Type.STEEL, Type.DARK, Type.ICE));
            resistant.addAll(List.of(Type.POISON, Type.FLYING, Type.PSYCHIC, Type.BUG, Type.FAIRY));
            effectless.add(Type.GHOST);
        }
    }

    public class Poison_Attack extends Attack {
        public Poison_Attack(String name, int damage) {
            super(name, damage, Type.POISON);
            effective.addAll(List.of(Type.GRASS, Type.FAIRY));
            resistant.addAll(List.of(Type.POISON, Type.GROUND, Type.PSYCHIC, Type.ROCK, Type.GHOST));
            effectless.add(Type.STEEL);
        }
    }

    public class Ground_Attack extends Attack {
        public Ground_Attack(String name, int damage) {
            super(name, damage, Type.GROUND);
            effective.addAll(List.of(Type.FIRE, Type.ROCK, Type.ELECTRIC, Type.POISON, Type.STEEL));
            resistant.addAll(List.of(Type.GRASS, Type.BUG));
            effectless.add(Type.FLYING);
        }
    }

    public class Flying_Attack extends Attack {
        public Flying_Attack(String name, int damage) {
            super(name, damage, Type.FLYING);
            effective.addAll(List.of(Type.GRASS, Type.FIGHTING, Type.BUG));
            resistant.addAll(List.of(Type.ELECTRIC, Type.ROCK, Type.STEEL));
        }
    }

    public class Psychic_Attack extends Attack {
        public Psychic_Attack(String name, int damage) {
            super(name, damage, Type.PSYCHIC);
            effective.addAll(List.of(Type.FIGHTING, Type.POISON));
            resistant.addAll(List.of(Type.PSYCHIC, Type.STEEL));
            effectless.add(Type.DARK);
        }
    }

    public class Bug_Attack extends Attack {
        public Bug_Attack(String name, int damage) {
            super(name, damage, Type.BUG);
            effective.addAll(List.of(Type.GRASS, Type.PSYCHIC, Type.DARK));
            resistant.addAll(List.of(Type.FIRE, Type.FIGHTING, Type.POISON, Type.FLYING, Type.GHOST, Type.STEEL, Type.FAIRY));
        }
    }

    public class Rock_Attack extends Attack {
        public Rock_Attack(String name, int damage) {
            super(name, damage, Type.ROCK);
            effective.addAll(List.of(Type.FIRE, Type.ICE, Type.FLYING, Type.BUG));
            resistant.addAll(List.of(Type.STEEL, Type.FIGHTING, Type.GROUND));
        }
    }

    public class Steel_Attack extends Attack {
        public Steel_Attack(String name, int damage) {
            super(name, damage, Type.STEEL);
            effective.addAll(List.of(Type.ICE, Type.ROCK, Type.FAIRY));
            resistant.addAll(List.of(Type.FIRE, Type.WATER, Type.ELECTRIC, Type.STEEL));
        }
    }

    public class Dragon_Attack extends Attack {
        public Dragon_Attack(String name, int damage) {
            super(name, damage, Type.DRAGON);
            effective.add(Type.DRAGON);
            resistant.add(Type.STEEL);
            effectless.add(Type.FAIRY);
        }
    }

    public class Ghost_Attack extends Attack {
        public Ghost_Attack(String name, int damage) {
            super(name, damage, Type.GHOST);
            effective.addAll(List.of(Type.PSYCHIC, Type.GHOST));
            resistant.add(Type.DARK);
            effectless.add(Type.NORMAL);
        }
    }

    public class Dark_Attack extends Attack {
        public Dark_Attack(String name, int damage) {
            super(name, damage, Type.DARK);
            effective.addAll(List.of(Type.PSYCHIC, Type.GHOST));
            resistant.addAll(List.of(Type.FIGHTING, Type.DARK, Type.FAIRY));
        }
    }

    public class Fairy_Attack extends Attack {
        public Fairy_Attack(String name, int damage) {
            super(name, damage, Type.FAIRY);
            effective.addAll(List.of(Type.FIGHTING, Type.DRAGON, Type.DARK));
            resistant.addAll(List.of(Type.FIRE, Type.POISON, Type.STEEL));
        }
    }

}
