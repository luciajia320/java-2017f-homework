package creature.animal;

import creature.Creature;
import formation.BasicFormation;
import formation.HeYi;
import exception.FormationException;
import space.Position;
import ui.Ground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * 葫芦娃战队，由七只葫芦娃组成
 */
public class CalaCrops extends Crops implements Iterable<Calabash> {
    private CalabashFactory calabashFactory = CalabashFactory.getInstance();
    private ArrayList<Calabash> calabashes = new ArrayList<>(Arrays.asList(
            calabashFactory.get(0),
            calabashFactory.get(1),
            calabashFactory.get(2),
            calabashFactory.get(3),
            calabashFactory.get(4),
            calabashFactory.get(5),
            calabashFactory.get(6)
    ));

    private static CalaCrops crops = new CalaCrops();
    private CalaCrops() {
        try {
            setFormation(new HeYi(Ground.space, 5, 4));
        } catch (FormationException e) {
            e.printStackTrace();
        }
    }

    public static CalaCrops getInstance() {
        return crops;
    }

    /**
     * 将葫芦娃顺序打乱
     */
    @Deprecated
    public void shuffle() {
        Collections.shuffle(calabashes);
    }

    /**
     * 对葫芦娃进行排序
     */
    @Deprecated
    public void sort() {
        Collections.sort(calabashes);
    }

    public Calabash get(int i) {
        if(i >= 7) {
            throw new IndexOutOfBoundsException("method 'Calabash get(int i)' index out of bounds in class CalaCrops.");
        }
        return calabashes.get(i);
    }

    /**
     * @param formation, 设定葫芦娃战队的阵法
     */
    @Override
    public void setFormation(BasicFormation formation) {
        clearFormation();
        basicFormation = formation;
        Iterator<Position<Creature>> itr = formation.iterator();
        for(Calabash c: calabashes) {
            if(itr.hasNext()) {
                Position<Creature> pos = itr.next();
                c.setPosition(pos);
                pos.setHolder(c);
            }
        }
    }

    @Override
    public Iterator<Calabash> iterator() {
        return new CalabashIterator();
    }

    private class CalabashIterator implements Iterator<Calabash> {
        int no = 0;

        @Override
        public boolean hasNext() {
            return no < 7;
        }

        @Override
        public Calabash next() {
            return calabashes.get(no++);
        }
    }
}