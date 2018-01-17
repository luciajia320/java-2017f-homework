package creature.animal;

import creature.Creature;
import formation.BasicFormation;
import space.Position;

import java.util.*;

/**
 * 葫芦娃战队，由七只葫芦娃组成
 */
public class CalaCrops extends Crops {
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
    private CalaCrops() {}

    public static CalaCrops getInstance() {
        return crops;
    }

    /**
     * 将葫芦娃顺序打乱
     */
    public void shuffle() {
        Collections.shuffle(calabashes);
    }

    /**
     * 对葫芦娃进行排序
     */
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
}