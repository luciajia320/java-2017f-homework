import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BackGround extends Thing2D{

    public BackGround(){
        super(0,0);
        URL loc = this.getClass().getClassLoader().getResource( "background.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
