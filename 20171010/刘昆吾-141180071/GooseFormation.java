

public class GooseFormation extends Formation {
    public GooseFormation(ScorpionFaction matter) {
        super(7, 4);
        for (int i = 0; i < 4; i++) {
            content[i][i] = matter.getLittleMinions();
        }
        for (int i = 4; i < 7; i++) {
            content[i][2 - i % 4] = matter.getLittleMinions();
        }
        content[0][0] = matter.getLeader();
    }
}
