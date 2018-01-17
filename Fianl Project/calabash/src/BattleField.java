import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class BattleField extends JPanel {
    private final int SPACE = 20;
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Huluwa> huluwas = new ArrayList<>();
    public BattleField(){

        addKeyListener(new KeyBoard());
        setFocusable(true);
        addAnimals();
    }

    public void addAnimals(){
        String prototype =
                ".1.....m..\n"+
                ".2....m...\n"+
                ".3....mmm.\n"+
                "..4...mm..\n"+
                "..5...mmm.\n"+
                ".6....m...\n"+
                ".7.....m..\n";
        int x = 0, y = 0;
        String[] calabash_path = {"red.png", "ora.png", "yel.png", "gre.png",
                "gbl.png", "blu.png", "vio.png"};
        int enemeid = 0, calabashid = 0;
        for(int i = 0; i < prototype.length(); i++){
            int ch = prototype.charAt(i);

            if(ch <= '7' && ch >='1'){
                int index = ch -'1';
                Huluwa huluwa = new Huluwa(this, calabash_path[index], x, y, calabashid++);
                x+=SPACE;
                huluwas.add(huluwa);
                continue;
            }
            switch(ch){
                case '.':
                    x+= SPACE;
                    break;
                case 'm':
                    Enemy enemy = new Enemy(this, "ene.png",x,y,enemeid++);
                    x+=SPACE;
                    enemies.add(enemy);
                    break;
                case '\n':
                    x = 0;
                    y+= SPACE;
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    private void draw(Graphics g){
        ArrayList<TinyImage> allimage = new ArrayList<>();
        allimage.addAll(enemies);
        allimage.addAll(huluwas);
        for(TinyImage tinyImage: allimage){
            g.drawImage(tinyImage.getImage(), tinyImage.getX(), tinyImage.getY(), this);
        }
    }

    public class KeyBoard extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            switch(key){
                case KeyEvent.VK_L:
                    System.out.println('L');
                    break;
                case KeyEvent.VK_R:
                    System.out.println('R');
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println(' ');
                    break;
                default:
                    break;
            }
        }
    }
}
