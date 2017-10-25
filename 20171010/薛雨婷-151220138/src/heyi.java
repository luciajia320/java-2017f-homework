public class heyi implements formation {
    @Override
    public void sort(Map queue) {
        creature creature;
        Position[][] positions = queue.getPositions();
       // positions[0][1].getHolder().setPosition(positions[1][1]);
        positions[1][1].setHolder(positions[0][1].getHolder());
        positions[0][1].invalid();

        positions[2][2].setHolder(positions[0][2].getHolder());
        positions[0][2].invalid();

        positions[2][4].setHolder(positions[0][3].getHolder());
        positions[0][3].invalid();

        positions[1][5].setHolder(positions[0][4].getHolder());
        positions[0][4].invalid();

        positions[0][6].setHolder(positions[0][5].getHolder());
        positions[0][5].invalid();

        positions[3][3].setHolder(positions[1][0].getHolder());
        positions[1][0].invalid();

    }
}
