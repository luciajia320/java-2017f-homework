import java.util.ArrayList;

public class CraneWingFormation extends Formation {
    ArrayList<String> holdernames;//队形上面实质存在的个体名字
    CraneWingFormation(ArrayList<String> names){
        super(7,4);
        holdernames=new ArrayList<String>();
        holdernames=names;
        setHolderNamesContext();
    }
    void setHolderNamesContext(){
        content[3][0]=holdernames.get(0); //蝎子在的位置
        //剩余放喽啰
        for(int i=1;i<4;i++) {
            content[i-1][4 - i] = holdernames.get(i); //雁行的队形设置
        }
        for(int i=4;i<7;i++){
            content[i][i-3] = holdernames.get(i); //雁行的队形设置
        }
    }
}
