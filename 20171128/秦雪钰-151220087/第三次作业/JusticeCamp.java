import java.util.ArrayList;
import java.util.Collections;

public class JusticeCamp extends Camp {

    public JusticeCamp(){
        super();
        membersorter=new SelectSorter();
    }
    Sorter membersorter;//用于排序
    void shuffleMembers(){//初始要乱序
        Collections.shuffle(members);
    }
    void sortMember(){//排序并且会把排序后结果反馈回队列
        ArrayList<Position>positions=new ArrayList<Position>();
        for(Creature i:members){
            positions.add(i.getPos());
        }
        membersorter.sort(positions);//对位置上的葫芦娃排序并且要把这个排序而的结果反馈给成员
        ArrayList<Creature>creatures=new ArrayList<Creature>();
        for(Position p:positions){
            creatures.add(p.getSitter());
        }
        members.clear();
        members=creatures; //把排序结果反馈回成员
        createFormation();//排序后队形信息重建
    }

    @Override
    void createFormation(){ //第一次总是需要创建队形的，待全部人都加入后
        ArrayList<String> names=new ArrayList<String>();
        for(Creature i:members){
            names.add(i.getName());
//            System.out.println(i.getName());
        }
        if(formation==null)
            formation=new LongSnakeFormation(names);
        else{
            LongSnakeFormation lf=(LongSnakeFormation)formation;
            lf.setHolderNames(names); //排序后会改变信息
        }
    }
//    void setMembersPosition(Ground ground){ //设置成员位置，必须保证在土地上加入了阵型以后
//        for(int i=0;i<members.size();i++) {
//            //获得阵型的起始点
//            Position pos=ground.getPosition(formation.getBeginPosition().getX()+i,formation.getBeginPosition().getY());
//            ground.firstlayout(members.get(i), pos);
//        }
//    }
}
