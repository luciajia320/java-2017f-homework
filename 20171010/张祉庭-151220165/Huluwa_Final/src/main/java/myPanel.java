import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class myPanel extends JPanel {
    public Position[][] positions;
    public int N=13;
    public map my_map;

    public void InitUI(Graphics g){
        g.setColor(Color.green);
        g.fillRect(0,0,1050,700);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              /*  ImageIcon aii=new ImageIcon("./src/main/resources/tile.png");
                Image image=aii.getImage();
                g.drawImage(image,50*(i+4),50*j,40,40,this);
                */
                g.setColor(Color.PINK);
                g.fillRect(50*(4+i),50*j,40,40);
            }
        }
    }



    public void showmap(Graphics g)
    {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(positions[i][j].isHolderNull()==false) {
                    Image image = positions[i][j].ReturnHolder().getImage();
                    g.drawImage(image, 50 * (i+4), 50 * j, 40, 40, this);
                }
            }
        }
    }

    public void map_formation(map battle_map)
    {
        Formation ff=new Formation(battle_map);
    }


    public void map_CraneWing(map battle_map){
        CraneWing cw=new CraneWing(battle_map);
    }

    public void map_flying(map battle_map)
    {
        Flying fl=new Flying(battle_map);
    }

    public void map_SquareFormation(map battle_map)
    {
        SquareFormation sq=new SquareFormation(battle_map);
    }

    public void paint(Graphics g)
    {
        InitUI(g);
        showmap(g);
    }

    myPanel(map battle_map)
    {
        this.positions=battle_map.positions;
        this.my_map=battle_map;
        addKeyListener(new myKeyAdapter());
        setFocusable(true);
    }

    class myKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            //        super.keyTyped(e);

            int key=e.getKeyCode();
            if(key==KeyEvent.VK_1)
            {
                map_formation(my_map);
                repaint();
            }
            else if(key==KeyEvent.VK_2)
            {
                map_CraneWing(my_map);
                repaint();
            }
            else if(key==KeyEvent.VK_3)
            {
                map_flying(my_map);
                repaint();
            }
            else if(key==KeyEvent.VK_4)
            {
                map_SquareFormation(my_map);
                repaint();
            }

            //   repaint();
        }
    }

}
