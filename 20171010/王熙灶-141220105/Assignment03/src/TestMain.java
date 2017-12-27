import creature.CalaCrops;
import creature.EssenceCrops;
import creature.GrandPa;
import creature.SnakeEssence;
import formation.*;
import space.Space;

public class TestMain {
    public static void main(String[] args) {
        Space space = new Space(11);

        CalaCrops calaCrops = new CalaCrops();
        calaCrops.randomPermutation();
        calaCrops.setFormation(new LongSnake(space, 2, 2));
        System.out.println("Scenario 1: 乱序葫芦娃加入战场，长蛇阵法！");
        System.out.println(space);

        calaCrops.clearFormation();
        calaCrops.sort();
        calaCrops.setFormation(new LongSnake(space, 2, 2));
        System.out.println("Scenario 2: 葫芦娃整理战队，重新登场！");
        System.out.println(space);

        EssenceCrops essenceCrops = new EssenceCrops();
        essenceCrops.setFormation(new FengShi(space, 5, 5));
        System.out.println("Scenario 3: 蝎子精带领小喽啰加入战场，锋矢阵法！");
        System.out.println(space);

        GrandPa grandPa = new GrandPa();
        space.positionSetter(grandPa, 10, 2);

        SnakeEssence snakeEssence = new SnakeEssence();
        space.positionSetter(snakeEssence, 7, 5);
        System.out.println("Scenario 4: 爷爷和蛇精加入战场，为双方助威！");
        System.out.println(space);

        essenceCrops.clearFormation();
        essenceCrops.setFormation(new DongE(space, 4, 4));
        System.out.println("Scenario 5: 蝎子精战队变换阵法，动轭阵法！");
        System.out.println(space);

        essenceCrops.clearFormation();
        essenceCrops.setFormation(new HeYi(space, 5, 5));
        System.out.println("Scenario 6: 蝎子精战队变换阵法，鹤翼阵法！");
        System.out.println(space);

        essenceCrops.clearFormation();
        essenceCrops.setFormation(new FangYuan(space, 5, 5));
        System.out.println("Scenario 7: 蝎子精战队变换阵法，方円阵法！");
        System.out.println(space);
    }
}