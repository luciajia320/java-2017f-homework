package creature.animal;

import creature.Creature;
import formation.BasicFormation;
import formation.HeYi;
import exception.FormationException;
import space.Position;

import java.util.*;

import static util.Constant.space;

/**
 * 葫芦娃战队，由七只葫芦娃组成
 */
public class CalaCrops extends Crops {
    private CalabashFactory calabashFactory = CalabashFactory.getInstance();
    private List<Calabash> calabashes = new ArrayList<>(Arrays.asList(
            calabashFactory.get(0),
            calabashFactory.get(1),
            calabashFactory.get(2),
            calabashFactory.get(3),
            calabashFactory.get(4),
            calabashFactory.get(5),
            calabashFactory.get(6)
    ));
    private GrandPa grandPa = GrandPa.getInstance();

    private static CalaCrops crops = new CalaCrops();
    private CalaCrops() {
        animals.add(grandPa);
        animals.add(calabashFactory.get(0));
        animals.add(calabashFactory.get(1));
        animals.add(calabashFactory.get(2));
        animals.add(calabashFactory.get(3));
        animals.add(calabashFactory.get(4));
        animals.add(calabashFactory.get(5));
        animals.add(calabashFactory.get(6));

        space.bind(grandPa, 3, 1);

        try {
            setFormation(new HeYi(3, 4));
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

    public List<? extends Animal> getCalabashes() {
        return calabashes;
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
//        clearFormation();
        basicFormation = formation;
        Iterator<Position<Creature>> itr = formation.iterator();
        for(Calabash c: calabashes) {
            if(itr.hasNext()) {
                Position<Creature> pos = itr.next();
                space.bind(c, pos.getX(), pos.getY());
            }
        }
    }

    @Deprecated
    public void addEnemy(Animal a) {
        for(Animal c: this) {
            c.addEnemy(a);
        }
    }

    @Deprecated
    public void addEnemy(EssenceCrops crops) {
        for(Animal c: this) {
            c.addEnemy(crops.getScorpionEssence());
            c.addEnemy(crops.getMinions());
        }
    }

    @Override
    public Iterator<Animal> iterator() {
        return new CalabashIterator();
    }

    private class CalabashIterator implements Iterator<Animal> {
        int no = 0;

        @Override
        public boolean hasNext() {
            return no < 8;
        }

        @Override
        public Animal next() {
            if(no == 0) {
                no++;
                return grandPa;
            }
            else {
                no++;
                return calabashes.get(no-2);
            }
        }
    }

    @Deprecated
    public static void main(String[] args) {
        CalaCrops calaCrops = CalaCrops.getInstance();
        for(Animal a: calaCrops) {
            System.out.println(a);
        }
    }
}