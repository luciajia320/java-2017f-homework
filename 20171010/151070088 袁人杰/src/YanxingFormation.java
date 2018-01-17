public class YanxingFormation extends Formations{
    public YanxingFormation(CreatureQueue queue) {
        super(10,5);
        for (int i = 0; i < 5; i++) {
            grids[i][i].setHolder(queue.getGrid(i).getHolder());
        }
        for (int i = 5; i < 10; i++) {
            grids[i][4 - i % 5].setHolder(queue.getGrid(i).getHolder());
        }
    }
}

