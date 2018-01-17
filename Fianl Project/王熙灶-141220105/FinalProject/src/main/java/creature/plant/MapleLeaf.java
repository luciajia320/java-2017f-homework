package creature.plant;

@Deprecated
public final class MapleLeaf extends Plant {
    public MapleLeaf() {
        super("MapleLeaf", "\uD83C\uDF41");
    }

    @Override
    public String toString() {
        return plantEmoji;
    }
}
