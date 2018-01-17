import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Creature extends  TinyImage {
    private BattleField fHandler;
    protected boolean alive;
    public Creature(BattleField fHandler, String picPath, int x, int y){
        super(x,y);
        alive = true;
        this.fHandler = fHandler;
        URL url = this.getClass().getClassLoader().getResource(picPath);
        setImage(new ImageIcon(url).getImage());
       // System.out.println(url);
    }
}
