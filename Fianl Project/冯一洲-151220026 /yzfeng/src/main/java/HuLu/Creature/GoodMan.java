package HuLu.Creature;

import HuLu.Field.Field;
import HuLu.Formation.Holder;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public  class GoodMan extends Creature {
    public GoodMan(int id, Field field, CyclicBarrier cyclic) {
        super(id, field, cyclic);
    }

    @Override
    public synchronized int meetEnemy() {

        Holder holders[][] = field.getHolders();
        int x = this.x;
        int y = this.y;

        if (x + attackRange < field.getBoardWidth()
                && !holders[x + attackRange][y].isEmpty()
                && holders[x + attackRange][y].getItem() instanceof BadMan)
            return 0;

        if (x - attackRange >= 0
                && !holders[x - attackRange][y].isEmpty()
                && holders[x - attackRange][y].getItem() instanceof BadMan)
            return 1;

        return -1;
    }

    public synchronized void attackAt(Creature c) {
        int p = new Random().nextInt(100);
        if (p <= 60) {
            c.killed();
        } else {
            killed();
        }
    }

    public synchronized void findnearest() {
        for (int i = 0; i < field.getBadMEN().size(); i++) {
            if (field.getBadMEN().get(i).isAlive()
                    && field.getBadMEN().get(i).y() == y) {
                if (field.getBadMEN().get(i).x() > x) {
                    nextX = 1;
                    nextY = 0;
                    return;
                } else {
                    nextX = 0;
                    nextY = 1;
                    return;
                }
            }
        }
        int ymin = 10;
        int yi = -1;
        for (int i = 0; i < field.getBadMEN().size(); i++) {
            if (field.getBadMEN().get(i).isAlive()) {
                int disy = Math.abs(field.getBadMEN().get(i).y() - y);
                if (disy < ymin) {
                    ymin = disy;
                    yi = i;
                }
            }
        }
        if (yi != -1 && field.getBadMEN().get(yi).y() > y)
            nextY = 1;
        else
            nextY = -1;

        nextX = 0;
        return;
    }

}
