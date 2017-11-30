
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

    	Ground g = new Ground(10);
    	//葫芦娃阵营
    	HuluFaction huluFaction = new HuluFaction();
    	huluFaction.huluInitialize(); //初始化
    	huluFaction.quickSort(); //排序
    	SnakeFormation snake = new SnakeFormation(huluFaction); 
    	
    	//蝎子精阵营
    	ScorpionFaction scorpionFaction = new ScorpionFaction();
    	//将不同阵型存入数组中
        List<Formation> list = new ArrayList<Formation>();
        
        //鶴翼阵
        list.add(new GooseFormation(scorpionFaction)); 
        //雁行阵
        list.add(new GeeseFormation(scorpionFaction));
        //鱼鳞阵
        list.add(new FishScalesFormation(scorpionFaction));
        //方门阵
        list.add(new SideDoorFormation(scorpionFaction));
        //偃月阵
        list.add(new CrescentMoonFormation(scorpionFaction));
        //锋矢阵
        list.add(new FengShiFormation(scorpionFaction));
        //冲轭阵
        list.add(new ChongEFormation(scorpionFaction));
        try{
        	for(int i = 0;i < list.size();++i) { 		
        		g.layout(snake, new Location(0,0));
        		g.layout(list.get(i), new Location(0,4));
        		//放置爷爷和蛇精分别为双方加油助威
        		g.layout(new GrandFather(), new Location(3,1));
        		g.layout(new SheJing(), new Location(9,4));
        		System.out.print(g);
        		g.clear();
        	}
        }catch ( Exception e){
            e.printStackTrace();
        }
    }
}
