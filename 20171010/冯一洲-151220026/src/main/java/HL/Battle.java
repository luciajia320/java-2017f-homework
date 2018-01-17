package HL;

import HL.creature.Creature;
import HL.creature.Grandpa;
import HL.creature.Xiaoloulou;
import HL.creature.Xiezijing;
import HL.formation.HeYi;
import HL.formation.SnakeFormation;
import HL.formation.Yanxing;

import java.util.ArrayList;

public class Battle {
    private Field field;
    private ArrayList<Creature> huluwa = new ArrayList<Creature>();
    private ArrayList<Creature> xiaolouluo = new ArrayList<Creature>();
    private Grandpa grandpa;
    private Xiezijing xiezijing;

    public Battle(int N){
        //创建一个N * N的场景
        field = new Field(N);

        //葫芦娃
        for (int i = 0; i < 7; i++) {
            huluwa.add(new HL.Huluwa(HL.COLOR.values()[i], HL.SENIORITY.values()[i]));
        }
        //爷爷
        grandpa = new Grandpa();

        //小喽啰
        for (int i = 0; i < 7; i++) {
            xiaolouluo.add(new Xiaoloulou());
        }
        //蝎子精
        xiezijing = new Xiezijing();

    }

    public void BattleStart(){
        //葫芦娃蛇形排开
        new SnakeFormation(field, huluwa, 0).formation();
        //爷爷在助威
        field.setCreature(grandpa, 2, 1);

        //小喽啰雁型排开
        new Yanxing(field, xiaolouluo, 9).formation();
        //蝎子精一旁助威
        field.setCreature(xiezijing, 3, 3);

        //显示出来
        field.display();
        //清空一下
        field.setNull();
        System.out.println();

        //葫芦娃重新长蛇阵
        new SnakeFormation(field, huluwa, 9).formation();

        //小喽啰重新按鹤翼布阵
        new HeYi(field, xiaolouluo, 3).formation();

        //爷爷和蝎子精在一旁助威
        field.setCreature(grandpa, 3, 8);
        field.setCreature(xiezijing, 4, 3);

        //显示
        field.display();

        //清空
        field.setNull();
    }
}
