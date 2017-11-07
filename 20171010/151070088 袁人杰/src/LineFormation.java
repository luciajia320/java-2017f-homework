public class LineFormation extends Formations {
    public LineFormation(CreatureQueue queue){
        super(7,2);
        for (int i = 0; i < 7; i++) {
            this.grids[i][0].setHolder(queue.getGrid(i).getHolder());
        }
    }
}
