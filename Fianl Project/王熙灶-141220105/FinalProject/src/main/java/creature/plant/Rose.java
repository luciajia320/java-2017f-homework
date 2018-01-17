package creature.plant;

@Deprecated
public final class Rose extends Plant {
    public Rose() {
        super("Rose", "\uD83C\uDF39");
    }

    @Override
    public String toString() {
        return plantEmoji;
    }
}
