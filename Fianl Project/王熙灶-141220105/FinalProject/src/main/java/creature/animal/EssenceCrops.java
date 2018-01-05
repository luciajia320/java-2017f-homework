package creature.animal;

import creature.Creature;
import formation.BasicFormation;
import formation.FengShi;
import exception.FormationException;
import space.Position;
import ui.Ground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 妖精战队，由蝎子精带领六只小喽啰组成
 */
public class EssenceCrops extends Crops implements Iterable<Animal> {
    private ScorpionEssence scorpionEssence  = ScorpionEssence.getInstance();
    private MinionFactory minionFactory = MinionFactory.getInstance();
    private List<Minion> minions = new ArrayList<>(Arrays.asList(
            minionFactory.get(0), minionFactory.get(1), minionFactory.get(2),
            minionFactory.get(3), minionFactory.get(4), minionFactory.get(5)
    ));

    private static EssenceCrops crops = new EssenceCrops();
    private EssenceCrops() {
        try {
            setFormation(new FengShi(7, 4));
        } catch (FormationException e) {
            e.printStackTrace();
        }
    }

    public static EssenceCrops getInstance() {
        return crops;
    }

    /**
     * @param formation, 设定妖精战队的阵法
     */
    @Override
    public void setFormation(BasicFormation formation) {
        clearFormation();
        basicFormation = formation;
        Iterator<Position<Creature>> itr = formation.iterator();
        Position<Creature> pos = itr.next();
        scorpionEssence.setPosition(pos);
        pos.setHolder(scorpionEssence);

        for(Minion minion: minions) {
            if(itr.hasNext()) {
                pos = itr.next();
                minion.setPosition(pos);
                pos.setHolder(minion);
            }
        }
    }

    /**
     * @return 返回妖魔战队中的蝎子精头领
     */
    public ScorpionEssence getScorpionEssence() {
        return scorpionEssence;
    }

    /**
     * @param i, 小喽啰的序号
     * @return 返回妖魔战队中的第 i 个小喽啰
     */
    public Minion get(int i) {
        if(i >= 6) {
            throw new IndexOutOfBoundsException("method 'Minion get(int i)' index out of bounds in class EssenceCrops.");
        }
        return minions.get(i);
    }

    public List<Minion> getMinions() {
        return minions;
    }

    public void addEnemy(Animal a) {
        for(Animal c: this) {
            c.addEnemy(a);
        }
    }

    public void addEnemy(CalaCrops crops) {
        for(Animal a: this) {
            a.addEnemy(crops.getCalabashes());
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
            return no < 7;
        }

        @Override
        public Animal next() {
            if(no == 0) {
                no++;
                return scorpionEssence;
            }
            else {
                no++;
                return minions.get(no-2);
            }
        }
    }
}