import java.util.ArrayList;

public class sort {
    public static  void main(String[] args) {
        /*brothers[] brother = new brothers[7];
        brother[0] = new brothers(COLORS.values()[3], SENIORITY.values()[3]);
        brother[1] = new brothers(COLORS.values()[2], SENIORITY.values()[2]);
        brother[2] = new brothers(COLORS.values()[5], SENIORITY.values()[5]);
        brother[3] = new brothers(COLORS.values()[4], SENIORITY.values()[4]);
        brother[4] = new brothers(COLORS.values()[6], SENIORITY.values()[6]);
        brother[5] = new brothers(COLORS.values()[1], SENIORITY.values()[1]);
        brother[6] = new brothers(COLORS.values()[0], SENIORITY.values()[0]);*/

        ArrayList<brothers> brother=new ArrayList<>();
        for(int i=0;i<7;i++)
           brother.add(new brothers(COLORS.values()[i],SENIORITY.values()[i]));
        //将葫芦娃入队并打乱顺序
        queue huluQueue=new queue(brother);
        huluQueue.ramdom();

        //蝎子精
        xiezijing xiezi=new xiezijing();

        //monsters[] monster=new monsters[6];

        //小喽啰
        ArrayList<monsters> monster=new ArrayList<>();
        for(int i=0;i<6;i++)
            monster.add(new monsters(i));

        //爷爷
        grandpa grand=new grandpa();

        //蛇精
        shejing snake=new shejing();

       // Map map=new Map(huluQueue,xiezi,monster);
       // map.shuffle();

        //创建地图，将葫芦娃、蝎子精、小喽啰放置上去
        Map map=new Map(13,10);
        map.PutOnMap(huluQueue,xiezi,monster);

        System.out.println("初始站位：");
        map.output();

        System.out.println("葫芦娃成长蛇队形：");
        new changshe().sort(map);
        map.output();

        System.out.println("蝎子精带领它的小喽啰布好阵：");
        new heyi().sort(map);
        map.output();

        System.out.println("老爷爷和蛇精前来助阵：");
        new GrandpaAndShejing(grand,snake).sort(map);
        map.output();

        System.out.println("蝎子精带领它的小喽啰转换阵型：");
        new jianshi().sort(map);
        map.output();
    }

}
