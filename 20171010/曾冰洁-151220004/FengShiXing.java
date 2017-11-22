import java.util.ArrayList;

//鋒矢形
public class FengShiXing implements Formation {
    @Override
    public void format(Queue queue) {
        ArrayList<Position> copyPositions = queue.getPositions();
        if (copyPositions.size()==0){ return; }
        int i=0;
        copyPositions.get(i).setCoord(new Coord(i,2));
        copyPositions.get(i).getHolder().setPosition(copyPositions.get(i));
        for (i=1; i<copyPositions.size() && i<3; i++){
            copyPositions.get(2*i-1).setCoord(new Coord(i,2-i));
            copyPositions.get(2*i-1).getHolder().setPosition(copyPositions.get(2*i-1));
            copyPositions.get(2*i).setCoord(new Coord(i,2+i));
            copyPositions.get(2*i).getHolder().setPosition(copyPositions.get(2*i));
        }
        for (i=5; i<copyPositions.size(); i++){
            copyPositions.get(i).setCoord(new Coord(i-4,2));
            copyPositions.get(i).getHolder().setPosition(copyPositions.get(i));
        }
    }
}
