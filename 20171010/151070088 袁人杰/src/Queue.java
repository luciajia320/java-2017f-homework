public class Queue {
    private Grid[] grids;
    private int length;
    private int curIndex=0;

    public Queue(int length){
        this.length=length;
        grids = new Grid[length];
        for (int i=0;i<length;i++){
            grids[i]=new Grid(20+i,0);
        }
    }

    public int getLength() {
        return length;
    }

    public Grid getGrid(int index){
        return grids[index];
    }

    public void enQueue(Creature creature){
        if (curIndex>length-1){
            System.out.println("出界");
        }
        else {
            grids[curIndex].setHolder(creature);
            creature.setGrid(getGrid(curIndex));
            curIndex++;
        }
    }
}
