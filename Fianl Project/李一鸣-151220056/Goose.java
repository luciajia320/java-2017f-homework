import java.util.ArrayList;

public class Goose implements Formation{
    @Override
    public void format(Queue queue){
        ArrayList<Position> tmp = queue.getPositions();
        tmp.get(0).setX(6);
        tmp.get(0).setY(4);
        tmp.get(0).getHolder().setPosition(tmp.get(0));
        for (int i=1; i<tmp.size(); ++i) {
            tmp.get(i).setX(6+i);
            tmp.get(i).setY(4+i);
            tmp.get(i).getHolder().setPosition(tmp.get(i));
        }
    }
}
