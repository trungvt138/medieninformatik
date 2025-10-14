public class Item {
    public String name;
    protected int count;
    protected Trainer trainer;

    public Item(String name, int count, Trainer trainer) {
        this.name = name;
        this.count = count;
    }

    public boolean use() {
        if (count <= 0) {
            return false;
        }
        count--;
        return true;
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        return this.name + " (" + this.count + ")";
    }
}
