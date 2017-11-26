public class jianshi implements formation{
    @Override
    public void sort(Map queue) {
        creature creature;
        Position[][] positions = queue.getPositions();
        // positions[0][1].getHolder().setPosition(positions[1][1]);
        positions[1][1].setHolder(positions[0][1].getHolder());
        positions[0][1].invalid();

        positions[1][2].setHolder(positions[2][2].getHolder());
        positions[2][2].invalid();

        positions[0][2].setHolder(positions[2][4].getHolder());
        positions[2][4].invalid();

        positions[1][3].setHolder(positions[1][5].getHolder());
        positions[1][5].invalid();

        positions[0][4].setHolder(positions[0][6].getHolder());
        positions[0][6].invalid();

        positions[2][2].setHolder(positions[3][3].getHolder());
        positions[3][3].invalid();

    }
}
