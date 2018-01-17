package creature.plant;

@Deprecated
public final class Tulip extends Plant {
    public Tulip() {
        super("Tulip", "\uD83C\uDF37");
    }

    @Override
    public String toString() {
        return plantEmoji;
    }
}
