import Characters.Huluwa;
import Layout.Troop;
import Types.COLOR;
import Types.SENIORITY;

public class main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow(1000, 800);

        Field field = new Field(1000, 800, 20, 16, 2);
        gameWindow.add(field);

        //葫芦娃
        Huluwa[] HuluBrothers = new Huluwa[1];
        for (int i = 0; i < HuluBrothers.length; i++) {
            HuluBrothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
        }
        Troop powerOfHuluwa = new Troop(5, "葫芦娃", 0, 0);
        field.addTroop(powerOfHuluwa);

        powerOfHuluwa.addCreatures(HuluBrothers);

        gameWindow.setVisible(true);
    }
}
