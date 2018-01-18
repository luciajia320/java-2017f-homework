package entity;

import common.Constant;

import java.util.ArrayList;

/* 阵型 */

public class Formation implements Constant {
    public Formation(int x, int y) {
        this.width = x;
        this.height = y;
    }

    private int width, height;
}

class GooseSwing extends Formation {
    public GooseSwing(Location location, ArrayList<Creature> creatures) {
        super(GOOSESWINGWIDTH, GOOSESWINGHEIGHT);

        for (int i = 0; i <= GOOSESWINGHEIGHT / 2; i++)
            creatures.get(i).setPoint(location.getX() + i, location.getY() +  i);
        for (int i = GOOSESWINGHEIGHT / 2 + 1; i < GOOSESWINGHEIGHT; i++)
            creatures.get(i).setPoint(location.getX() - i + GOOSESWINGHEIGHT - 1, location.getY() + i);
    }
}

class XFormation extends Formation {
    public XFormation(Location location, ArrayList<Creature> creatures) {
    super(XWIDTH, XHEIGHT);

        for (int i = 0; i < XHEIGHT; i++) {
            if (i % 2 == 0)
                creatures.get(i).setPoint(location.getX(), location.getY() + i);
            else
                creatures.get(i).setPoint(location.getX() + 1, location.getY() + i);
        }
    }
}