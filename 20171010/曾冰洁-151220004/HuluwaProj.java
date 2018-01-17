import java.util.ArrayList;

public class HuluwaProj {
    public static void main(String[] args){
        final int N = 7;
        //創建七個葫蘆娃
        Queue huluwaQueue = new Queue(N);
        ArrayList<Huluwa> huluwas = new ArrayList<>();
        for (int i=0; i<N; i++){
            huluwas.add(new Huluwa(i, COLOR.values()[i]));
        }
        huluwaQueue.randomEnqueue(huluwas); //葫蘆娃初始亂序
        new BubbleSorter().sort(huluwaQueue); //葫蘆娃按排行站隊
        new ChangSheXing().format(huluwaQueue); //葫蘆娃站成長蛇陣型
        //創建一個蝎子精和一堆小嘍啰，依次入隊
        Queue xiezijingQueue = new Queue(N);
        xiezijingQueue.enqueue(new XieZiJing());
        for (int i=0; i<N-1; i++){
            xiezijingQueue.enqueue(new XiaoLouLuo());
        }
        new HeYiXing().format(xiezijingQueue); //蝎子精帶領小嘍啰站成鶴翼陣型
        //造一個二維空間
        Field battleField = new Field(15);
        //葫蘆娃和蝎子精小嘍啰放入二維空間
        battleField.putQueueIn(huluwaQueue, new Coord(2,4));
        battleField.putQueueIn(xiezijingQueue, new Coord(6,4));
        //爺爺和蛇精放入合適的位置加油
        Grandfather grandfather = new Grandfather();
        Coord grandfatherCoord = new Coord(0,7);
        Shejing shejing = new Shejing();
        Coord shejingCoord = new Coord(11,7);
        battleField.putCreatureIn(grandfather, grandfatherCoord);
        battleField.putCreatureIn(shejing, shejingCoord);
        //輸出對峙局面
        battleField.printSituation();
        System.out.println();
        //清空二維空間，蝎子精小嘍啰換一個陣型，重複上述步驟
        battleField.clearHolder();
        new FangYuanXing().format(xiezijingQueue);
        battleField.putQueueIn(huluwaQueue, new Coord(2,4));
        battleField.putQueueIn(xiezijingQueue, new Coord(6,4));
        battleField.putCreatureIn(grandfather, grandfatherCoord);
        battleField.putCreatureIn(shejing, shejingCoord);
        battleField.printSituation();

    }
}
