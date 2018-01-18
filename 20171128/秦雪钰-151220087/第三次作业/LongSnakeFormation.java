import java.util.ArrayList;

public class LongSnakeFormation extends Formation{
    //长蛇
    ArrayList<String> holdernames;//队形上面实质存在的个体名字
    LongSnakeFormation(ArrayList<String> names){
        super(7,1);
        holdernames=new ArrayList<String>();
        holdernames=names;
        setHolderNamesContext();
    }

    void setHolderNames(ArrayList<String> names){
        holdernames=names; //每次重置名字都要更新内容
        setHolderNamesContext();
    }
    void setHolderNamesContext(){
        for(int i=0;i<7;i++)
            content[i][0]=holdernames.get(i); //长蛇的队形设置
    }
}
