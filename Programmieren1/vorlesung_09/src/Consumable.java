public class Consumable extends Item {
    protected boolean locked = false;
    public Consumable(String name, int count, Trainer trainer) {
        super(name, count, trainer);
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
