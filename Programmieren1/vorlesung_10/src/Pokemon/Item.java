package Pokemon;

public class Item {
    public String name;
    protected int count;
    protected Inventory inventory;

    public Item(String name, int count, Inventory inventory) {
        this.name = name;
        this.count = count;
        this.inventory = inventory;
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

    public void setCount(int count) {
        this.count = count;
    }

    public String toString() {
        return this.name + " (" + this.count + ")";
    }
}
