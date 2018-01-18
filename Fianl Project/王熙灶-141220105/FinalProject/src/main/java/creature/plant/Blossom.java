package creature.plant;

@Deprecated
public final class Blossom extends Plant {
    public Blossom() {
        super("Blossom", "\uD83C\uDF3C");
    }

    @Override
    public String toString() {
        return plantEmoji;
    }
}