import javax.swing.*;
import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main{
    public static void main(String[] args){
        map battle_map=new map();
        Ground my_ground=new Ground(battle_map);
        my_ground.setTitle("Huluwa vs Minion");
        my_ground.setSize(1050,700);
        my_ground.setVisible(true);
        my_ground.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try{
            Formation ftry=new Formation(battle_map);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("exception solved");
        }

        try{
            CraneWing cwtry=new CraneWing(battle_map);
            throw new Exception();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("exception solved");
        }

      return;
    }
}