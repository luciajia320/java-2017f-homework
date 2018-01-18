import java.util.*;

public abstract class Camp {
    protected ArrayList<Creature> members;
    protected Creature leader; //一些成员和一个leader
    protected Formation formation; //每个阵营都有自己的阵型
    public Camp(){
        members=new ArrayList<Creature>();
        formation=null;
    }

    void setLeader(Creature leader){
        this.leader=leader;
    }
    Creature getLeader(){
        return leader;
    }
    void addMember(Creature creature){
        members.add(creature);
    }

    void printFormation(){//打印自己的阵型
        System.out.print(formation.toString());
    }
    void setFormation(Formation formation){ //布阵函数
        this.formation=formation;
    }
    abstract void createFormation(); //具体队形具体创建
    void setMembersPosition(Ground ground){ //设置成员位置，必须保证在土地上加入了阵型以后
        for(int i=0;i<members.size();i++) {
            //获得阵型的起始点
            Position pos=ground.getPosition(formation.getBeginPosition().getX()+i,formation.getBeginPosition().getY());
            ground.firstlayout(members.get(i), pos);
        }
    }
}
