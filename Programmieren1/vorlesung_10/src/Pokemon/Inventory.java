package Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Trainer owner;
    private List<Item> items;
    private int maxSize;

    public Inventory(Trainer owner, int maxSize) {
        this.owner = owner;
        this.maxSize = maxSize;
        items = new ArrayList<Item>();
    }

    public Trainer getOwner() {
        return owner;
    }

    public int getItemsCount() {
        return items.size();
    }

    public Item search(String itemName) {
        for (Item item : items) {
            if (item.name.equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void add(Item item) {
        Item temp = search(item.name);
        if (temp != null) {
            temp.setCount(item.getCount()+1);
        } else if (items.size() < maxSize) {
            items.add(item);
        }
    }

    public void drop(Item item) {
        if (search(item.name) != null) {
            items.remove(item);
        }
    }

    public boolean use(Item item) {
        Item temp = search(item.name);
        if (temp != null) {
            if (temp.use()) {
                System.out.printf("Pokemon.Trainer %s used 1 %s\n", owner.name, item.name);
                if (temp.getCount() == 0) {
                    items.remove(temp);
                }
                return true;
            };
        }
        return false;
    }

    public String toString() {
        return "Pokemon.Inventory of " + owner.name + " - Size: " + items.size();
    }
}
