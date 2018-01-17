package Position;
import Creatures.Creature;

public interface PositionInterface {
    Creature getHolder();
    void setHolder(Creature holder);
    int[] getPosition();
    void setPosition(int ...pos);
    String toString();
}
