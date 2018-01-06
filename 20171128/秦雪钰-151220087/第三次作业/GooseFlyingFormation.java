import java.util.ArrayList;

public class GooseFlyingFormation extends Formation{
    ArrayList<String> holdernames;//队形上面实质存在的个体名字
    GooseFlyingFormation(ArrayList<String> names){
        super(7,7);
        holdernames=new ArrayList<String>();
        holdernames=names;
        setHolderNamesContext();
    }
    void setHolderNamesContext(){
        for(int i=0;i<7;i++) {
            content[6-i][i] = holdernames.get(i); //雁行的队形设置
        }
    }
}
