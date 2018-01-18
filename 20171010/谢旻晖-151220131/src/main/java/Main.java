import BattleGround.TwoDimensionBattleGround;
import BattleGround.BattleGround;
public class Main {
    public static void main(String[] args) {
        BattleGround battleGround = TwoDimensionBattleGround.getInstance();
        battleGround.initCreature();
        battleGround.battle();
    }
}
