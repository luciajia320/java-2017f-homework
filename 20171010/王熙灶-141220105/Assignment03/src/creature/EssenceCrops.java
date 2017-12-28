package creature;

import formation.BasicFormation;
import space.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 妖精战队，由蝎子精带领六只小喽啰组成
 */
public class EssenceCrops extends Crops {
    private ScorpionEssence scorpionEssence  = new ScorpionEssence();
    private ArrayList<Minion> minions = new ArrayList<>(Arrays.asList(
            new Minion(), new Minion(), new Minion(),
            new Minion(), new Minion(), new Minion()
    ));

    /**
     * @param formation, 设定妖精战队的阵法
     */
    @Override
    public void setFormation(BasicFormation formation) {
        basicFormation = formation;
        Iterator<Position> itr = formation.iterator();
        Position pos = itr.next();
        scorpionEssence.setPosition(pos);
        pos.setHolder(scorpionEssence);

        for(Minion minion: minions) {
            if(itr.hasNext()) {
                pos = itr.next();
                minion.setPosition(pos);
                pos.setHolder(minion);
            }
        }
    }
}