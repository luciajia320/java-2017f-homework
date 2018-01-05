
public class HeYi extends Formation{

    HeYi(int a, int b, int w, int h, Creature[] c, Creature l) {
        super(a, b, w, h, c, l);
    }

    @Override
    public void arrange() {
        int head = width / 2;
        lp = new Position(height - 1, head);
        leader.setPosition(lp);
        for(int i = 0; i < creatures.length; i++) {
            if(i == 0) {
                positions[i] = new Position(height - 2, head);

            }
            else {
                if(i % 2 == 1) {
                    if((height - (i + 1)/2 - 2 < 0) || (head - (i + 1)/2 < 0)) break;
                    positions[i] = new Position(height - (i + 1)/2 - 2, head - (i + 1)/2);

                }
                else {
                    if((height - i / 2 - 2 < 0) || (head + i / 2 > width - 1)) break;
                    positions[i] = new Position(height - i / 2 - 2, head + i / 2);
                }
            }
            creatures[i].setPosition(positions[i]);
        }
    }
}
