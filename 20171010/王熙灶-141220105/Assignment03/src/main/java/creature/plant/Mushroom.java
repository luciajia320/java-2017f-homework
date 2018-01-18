package creature.plant;

public final class Mushroom extends Plant {
    public Mushroom() {
        super("Mushroom", "\uD83C\uDF44");
    }

    @Override
    public String toString() {
        return plantEmoji;
    }
}