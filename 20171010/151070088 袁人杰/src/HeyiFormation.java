public class HeyiFormation extends Formations{
    public HeyiFormation(CreatureQueue queue) {
        super(10,10);
        for (int i = 0; i < 10; i++) {
            this.grids[i][i].setHolder(queue.getGrid(i).getHolder());
        }
    }
}
