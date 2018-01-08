package HL;
public class Main {
    public static void main(String args[]){

        //创建一个10 * 10的场景
        Field field = new Field(10);

        //葫芦娃一字排开
        Huluwa[] huluwas = new Huluwa[7];
        for (int i = 0; i < 7; i++) {
            huluwas[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
        }
        new SnakeFormation(field, huluwas, 0);

        //爷爷在一旁助威
        Grandpa grandpa = new Grandpa();
        field.setCreature(grandpa, 2, 1);

        //小喽啰雁型排开
        Xiaoloulou[] xiaoloulou = new Xiaoloulou[7];
        for (int i = 0; i < 7; i++) {
            xiaoloulou[i] = new Xiaoloulou();
        }
        new Yanxing(field, xiaoloulou, 9);

        //蝎子精一旁助威
        Xiezijing xiezijing = new Xiezijing();
        field.setCreature(xiezijing, 3, 3);

        //显示出来
        field.display();

        //清空一下
        field.setNull();
        System.out.println();

        //葫芦娃重新长蛇阵
        new SnakeFormation(field, huluwas, 9);

        //小喽啰重新按鹤翼布阵
        new HeYi(field, xiaoloulou, 3);

        //爷爷和蝎子精在一旁助威
        field.setCreature(grandpa, 3, 8);
        field.setCreature(xiezijing, 4, 3);

        //显示
        field.display();

        //清空
        field.setNull();
    }
}
