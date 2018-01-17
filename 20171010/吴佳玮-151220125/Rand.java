
import java.util.List;
import java.util.Random;

public class Rand implements Embattle {

    Position getRandPosition(int xmin,int xmax,int ymin,int ymax){
        Random random=new Random();
        int x = random.nextInt(xmax)%(xmax-xmin+1) + xmin;
        int y = random.nextInt(ymax)%(ymax-ymin+1) + ymin;
        Position ret=new Position(x,y);
        return ret;
    }


    @Override
    public void action(Position startPos, BattleField battleField, List<Creature> creatures) {
        int height=battleField.getHeight();
        int length=battleField.getLength();
        for(Creature i : creatures){

            Position t;
            do {
                Position r=getRandPosition(startPos.getX(),startPos.getX()+battleField.getMiddle(),startPos.getY(),startPos.getY()+battleField.getMiddle());
                t = battleField.getPosition(r);
            }while(t.getHolder()!=null);
            Embattle.setPos(i,t);
        }
    }
}
