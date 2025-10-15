package Pokemon;

public class Consumable extends Item {
    protected boolean locked = false;
    public Consumable(String name, int count, Inventory inventory) {
        super(name, count, inventory);
    }

    @Override
    public boolean use() {
        if (!this.locked) {
            this.locked = true;
            return super.use();
        }
        return false;
    }
}
