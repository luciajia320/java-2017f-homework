package wz;

import wz.Creature.*;
import wz.form.*;
import wz.position.RectField;

public class GodIsGod {
    public static void main(String[] args) {

        //        //葫芦树上结了7个葫芦
        //        Huluwa[] hulus = HuluTree.createHuluwas();
        //
        //        //生成一个队列
        //        Queue queue = new Queue();
        //
        //        //将葫芦兄弟随机放到队列中
        //        queue.enQueue(hulus);
        //
        //        //队列上的葫芦兄弟随机站队
        //        queue.randomQueue();
        //
        //        //葫芦兄弟根据名字报数
        //        queue.reportByName();
        //
        //        //队列上的葫芦兄弟冒泡排序
        //        //注: 排序都是借助0号位置来完成, 而非直接交换两个葫芦娃的位置
        //        queue.bubbleSort();
        //
        //        //葫芦兄弟根据名字报数
        //        queue.reportByName();
        //
        //        //队列上的葫芦兄弟随机站队
        //        queue.randomQueue();
        //
        //        //葫芦兄弟根据颜色报数
        //        queue.reportByColor();
        //
        //        //队列上的葫芦兄弟快速排序
        //        queue.quickSort();
        //
        //        //葫芦兄弟根据颜色报数
        //        queue.reportByColor();

        //生成一个方形场地
        RectField field = new RectField(9, 13);

        //生成葫芦兄弟
        Huluwa[] brothers = HuluTree.createHuluwas();

        //葫芦兄弟排成长蛇阵
        Form form = new FormChangShe();
        int hulurow = 1, hulucol = 1;
        form.put(brothers, hulurow, hulucol, field);
        //随机
        field.shuffle(brothers, hulurow, hulucol);
        //排序
        new BubbleSort().sort(field, brothers, hulurow, hulucol); //排序

        //生成7个妖怪
        Monster[] monsters = new Monster[7];
        //蝎子精
        monsters[0] = new Xiezijing();
        ////小喽啰
        for(int i = 1; i < 7; i++) {
            monsters[i] = new Xiaoloulou();
        }

        //妖怪排成鹤翼阵
        int monsterRow = 1, monsterCol = 5;
        form = new FormHeyi();
        form.put(monsters, monsterRow, monsterCol, field);


        //爷爷助威
        Creature yeye = new Grandfather();
        int yeyeRow = 7, yeyeCol = 2;
        field.addCreature(yeye, yeyeRow, yeyeCol);

        //蛇精助威
        Creature shejing = new Shejing();
        int sheRow = 7, sheCol = 8;
        field.addCreature(shejing, sheRow, sheCol);

        //对峙局面
        field.displayMap();

        //妖怪取消取鹤翼阵 改为雁形阵
        form.reset(monsters, monsterRow, monsterCol, field);
        form = new FormYanxing();
        form.put(monsters, monsterRow, monsterCol, field);

        //对峙局面
        field.displayMap();

        //妖怪取消取雁形阵 改为鹤翼阵
        form.reset(monsters, monsterRow, monsterCol, field);
        form = new FormHeyi();
        form.put(monsters, monsterRow, monsterCol, field);

        //对峙局面
        field.displayMap();
    }
}
