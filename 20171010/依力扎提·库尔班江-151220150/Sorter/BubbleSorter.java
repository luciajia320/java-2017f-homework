package Sorter;

import Creatures.Creature;
import Position.Position;
import Queue.Queue;

public class BubbleSorter implements Sorter {
    @Override
    public void Sort(Queue queue) {
        Creature creature;
        Position[] positions = queue.getPositions();

        for (int i = 0; i < positions.length - 1; i++) {
            for (int j = 0; j < positions.length - 1 - i; j++) {
                if (((Comparable) (positions[j].getSomeone())).biggerThan((Comparable) (positions[j + 1].getSomeone()))) {
                    creature = positions[j].getSomeone();
                    positions[j + 1].getSomeone().setPosition(positions[j]);
                    creature.setPosition(positions[j + 1]);
                }
            }
        }
    }
}