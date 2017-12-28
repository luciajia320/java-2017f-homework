import java.util.ArrayList;

public class Main {
    final int N=15;
    static final int HLW_SUM=7;
    static final int LL_SUM=6;
    public static void main(String[] args) {
        //生成葫芦娃
        ArrayList<Huluwa> brothers= new ArrayList<>();
        for (int i = 0; i < HLW_SUM; i++) {
            brothers.add( new Huluwa(COLOR.values()[i], SENIORITY.values()[i] ,new Position(0,i)));
        }
        Queue huluwaqueue = new Queue (HLW_SUM);   //生成葫芦娃队
        for (int i=0;i<brothers.size();++i)
            huluwaqueue.JoinIn(brothers.get(i));
        new Snake().format(huluwaqueue);        //葫芦娃排成长蛇形
        Field field = new Field();
        field.putIn(huluwaqueue);   //葫芦娃放进field
     //   field.shuffle();        //葫芦娃乱序
        /*
        field.rollCall();
        System.out.println();
        */
     //   new BubbleSorter().sort(field,1);   //葫芦娃排序

        //生成喽罗
        ArrayList<LouLuo>louluos = new ArrayList<>();
        for (int i = 0; i < LL_SUM; i++) {
            louluos.add ( new LouLuo());
        }
        Monster xiezijing = new Monster("🐛");
        Queue monsterqueue = new Queue(HLW_SUM);         //生成妖怪队
        monsterqueue.JoinIn(xiezijing);
        for (int i=0;i<louluos.size();++i)
            monsterqueue.JoinIn(louluos.get(i));
        new CraneWing().format(monsterqueue);    //妖怪鹤翼形
        field.putIn(monsterqueue);

        Creature grandfather = new Grandfather("🎅");  //0,0
        Monster  shejing = new Monster("🐍");      //14,14
        Queue audiencequeue = new Queue(2);  //观战助威队
        audiencequeue.JoinIn(grandfather);
        audiencequeue.JoinIn(shejing);
        new Audience().format(audiencequeue);
        field.putIn(audiencequeue);

        field.rollCall();
/*
        System.out.println();
        field.clean();    //清空  blank填满field   重新站队形 翼形
        field.putIn(huluwaqueue);
        new Goose().format(monsterqueue);
        field.putIn(monsterqueue);
        field.putIn(audiencequeue);
        field.rollCall();
*/

    }
}
