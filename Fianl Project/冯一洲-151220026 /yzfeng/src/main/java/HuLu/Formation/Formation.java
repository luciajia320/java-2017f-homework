package HuLu.Formation;

import HuLu.Creature.*;
import HuLu.Field.Field;


import java.util.ArrayList;

public class Formation {
    public static void HeYiformation(Field field, ArrayList<GoodMan>items){
       Holder holders[][] = field.getHolders();
       for(int i = 1; i <= 4; i++){
           items.get(i-1).moveTo(i,  i);
       }
       for(int i = 5; i<=7; i++){
           items.get(i-1).moveTo(8-i, i);
       }
       items.get(7).moveTo(0, 4);

    }

    public static void Snakemation(Field field, ArrayList<BadMan>items){
        Holder holders[][] = field.getHolders();
        for(int i = 0; i < 4; i++){
            items.get(i).moveTo(14, 2 * i + 1);
        }
        for(int i = 4; i<7; i++){
            items.get(i).moveTo(14, 2 * i - 6);
        }
        items.get(7).moveTo(15, 4);
    }
}
