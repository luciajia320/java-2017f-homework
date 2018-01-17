public class LineFormation extends Formations {
    public LineFormation(CreatureQueue queue){
        super(2,7);
        for (int i = 0; i < 7; i++) {
            this.grids[0][i].setHolder(queue.getGrid(i).getHolder());
        }
    }
}
