import Characters.Huluwa;
import Characters.Louluo;
import Layout.Troop;
import Field.Field;
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
        Louluo[] lackeys = new Louluo[1];
        for (int i = 0; i < lackeys.length; i++) {
            lackeys[i] = new Louluo(TianGan.values()[i]);
        }

        /* 初始化各方势力 */
        Troop powerOfHuluwa = new Troop("葫芦娃", 2, 0);
        Troop powerOfYaojing = new Troop("妖精", 2, 6);
        /* 各方势力登场 */
        field.addTroop(powerOfHuluwa);
        field.addTroop(powerOfYaojing);

        /* 所有人物加入势力 */
        powerOfHuluwa.addCreatures(HuluBrothers);
        powerOfYaojing.addCreatures(lackeys);

        /* 各方势力宣战 */
        powerOfHuluwa.declareWarTo(powerOfYaojing);
        powerOfYaojing.declareWarTo(powerOfHuluwa);

        gameWindow.add(field);
        gameWindow.setVisible(true);
    }
}
