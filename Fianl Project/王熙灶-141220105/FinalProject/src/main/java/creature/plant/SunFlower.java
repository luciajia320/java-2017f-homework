package creature.plant;

@Deprecated
public final class SunFlower extends Plant {
    public SunFlower() {
        super("SunFlower", "\uD83C\uDF3B");
    }

    @Override
    public String toString() {
        return plantEmoji;
    }
}
