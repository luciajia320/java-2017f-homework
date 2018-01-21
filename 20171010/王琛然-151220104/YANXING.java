import java.util.ArrayList;

public class YANXING implements Formation {
    private ArrayList<? extends Creature> creatures;

    @Override
    public void excuete(ArrayList<? extends Creature> creatures, Creature c){
        try {
            this.creatures = creatures;
        }catch(NullPointerException e) {
            System.out.println("ArrayList 空指针异常");
            e.printStackTrace();
        }
        int num = 7;
        int i = 0, j = 0;
        for (; i < num; i++, j++) {
            Position p = new Position(i + 5, j + 12);
            creatures.get(i).setPosition(p);
        }
        try{
            Position p = new Position(8, 18);
            c.setPosition(p);
        }catch(NullPointerException e) {
            System.out.println("Creature 空指针异常");
            e.printStackTrace();
        }
    }
}
