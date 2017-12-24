import java.util.ArrayList;

public class Audience implements Formation{
    @Override
    public void format(Queue queue){
        ArrayList<Position> tmp = queue.getPositions();
        tmp.get(0).setX(0);
        tmp.get(0).setY(0);
        tmp.get(0).getHolder().setPosition(tmp.get(0));
        tmp.get(1).setX(14);
        tmp.get(1).setY(14);
        tmp.get(1).getHolder().setPosition(tmp.get(1));
    }
}
