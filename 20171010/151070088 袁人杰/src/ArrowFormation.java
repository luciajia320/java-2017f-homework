public class ArrowFormation extends Formations {
    public ArrowFormation(CreatureQueue queue) {
        super(5,5);
        for (int i = 0; i < 7; i++) {
            if (i==0){this.grids[i][2].setHolder(queue.getGrid(i).getHolder());}
            else if (i<7){
                if (i%3==1){grids[1-i/3][1+(i-1)/3].setHolder(queue.getGrid(i).getHolder()); }
                if (i%3==2){grids[2][1+(i-1)/3].setHolder(queue.getGrid(i).getHolder()); }
                if (i%3==0){grids[2+i/3][1+(i-1)/3].setHolder(queue.getGrid(i).getHolder()); }
            }
            else grids[i-4][3].setHolder(queue.getGrid(i).getHolder());
        }
    }
}
