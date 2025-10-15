public class Pokeball extends Item {
    public Pokeball(int count, Trainer trainer) {
        super("Pokeball", count, trainer);
    }

    @Override
    public boolean use() {
        if (!super.use()) {
            System.out.printf("Trainer %s: You don't have any %ss left!\n", trainer.name, this.name);
            return false;
        }
        return true;
    }
}
