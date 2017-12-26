package creature;

import formation.BasicFormation;
import space.Position;

import java.util.Arrays;
import java.util.Random;

/**
 * 葫芦娃战队，由七只葫芦娃组成
 */
public class CalaCrops {
    private Calabash[] calabashes;
    private BasicFormation basicFormation;

    public CalaCrops() {
        calabashes = new Calabash[7];
        calabashes[0] = new Calabash(Color.红, Order.一);
        calabashes[1] = new Calabash(Color.橙, Order.二);
        calabashes[2] = new Calabash(Color.黄, Order.三);
        calabashes[3] = new Calabash(Color.绿, Order.四);
        calabashes[4] = new Calabash(Color.青, Order.五);
        calabashes[5] = new Calabash(Color.蓝, Order.六);
        calabashes[6] = new Calabash(Color.紫, Order.七);
    }

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
        for(Calabash c: calabashes) {
            Position pos = formation.Next();
            if(pos == null) break;
            c.setPosition(pos);
            pos.setHolder(c);
        }
    }

    /**
     * 清除葫芦娃战队的当前阵法
     */
    public void clearFormation() {
        basicFormation.reset();
        Position pos = basicFormation.Next();
        while (pos != null) {
            pos.setHolder(null);
            pos = basicFormation.Next();
        }
    }
}