import creature.animal.CalaCrops;
import creature.animal.EssenceCrops;
import creature.animal.GrandPa;
import creature.animal.SnakeEssence;
import formation.*;
import exception.FormationException;
import space.Space;

import java.io.FileWriter;
import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        Space space = new Space(11);
        FileWriter fr = new FileWriter("target/output.txt");
        StringBuilder stringBuilder = new StringBuilder();

        CalaCrops calaCrops = CalaCrops.getInstance();
        calaCrops.shuffle();
        try {
            calaCrops.setFormation(new LongSnake(space, 2, 2));
        } catch (FormationException e) {
            e.printStackTrace();
        }
        stringBuilder.append("Scenario 1: 乱序葫芦娃加入战场，长蛇阵法！\n");
        stringBuilder.append(space.toString()).append('\n');

        calaCrops.clearFormation();
        calaCrops.sort();
        try {
            calaCrops.setFormation(new LongSnake(space, 2, 2));
        } catch (FormationException e) {
            e.printStackTrace();
        }
        stringBuilder.append("Scenario 2: 葫芦娃整理战队，重新登场！\n");
        stringBuilder.append(space.toString()).append('\n');

        EssenceCrops essenceCrops = EssenceCrops.getInstance();
        try {
            essenceCrops.setFormation(new FengShi(space, 5, 5));
        } catch (FormationException e) {
            e.printStackTrace();
        }
        stringBuilder.append("Scenario 3: 蝎子精带领小喽啰加入战场，锋矢阵法！\n");
        stringBuilder.append(space.toString()).append('\n');

        GrandPa grandPa = GrandPa.getInstance();
        space.creature_position_setter(grandPa, 10, 2);

        SnakeEssence snakeEssence = SnakeEssence.getInstance();
        space.creature_position_setter(snakeEssence, 7, 5);
        stringBuilder.append("Scenario 4: 爷爷和蛇精加入战场，为双方助威！\n");
        stringBuilder.append(space.toString()).append('\n');

        essenceCrops.clearFormation();
        try {
            essenceCrops.setFormation(new DongE(space, 4, 4));
        } catch (FormationException e) {
            e.printStackTrace();
        }
        stringBuilder.append("Scenario 5: 蝎子精战队变换阵法，动轭阵法！\n");
        stringBuilder.append(space.toString()).append('\n');

        essenceCrops.clearFormation();
        try {
            essenceCrops.setFormation(new HeYi(space, 5, 5));
        } catch (FormationException e) {
            e.printStackTrace();
        }
        stringBuilder.append("Scenario 6: 蝎子精战队变换阵法，鹤翼阵法！\n");
        stringBuilder.append(space.toString()).append('\n');

        essenceCrops.clearFormation();
        try {
            essenceCrops.setFormation(new FangYuan(space, 5, 5));
        } catch (FormationException e) {
            e.printStackTrace();
        }
        stringBuilder.append("Scenario 7: 蝎子精战队变换阵法，方円阵法！\n");
        stringBuilder.append(space.toString()).append('\n');

        System.out.println(stringBuilder.toString());
        fr.write(stringBuilder.toString());
        fr.close();
    }
}