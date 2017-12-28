import java.util.ArrayList;

public class CraneWing implements Formation {
    final int N=15;
    static final int HLW_SUM=7;
    static final int LL_SUM=6;

    @Override
    public void format(Queue queue){
        ArrayList<Position> tmp = queue.getPositions();
        tmp.get(0).setX(N/2);
        tmp.get(0).setY(8);
        tmp.get(0).getHolder().setPosition(tmp.get(0));
        for (int i=1; i<tmp.size(); ++i) {
            if (i%2!=1) {
                tmp.get(i).setY(8 + i / 2);
                tmp.get(i).setX(7 + i / 2);
            }
            else {
                tmp.get(i).setY(8 + i / 2 + 1);
                tmp.get(i).setX(7 - i / 2 - 1);
            }
            tmp.get(i).getHolder().setPosition(tmp.get(i));
        }
    }
}