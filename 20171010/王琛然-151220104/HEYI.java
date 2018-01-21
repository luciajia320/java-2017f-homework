import javafx.geometry.Pos;

import javax.management.ListenerNotFoundException;
import java.util.ArrayList;

public class HEYI implements Formation {
    private ArrayList<? extends Creature> creatures;

    @Override
    public void excuete(ArrayList<? extends Creature> creatures, Creature c){
        int num = 7;
        try{
            this.creatures = creatures;
        }catch(NullPointerException e) {
            System.out.println("ArrayList 空指针异常");
            e.printStackTrace();
        }
        Position p = new Position(num/2 + 5, 15);
        this.creatures.get(0).setPosition(p);
        int j = 1;
        for(int i = 1; i < num; i++){
            if(i % 2 == 1){
                p = new Position(num/2 - j + 5, j + 15);
                this.creatures.get(i).setPosition(p);
            }
            else{
                p = new Position(num/2 + j + 5, j + 15);
                this.creatures.get(i).setPosition(p);
                j++;
            }
        }
        try {
            p = new Position(num / 2 + 5, 18);
            c.setPosition(p);
        }catch(NullPointerException e) {
            System.out.println("Creature 空指针异常");
            e.printStackTrace();
        }
    }
}
