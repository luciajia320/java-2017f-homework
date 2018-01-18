
import javax.swing.*;
import java.awt.*;

public class Ground extends JFrame {

    public Ground(map battle_map) {
        myPanel my_panel=new myPanel(battle_map);
       add(my_panel);
    }
}


