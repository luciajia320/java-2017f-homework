package Sorter;

import Creatures.Creature;
import Queue.Queue;
import Position.Position;

public class QuickSorter implements Sorter {
    @Override
    public void Sort(Queue queue) {
        quickSort(0,queue.getCreatures().length-1,queue);
    }
    void quickSort(int beg, int end, Queue queue){
        if(beg<end){
            int par=Partition(beg,end,queue);
            quickSort(beg,par-1,queue);
            quickSort(par+1,end,queue);
        }
    }

    int Partition(int beg, int end, Queue queue) {
        Creature creature;
        Position[] positions = queue.getPositions();

        while (beg < end){
            while(end>beg&&((Comparable) (positions[end].getSomeone())).biggerThan((Comparable) (positions[beg].getSomeone())))
                end--;

            if(beg<end){
                creature = positions[end].getSomeone();
                positions[beg].getSomeone().setPosition(positions[end]);
                creature.setPosition(positions[beg]);
            }

            while(beg<end && ((Comparable)(positions[end].getSomeone())).biggerThan((Comparable)(positions[beg].getSomeone())))
                beg++;

            if(beg<end){
                creature = positions[end].getSomeone();
                positions[beg].getSomeone().setPosition(positions[end]);
                creature.setPosition(positions[beg]);
            }
        }
        return beg;
    }
}
