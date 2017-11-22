import java.util.ArrayList;

//衝軛形
public class ChongEXing implements Formation {
    @Override
    public void format(Queue queue) {
        ArrayList<Position> copyPositions = queue.getPositions();
        for (int i=0; i<copyPositions.size(); i++){
            copyPositions.get(i).setCoord(new Coord(i,(i+1)%2));
            copyPositions.get(i).getHolder().setPosition(copyPositions.get(i));
        }
    }
}
