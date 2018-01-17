import java.util.ArrayList;

//方円形
public class FangYuanXing implements Formation{
    @Override
    public void format(Queue queue) {
        ArrayList<Position> copyPositions = queue.getPositions();
        int refCoordY = (copyPositions.size()+3)/4;
        for (int i=0; i<copyPositions.size(); i++){
            int deltaY = Math.abs((i+1)/2 - refCoordY);
            if (i%2==0) {
                copyPositions.get(i).setCoord(new Coord((i+1)/2, 2*refCoordY-deltaY));
                copyPositions.get(i).getHolder().setPosition(copyPositions.get(i));
            } else {
                copyPositions.get(i).setCoord(new Coord((i+1)/2, deltaY));
                copyPositions.get(i).getHolder().setPosition(copyPositions.get(i));
            }
        }
    }
}
