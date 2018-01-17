import battlefield.Field;
import mainroles.Huluwa;
import mainroles.Slime;
import mainroles.Xiezijing;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class World extends JFrame {
    private Field field;
    private Huluwa h1;
    private Huluwa h2;
    private Huluwa h3;
    private Huluwa h4;
    private Huluwa h5;
    private Huluwa h6;
    private Huluwa h7;
    private Xiezijing xzj;
    private Slime s1;
    private Slime s2;
    private Slime s3;
    private Slime s4;
    private Slime s5;
    private Slime s6;
    private Slime s7;
    ExecutorService exec;
    World()
    {

        field = new Field();
        add(field);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLocationRelativeTo(null);
        setTitle("葫芦娃大战");

        h1 = new Huluwa("h1",field,field.getPosition(5,7));
        h2 = new Huluwa("h2",field,field.getPosition(5,8));
        h3 = new Huluwa("h3",field,field.getPosition(5,9));
        h4 = new Huluwa("h4",field,field.getPosition(5,10));
        h5 = new Huluwa("h5",field,field.getPosition(5,11));
        h6 = new Huluwa("h6",field,field.getPosition(5,12));
        h7 = new Huluwa("h7",field,field.getPosition(5,13));
        xzj = new Xiezijing("xzj",field,field.getPosition(14,10));
        s1 = new Slime("s1",field,field.getPosition(15,7));
        s2 = new Slime("s2",field,field.getPosition(15,8));
        s3 = new Slime("s3",field,field.getPosition(15,9));
        s4 = new Slime("s4",field,field.getPosition(15,10));
        s5 = new Slime("s5",field,field.getPosition(15,11));
        s6 = new Slime("s6",field,field.getPosition(15,12));
        s7 = new Slime("s7",field,field.getPosition(15,13));

    }

    public void GameStart()
    {
        exec = Executors.newCachedThreadPool();
        exec.execute(h1);
        exec.execute(h2);
        exec.execute(h3);
        exec.execute(h4);
        exec.execute(h5);
        exec.execute(h6);
        exec.execute(h7);
        exec.execute(xzj);
        exec.execute(s1);
        exec.execute(s2);
        exec.execute(s3);
        exec.execute(s4);
        exec.execute(s5);
        exec.execute(s6);
        exec.execute(s7);
    }
    public void killall()
    {
        s1.beKilled();
        s2.beKilled();
        s3.beKilled();
        s4.beKilled();
        s5.beKilled();
        s6.beKilled();
        s7.beKilled();
        h1.beKilled();
        h2.beKilled();
        h3.beKilled();
        h4.beKilled();
        h5.beKilled();
        h6.beKilled();
        h7.beKilled();
        xzj.beKilled();
    }
    public void paint()
    {
        field.repaint();

        h1.setGo();
        h2.setGo();
        h3.setGo();
        h4.setGo();
        h5.setGo();
        h6.setGo();
        h7.setGo();
        xzj.setGo();
        s1.setGo();
        s2.setGo();
        s3.setGo();
        s4.setGo();
        s5.setGo();
        s6.setGo();
        s7.setGo();
    }

    public boolean gameover()
    {
        return (s1.getLive()==false&&s2.getLive()==false&&s3.getLive()==false&&s4.getLive()==false&&s5.getLive()==false
        &&s6.getLive()==false&&s7.getLive()==false&&xzj.getLive()==false)||
                (h1.getLive()==false&&h2.getLive()==false&&h3.getLive()==false&&h4.getLive()==false&&h5.getLive()==false
                &&h6.getLive()==false&&h7.getLive()==false);
    }
}
