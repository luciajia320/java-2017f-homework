import Characters.*;
import Base.Troop;
import Base.Field;
import Types.COLOR;
import Types.SENIORITY;
import Types.TianGan;

public class main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow(1200, 1000);

        Field field = new Field(1000, 800, 15, 12, 2);

        //葫芦娃
        Huluwa[] HuluBrothers = new Huluwa[7];
        for (int i = 0; i < HuluBrothers.length; i++) {
            HuluBrothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
        }
        // 小喽啰
        Louluo[] lackeys = new Louluo[7];
        for (int i = 0; i < lackeys.length; i++) {
            lackeys[i] = new Louluo(TianGan.values()[i%TianGan.values().length]);
        }
        // 爷爷
        Grandpa YeYe = new Grandpa("爷爷");
        // 蛇精
        SnakeMonster SheJing = new SnakeMonster("蛇精");
        // 蝎子精
        ScorpionMonster XieZiJing = new ScorpionMonster("蝎子精");

        /* 初始化各方势力 */
        Troop powerOfHuluwa = new Troop("葫芦娃", 2, 0);
        Troop powerOfYaojing = new Troop("妖精", 8, 6);
        /* 各方势力登场 */
        field.addTroop(powerOfHuluwa);
        field.addTroop(powerOfYaojing);

        /* 所有人物加入势力 */
        powerOfHuluwa.addCreatures(HuluBrothers);
        powerOfHuluwa.addOneCreature(YeYe);
        powerOfYaojing.addCreatures(lackeys);
       powerOfYaojing.addOneCreature(SheJing);
        powerOfYaojing.addOneCreature(XieZiJing);

        /* 各方势力宣战 */
        powerOfHuluwa.declareWarTo(powerOfYaojing);
        powerOfYaojing.declareWarTo(powerOfHuluwa);



        gameWindow.add(field);
        gameWindow.setVisible(true);

//        Vector2 pp = HuluBrothers[0].getPosition().getCoordinate();
//        HuluBrothers[0].moveTo(new Vector2(1, 1));
//        HuluBrothers[0].remainActionAnimationClockCircles = 10;
//        HuluBrothers[0].renderComponent.startAnimationProgressWithDuration(10, RenderComponent.ImageType.MOVING, pp);
//        HuluBrothers[0].state = CreatureState.MOVING;
//        field.repaint();

//        try {
//            Thread.sleep(10000);
//        } catch ( Exception e) {
//
//        }
    }
}
