public interface Creature {

    void report();

    void setPosition(Position position);

    Position getPosition();

    void changePositionWith(Creature another);
}
