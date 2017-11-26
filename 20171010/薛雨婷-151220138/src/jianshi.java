import java.util.ArrayList;

public class jianshi implements layout{
    @Override
    public void sort(Map queue) {
        ArrayList<ArrayList<Position>> positions = queue.getPositions();
        // positions[0][1].getHolder().setPosition(positions[1][1]);

        for(int i=0;i<2;i++){
            positions.get(i).get(4-i).setHolder(positions.get(i).get(6-i).getHolder());
            positions.get(i).get(6-i).invalid();
        }

        /*positions.get(1).get(1).setHolder(positions.get(0).get(1).getHolder());
        positions.get(0).get(1).invalid();*/

        positions.get(1).get(2).setHolder(positions.get(2).get(2).getHolder());
        positions.get(2).get(2).invalid();

        positions.get(0).get(2).setHolder(positions.get(2).get(4).getHolder());
        positions.get(2).get(4).invalid();

        /*positions.get(1).get(3).setHolder(positions.get(1).get(5).getHolder());
        positions.get(1).get(5).invalid();

        positions.get(0).get(4).setHolder(positions.get(0).get(6).getHolder());
        positions.get(0).get(6).invalid();*/

        positions.get(2).get(2).setHolder(positions.get(3).get(3).getHolder());
        positions.get(3).get(3).invalid();

        for(int i=6;i<13;i++){
            positions.get(i).get(2).setHolder(positions.get(i).get(3).getHolder());
            positions.get(i).get(3).invalid();
        }

    }
}
