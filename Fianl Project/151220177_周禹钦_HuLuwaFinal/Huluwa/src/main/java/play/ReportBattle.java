package  play;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by qin on 18.1.3.
 */
public class ReportBattle implements Serializable{
    ObjectOutputStream outfile;
    public ReportBattle(int x){
        String filename=String.valueOf(x);
        try {
            OutputStream output = new FileOutputStream(filename);
            outfile=new ObjectOutputStream(output);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void reportInit(int i,int j){
        System.out.println("mode "+(i-1)+","+"mode "+(j-1));

        try{
                outfile.writeInt(i);
                outfile.writeInt(j);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void reportMovement(Player p,int x,int y){
        System.out.println(p.ord()+"->"+x+","+y);
        try{
            outfile.writeInt(0);
            outfile.writeInt(p.ord());
            outfile.writeInt(x);
            outfile.writeInt(y);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void reportKill(int i){
        System.out.println("player "+i+" dead");
        try{
            outfile.writeInt(1);
            outfile.writeInt(i);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void endReport(){
        try {
            outfile.writeInt(-1);
            outfile.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
