import java.util.ArrayList;

//鶴翼形
public class HeYiXing implements Formation {
    @Override
    public void format(Queue queue){
        ArrayList<Position> copyPositions = queue.getPositions();
        for (int i=0; i<copyPositions.size()/2; i++){
            copyPositions.get(i).setCoordX(i);
            copyPositions.get(i).getHolder().setPosition(copyPositions.get(i));
            copyPositions.get(copyPositions.size()-1-i).setCoordX(i);
            copyPositions.get(copyPositions.size()-1-i).getHolder().setPosition(copyPositions.get(copyPositions.size()-1-i));
        }
        copyPositions.get(copyPositions.size()/2).setCoordX(copyPositions.size()/2);
        copyPositions.get(copyPositions.size()/2).getHolder().setPosition(copyPositions.get(copyPositions.size()/2));
    }
}
