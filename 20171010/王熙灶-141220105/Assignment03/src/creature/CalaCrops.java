package creature;

import formation.BasicFormation;
import space.Position;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * 葫芦娃战队，由七只葫芦娃组成
 */
public class CalaCrops {
    private Calabash[] calabashes = {
        new Calabash(Color.红, Order.一),
        new Calabash(Color.橙, Order.二),
        new Calabash(Color.黄, Order.三),
        new Calabash(Color.绿, Order.四),
        new Calabash(Color.青, Order.五),
        new Calabash(Color.蓝, Order.六),
        new Calabash(Color.紫, Order.七)
    };
    private BasicFormation basicFormation;

    /**
     * 将葫芦娃顺序打乱
     */
    public void randomPermutation() {
        Random random = new Random();
        for(int i = 0; i < 7; i++) {
            int p = random.nextInt(7);
            Calabash tmp = calabashes[i];
            calabashes[i] = calabashes[p];
            calabashes[p] = tmp;
        }
    }

    /**
     * 对葫芦娃进行排序
     */
    public void sort() {
        Arrays.sort(this.calabashes);
    }

    /**
     * @param formation, 设定葫芦娃战队的阵法
     */
    public void setFormation(BasicFormation formation) {
        basicFormation = formation;
        Iterator<Position> itr = formation.iterator();
        for(Calabash c: calabashes) {
            if(itr.hasNext()) {
                Position pos = itr.next();
                c.setPosition(pos);
                pos.setHolder(c);
            }
        }
    }

    /**
     * 清除葫芦娃战队的当前阵法
     */
    public void clearFormation() {
        if(basicFormation == null) return;
        basicFormation.reset();
        Iterator<Position> itr = basicFormation.iterator();
        while (itr.hasNext()) {
            Position pos = itr.next();
            pos.setHolder(null);
        }
    }
}