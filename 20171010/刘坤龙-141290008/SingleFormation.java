package lonhh.huluwa;

public class SingleFormation extends Formation {
    SingleFormation(Creature creature){
        super(1,1);
        Creature[][] creatures = super.getContent();
        creatures[0][0] = creature;
    }
}
