import java.util.List;
//鹤翼
public class Crane implements Embattle {
    @Override
    public void action(Position startPos, BattleField battleField, List<Creature> creatures) {
        int height=battleField.getHeight();
        for(int i=0;i<creatures.size();i++){
            if(i%2==0)
                Embattle.setPos(creatures.get(i),battleField.getPosition(new Position(startPos.getX()+(i+1)/2,startPos.getY()+height/2+(i+1)/2)));
            else
                Embattle.setPos(creatures.get(i),battleField.getPosition(new Position(startPos.getX()+(i+1)/2,startPos.getY()+height/2-(i+1)/2)));

        }
    }
}
