import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class Speices extends Thing2D {
    //0 for huluwa
    //1 for snake
    //2 for scorpion
    //3 for lackey
    //4 for grandpa

    public Fight fight;

    public int dead;

    private String pic;
    //血量上限
    private int blood;

    public int getBlood()
    {
        return blood;
    }

    //战斗之后收受伤，血量减少
    public void getWounded(int w)
    {
        this.blood -= w;
    }
    //退出江湖
    public void depressed()  {
        try {
            Thread.sleep(100000000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //constructor
    public Speices(int x, int y, Fight fight, String pic) {
        super(x, y);

        blood = 1000;
        this.fight = fight;
        this.pic = pic;

        URL loc = this.getClass().getClassLoader().getResource(pic);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);

        dead =0;
    }
    public void repaint() {
        this.fight.repaint();
    }


    /*
      protected int spieces;

    //输出
    public abstract void appear();
    //返回生物种类
    public int return_spieces()
    {
        return spieces;
    }
    //返回在本种族中的排行
    public abstract int return_index();
     */
}