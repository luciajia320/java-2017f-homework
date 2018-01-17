import java.util.List;

public class Queue implements Embattle{

    @Override
    public void action(Position startPos, BattleField battleField, List<Creature> creatures) {
        int height=battleField.getHeight();
        for(int i=0;i<creatures.size();i++){
            Embattle.setPos(creatures.get(i),battleField.getPosition(new Position(startPos.getX()+i, startPos.getY()+height/2)));
        }
    }
}

