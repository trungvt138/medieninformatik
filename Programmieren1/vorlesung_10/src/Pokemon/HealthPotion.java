package Pokemon;

public class HealthPotion extends Consumable {
    private int healthAmount;

    public HealthPotion(int count, Inventory inventory) {
        super("Health Potion", count, inventory);
        healthAmount = 50;
    }

    @Override
    public boolean use() {
        if (super.use()) {
            System.out.printf("%s was healed %d HP!", inventory.getOwner().activePokemon.getName(), this.healthAmount);
            return true;
        }
        return false;
    }
}
