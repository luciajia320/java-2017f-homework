import java.util.ArrayList;

public class EvilCamp extends Camp {
    @Override
    void createFormation(){ //第一次总是需要创建队形的，待全部人都加入后
        ArrayList<String> names=new ArrayList<String>();
        for(Creature i:members){
            names.add(i.getName());
        }
        if(formation==null)//创建最初的队形
        {
            formation = new GooseFlyingFormation(names);
        }
    }
    void changeFormation(Ground ground){//邪恶阵营改变阵型,给定起点
        ArrayList<String> names=new ArrayList<String>();
        for(Creature i:members){
            names.add(i.getName());
        }
        formation=new CraneWingFormation(names);
        try {
            ground.layoutFormation(formation, ground.getPosition(3, 6)); //放置阵型
        }catch (LayOutException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
