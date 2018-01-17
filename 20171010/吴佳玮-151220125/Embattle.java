import java.util.List;

public interface Embattle {
    public static void setPos(Creature creature,Position position ) {
        creature.setPosition(position);
        position.setHolder(creature);
    }

    public void action(Position startPos, BattleField battleField, List<Creature> creatures);
}
