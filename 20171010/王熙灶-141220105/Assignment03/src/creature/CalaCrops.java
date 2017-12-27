package creature;

import formation.BasicFormation;
import space.Position;

import java.util.*;

/**
 * 葫芦娃战队，由七只葫芦娃组成
 */
public class CalaCrops {
    private ArrayList<Calabash> calabashes = new ArrayList<>(Arrays.asList(
            new Calabash(Color.红, Order.一),
            new Calabash(Color.橙, Order.二),
            new Calabash(Color.黄, Order.三),
            new Calabash(Color.绿, Order.四),
            new Calabash(Color.青, Order.五),
            new Calabash(Color.蓝, Order.六),
            new Calabash(Color.紫, Order.七)
    ));

    private BasicFormation basicFormation;

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
        basicFormation.clear();
    }
}