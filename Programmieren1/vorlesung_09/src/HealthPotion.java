public class HealthPotion extends Consumable {
    private int healthAmount;

    public HealthPotion(int count, Trainer trainer) {
        super("Health Potion", count, trainer);
        healthAmount = 50;
    }

    @Override
    public boolean use() {
        if (super.use()) {
            System.out.printf("Trainer %s used a %s on %s\n%s was healed %d HP!", trainer.name, this.name,
                    trainer.activePokemon.getName(), trainer.activePokemon.getName(), this.healthAmount);
            return true;
        }
        return false;
    }
}
