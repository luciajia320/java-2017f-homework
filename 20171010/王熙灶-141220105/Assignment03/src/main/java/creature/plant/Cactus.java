package creature.plant;

public final class Cactus extends Plant {
    public Cactus() {
        super("Cactus", "\uD83C\uDF35");
    }

    @Override
    public String toString() {
        return plantEmoji;
    }
}
