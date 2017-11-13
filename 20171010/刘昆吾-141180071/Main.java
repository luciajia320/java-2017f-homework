
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

    	Ground g = new Ground(10);
    	SnakeFormation snake = new SnakeFormation("🍎");
    	
        List<Formation> list = new ArrayList<Formation>();
        list.add(new GooseFormation("🐸"));
        list.add(new GeeseFormation("🐸"));
        list.add(new FishScalesFormation("🐸"));
        list.add(new SideDoorFormation("🐸"));
        list.add(new CrescentMoonFormation("🐸"));
        list.add(new FengShiFormation("🐸"));
        list.add(new ChongEFormation("🐸"));
        try{
        	for(int i = 0;i < list.size();++i) { 		
        		g.layout(snake, new Location(0,0));
        		g.layout(list.get(i), new Location(0,4));
        		System.out.print(g);
        		g.clear();
        	}
        }catch ( Exception e){
            e.printStackTrace();
        }
    }
}
