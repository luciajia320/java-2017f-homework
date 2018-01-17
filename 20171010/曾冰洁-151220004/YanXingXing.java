import java.util.ArrayList;

//雁行形
public class YanXingXing implements Formation {
    @Override
    public void format(Queue queue) {
        ArrayList<Position> copyPositions = queue.getPositions();
        for (int i=0; i<copyPositions.size(); i++){
            copyPositions.get(i).setCoordX(copyPositions.size()-1-i);
            copyPositions.get(i).getHolder().setPosition(copyPositions.get(i));
        }
    }
}
