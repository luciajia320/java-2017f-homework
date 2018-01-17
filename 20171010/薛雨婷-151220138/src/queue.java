import java.util.ArrayList;
import java.util.Collections;

public class queue {
    private ArrayList<creature> huluwaQueue;
    public queue(ArrayList<? extends creature> creatures){
        huluwaQueue=new ArrayList<>();
        for(int i=0;i<creatures.size();i++){
            huluwaQueue.add(i,creatures.get(i));
        }
    }

    public creature getCreat(int i){
        return huluwaQueue.get(i);
    }

    public void ramdom(){
        Collections.shuffle(huluwaQueue);
    }
}
