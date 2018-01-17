import java.util.ArrayList;

public class Audience implements Formation{
    final int N=15;
    static final int HLW_SUM=7;
    @Override
    public void format(Queue queue){
        ArrayList<Position> tmp = queue.getPositions();
        tmp.get(0).setX(N/2);
        tmp.get(0).setY(0);
        tmp.get(0).getHolder().setPosition(tmp.get(0));
    }
}
