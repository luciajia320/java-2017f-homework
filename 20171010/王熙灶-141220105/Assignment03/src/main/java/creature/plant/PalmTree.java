package creature.plant;

public final class PalmTree extends Plant {
    public PalmTree() {
        super("PalmTree", "\uD83C\uDF34");
    }

    @Override
    public String toString() {
        return plantEmoji;
    }
}