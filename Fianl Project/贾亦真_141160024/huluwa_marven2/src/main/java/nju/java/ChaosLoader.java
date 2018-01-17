package nju.java;

import nju.java.common.Constant;

public class ChaosLoader implements Runnable {
    Creature creature;
    ChaosLoader( Creature c){
        creature = c;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {

                if (!creature.isAlive() || creature.field.getState() == Constant.STATE.ENDED) return;
                Creature target = creature.FindTarget();
                if (target == null) {
                    creature.field.setState(Constant.STATE.ENDED);
                    return;
                } else if (target.isAlive() & creature.withinRange(target)) {
                    try {
                        if (!creature.isAlive()) return;
                        if (!target.isAlive()) continue;
                        boolean result = creature.Battle(target);
                        Thread.sleep(2000);
                        creature.field.repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }

                } else if (target.isAlive()) {
                    double step_x = target.x() - creature.x();
                    double step_y = target.y() - creature.y();
                    double dis = Math.sqrt(Math.pow(step_x, 2) + Math.pow(step_y, 2));
                    int x_move = new Double(creature.speed * step_x / dis).intValue();
                    int y_move = new Double(creature.speed * step_y / dis).intValue();

                    creature.move(x_move, y_move);
                    creature.field.repaint();
                }
            }
            return;
        }finally {
            System.out.println(creature.getName() + " finished");
        }
    }
}
