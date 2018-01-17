/**
 * Created by qin on 18.1.3.
 */
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
public class ReplayBattle implements Serializable,Runnable{
    ObjectInputStream infile;
    private ArrayList AllAction;
    private int nAct;
    private Field fieldp;
    private int fi=2;
    public boolean isFinished(int i){
        if(i==nAct)
            return true;
        else return false;
    }
    public boolean isMove(int i){
        if((int)AllAction.get(i)==0)
            return true;
        else return false;
    }
    public boolean isKill(int i){
        if((int)AllAction.get(i)==1)
            return true;
        else return false;
    }
    //public int at(int i){
      //  return (int)AllAction.get(i);
   // }
    private void getAllRecord(){
        boolean t=true;
        try {
            while(t){
                int a=0;a=infile.readInt();
                AllAction.add(a);
                nAct++;
                //System.out.println(a);
                if(a==-1)
                {System.out.print("suc");return;}
            }
        }
        catch (Exception e){
            t=false;
            e.printStackTrace();
        }
    }
    public ReplayBattle(Field d){
        JFileChooser fd=new JFileChooser();
        fd.showOpenDialog(null);
        File f=fd.getSelectedFile();
        try{
            InputStream input=new FileInputStream(f);
            infile=new ObjectInputStream(input);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        AllAction=new ArrayList();
        nAct=0;
        fieldp=d;
    }
    public int[] replayInit(){
        int[] A=new int[2];
        getAllRecord();
        A[0]=(int)AllAction.get(0);
        A[1]=(int)AllAction.get(1);
        endReplay();
        return A;
    }
    private void endReplay(){
        try{
            infile.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void run() {
        while (!Thread.interrupted()) {
            if (this.isMove(fi)) {
                int p = (int)AllAction.get(fi + 1);
                int x = (int)AllAction.get(fi + 2);
                int y = (int)AllAction.get(fi + 3);
                System.out.println(p + "->" + x + "," + y);
                fieldp.moveplayer(p,x,y);
                fi += 4;
                fieldp.repaint();
            }
            else if (this.isKill(fi)) {
                int k = (int)AllAction.get(fi + 1);
                fieldp.killplayer(k);
                System.out.println("player " + k + " dead");
                fi += 2;
                fieldp.repaint();
            }
            else Thread.interrupted();
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
            }
        }

    }
}
