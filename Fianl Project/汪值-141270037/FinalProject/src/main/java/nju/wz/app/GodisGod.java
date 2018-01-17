package nju.wz.app;


import nju.wz.creature.*;
import nju.wz.form.Form;
import nju.wz.form.FormChangShe;
import nju.wz.form.FormHeyi;
import nju.wz.position.Field;
import nju.wz.position.Position;
import nju.wz.position.RectField;

import java.util.ArrayList;

public class GodisGod {

    public static Review getReview(Field field){
        return new Review(field);
    }
    public static ArrayList<Player> getPlayers(Field field) {
        //生成一个方形场地
        RectField rectField = new RectField(Field.FIELD_LENGTH, Field.FIELD_WIDTH);

        //生成葫芦兄弟
        Huluwa[] brothers = new Huluwa[7];
        for(int i = 0; i < brothers.length; i++) {
            brothers[i] = new Huluwa(field, i);
        }

        //葫芦兄弟排成长蛇阵
        Form form = new FormChangShe();
        int hulurow = 1, hulucol = 0;
        form.put(brothers, hulurow, hulucol, rectField);

        Creature yeye = new Grandfather(field, 7);
        int yeyeRow = 0, yeyeCol = 0;
        rectField.addCreature(yeye, yeyeRow, yeyeCol);

        Creature shejing = new Shejing(field, 8);
        int sheRow = 3, sheCol = 7;
        rectField.addCreature(shejing, sheRow, sheCol);

        Monster[] monsters = new Monster[7];
        //蝎子精
        monsters[0] = new Xiezijing(field, 9);
        //小喽啰
        for(int i = 1; i < monsters.length; i++) {
            monsters[i] = new Xiaoloulou(field, 9 + i);
        }

        int monsterRow = 0, monsterCol = 7;
        form = new FormHeyi();
        form.put(monsters, monsterRow, monsterCol, rectField);

        ArrayList<Player> players = new ArrayList<>();

        Position[][] pos = rectField.getPositions();

        for(int i = 0; i < pos.length; i++) {
            for(int j = 0; j < pos[i].length; j++) {
                if(pos[i][j] != null) {
                    Creature c = pos[i][j].getHolder();
                    if(c != null) {
                        int y1 = c.getPosition().getX() * Field.SPACE + Field.OFFSET;
                        int x1 = c.getPosition().getY() * Field.SPACE + Field.OFFSET;

                        Player p = (Player)c;
                        p.setX(x1);
                        p.setY(y1);
                        players.add((Player)c);
                    }
                }
            }
        }

        return players;
    }
}
