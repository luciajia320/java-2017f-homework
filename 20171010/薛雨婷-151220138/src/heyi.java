import java.util.ArrayList;

public class heyi implements layout {
    @Override
    public void sort(Map queue) {
        ArrayList<ArrayList<Position>> positions = queue.getPositions();
       // positions[0][1].getHolder().setPosition(positions[1][1]);
        for (int i=1;i<3;i++){
            positions.get(i).get(i).setHolder(positions.get(0).get(i).getHolder());
            positions.get(0).get(i).invalid();
        }
        for (int i=3;i<6;i++){
            positions.get(5-i).get(i+1).setHolder(positions.get(0).get(i).getHolder());
            positions.get(0).get(i).invalid();
        }


        /*positions.get(1).get(1).setHolder(positions.get(0).get(1).getHolder());
        positions.get(0).get(1).invalid();

        positions.get(2).get(2).setHolder(positions.get(0).get(2).getHolder());
        positions.get(0).get(2).invalid();

        positions.get(2).get(4).setHolder(positions.get(0).get(3).getHolder());
        positions.get(0).get(3).invalid();

        positions.get(1).get(5).setHolder(positions.get(0).get(4).getHolder());
        positions.get(0).get(4).invalid();

        positions.get(0).get(6).setHolder(positions.get(0).get(5).getHolder());
        positions.get(0).get(5).invalid();*/

        positions.get(3).get(3).setHolder(positions.get(1).get(0).getHolder());
        positions.get(1).get(0).invalid();

    }
}
