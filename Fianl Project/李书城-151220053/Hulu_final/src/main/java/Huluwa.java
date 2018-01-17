import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

//enum IndexHulu{大,二,三,四,五,六,七}
//enum Color{红,橙,黄,绿,青,蓝,紫}

public class Huluwa extends Speices implements Runnable{
    public int cnt;
  //  private IndexHulu index;
 //   private Color color;

    public Huluwa(int x, int y, int cnt,int index, Fight fight, String pic) {
        super(x, y, fight, pic);
     /*   switch (index)
        {
            case 1:
                this.index = IndexHulu.大;
                this.color = Color.红;
                break;
            case 2:
                this.index = IndexHulu.二;
                this.color = Color.橙;
                break;
            case 3:
                this.index = IndexHulu.三;
                this.color = Color.黄;
                break;
            case 4:
                this.index = IndexHulu.四;
                this.color = Color.绿;
                break;
            case 5:
                this.index = IndexHulu.五;
                this.color = Color.青;
                break;
            case 6:
                this.index = IndexHulu.六;
                this.color = Color.蓝;
                break;
            case 7:
                this.index = IndexHulu.七;
                this.color = Color.紫;
                break;
        }
*/
        URL loc = this.getClass().getClassLoader().getResource(pic);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);

        this.cnt = cnt;
    }


    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();
            try {
                if(this.dead ==1) break;
                Thread.sleep(rand.nextInt(200) + 200);

                Thing2D t = fight.fightWithEnemy(this);
                this.fight.huluwaStrength(t, this);

                this.repaint();

            } catch (Exception e) {

            }
        }
    }
    /*
        private int index;
    public Huluwa(int i)
    {
        this.index = i;
        this.spieces = 0;
    }
    public void appear(){
        switch (index)
        {
            case 1:
                System.out.print("大 ");
                break;
            case 2:
                System.out.print("二 ");
                break;
            case 3:
                System.out.print("三 ");
                break;
            case 4:
                System.out.print("四 ");
                break;
            case 5:
                System.out.print("五 ");
                break;
            case 6:
                System.out.print("六 ");
                break;
            case 7:
                System.out.print("七 ");
                break;
        }
    }
    public int return_index()
    {
        return index;
    }
     */
}