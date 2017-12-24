import java.util.ArrayList;

public class Snake implements Formation {
    @Override
    public void format(Queue queue){
        ArrayList<Position> tmp = queue.getPositions();
        for (int i=0; i<tmp.size(); ++i) {
            tmp.get(i).setX(0);
            tmp.get(i).setY(i + 4);
            tmp.get(i).getHolder().setPosition(tmp.get(i));
        }
    }
}
