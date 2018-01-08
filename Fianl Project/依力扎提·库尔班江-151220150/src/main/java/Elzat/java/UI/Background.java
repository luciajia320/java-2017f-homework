package UI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

//此类作为背景出现
public final class Background extends JFrame{

    private final int OFFSETY = 280;
    private final int OFFSETX=100;

    Field field=new Field();

    private BackgroundPanel background;

    JLayeredPane layeredPane=new JLayeredPane();

    public Background() {
        InitUI();
    }

    public Field getField() {
        return field;
    }


    public void InitUI() {

        //加载背景图片
        URL loc = this.getClass().getClassLoader().getResource("background.png");
        background=new BackgroundPanel(new ImageIcon(loc));
        background.setBounds(0,0,background.getIm().getIconWidth(),background.getIm().getIconHeight());

        //获得当前窗口并将背景图片加载进去
        Container ct=this.getContentPane();
        this.setLayout(null);//布局设为null之后所有的部件都要setBounds
        ct.add(background);

        //设置战场

        field.setBackground(null); // 把背景设置为会  
        field.setOpaque(false);
        field.setBounds(OFFSETX,OFFSETY,field.getBoardWidth(),field.getBoardHeight());
        ct.add(field);

        //层次布局设置
        layeredPane.add(background,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(field,JLayeredPane.MODAL_LAYER);

        //窗口设置
        setLayeredPane(layeredPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(background.getIm().getIconWidth(),background.getIm().getIconHeight());
        setLocationRelativeTo(null);
        setResizable(false);//测试
        setTitle("Ground");
    }


    public static void main(String[] args) {
        Background ground = new Background();
        ground.setVisible(true);
    }
}


class BackgroundPanel extends  JPanel{
    ImageIcon im;
    public BackgroundPanel (ImageIcon im)
    {
        this.im=im;
        this.setOpaque(true);
    }

    public  void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(im.getImage(),0,0,this.getWidth(),this.getHeight(),this);
    }

    public ImageIcon getIm() {
        return im;
    }
}
