public class Item {
    public String name;
    protected int count;
    protected Trainer trainer;

    public Item(String name, int count, Trainer trainer) {
        this.name = name;
        this.count = count;
        this.trainer = trainer;
    }

    public boolean use() {
        if (count <= 0) {
            return false;
        }
        count--;
        System.out.printf("Trainer %s used 1 %s\n", trainer.name, name);
        return true;
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        return this.name + " (" + this.count + ")";
    }
}
