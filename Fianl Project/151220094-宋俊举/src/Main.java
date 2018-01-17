import battlefield.Field;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args)
    {
        World w = new World();
        w.setVisible(true);
        while(true)
        {
            if(Field.gameMode) {
                if (Field.gameStart) {
                    w.GameStart();
                    while(true) {
                        if (!w.gameover()) {
                            w.paint();
                        } else {
                            w.paint();
                            JOptionPane.showMessageDialog(null, "游戏结束,记得将目录中的record.txt保存或改名，否则再次战斗会替换掉上次记录");
                            Field.gameStart = false;
                            break;
                        }

                        try {
                            TimeUnit.MILLISECONDS.sleep(300);
                        } catch (InterruptedException e) {
                            ;
                        }
                    }
                    break;
                }
            }
            else
            {
                while(Field.gameStart) {
                    if (!Field.PaintOver) {
                        w.paint();
                        try {
                            TimeUnit.MILLISECONDS.sleep(300);
                        } catch (InterruptedException e) {
                            ;
                        }
                    }
                    else {
                        w.paint();
                        JOptionPane.showMessageDialog(null, "游戏结束");
                        break;
                    }

                }
                if(Field.PaintOver == true)
                {
                    break;
                }
            }

            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                ;
            }
        }
        System.exit(1);
    }
}
