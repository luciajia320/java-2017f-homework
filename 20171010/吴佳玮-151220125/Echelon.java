import java.util.List;
//雁行
public class Echelon implements Embattle{
    @Override
    public void action(Position startPos, BattleField battleField, List<Creature> creatures) {
        for(int i=0;i<creatures.size();i++){
            Embattle.setPos(creatures.get(i),battleField.getPosition(new Position(startPos.getX()+i,startPos.getY()+i)));
        }
    }
}
