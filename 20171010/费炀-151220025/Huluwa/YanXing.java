public class YanXing extends Formation {
    YanXing(int a, int b, int w, int h, Creature[] c, Creature l) {
        super(a, b, w, h, c, l);
    }

    @Override
    public void arrange() {
        int center = height / 2;
        lp = new Position(center, 0);
        leader.setPosition(lp);
        int head = 1;
        for(int i = 0; i < creatures.length; i++) {
            if((height - i - 2 < 0) || (head + i > width - 1)) break;
            positions[i] = new Position(height - i - 2, head + i);
            creatures[i].setPosition(positions[i]);
        }
    }
}
