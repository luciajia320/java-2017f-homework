package util;

import archive.CreatureArchived;
import archive.TimePoint;

import java.util.concurrent.TimeUnit;

import static util.Constant.*;
import static util.Constant.status;

public class RePlay implements Runnable {
    @Override
    public void run() {
        ground.reset();
        for(TimePoint tp: ReadPoints) {
            space.unbindAll();
            int i = 0;
            for(CreatureArchived c: tp.creatures) {
                animals.get(i).setCurrentState(c.state);
                space.bind(animals.get(i), c.x, c.y);
                i++;
            }
            ground.repaint();
            status.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        control_start.setEnabled(false);
        control_stop.setEnabled(false);
        control_reset.setEnabled(true);
    }
}
