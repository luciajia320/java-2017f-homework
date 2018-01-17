package mainroles;

import battlefield.Position;

import java.awt.*;

public interface Creature extends Runnable{
    Position getPosition();
    void setPosition(Position p);
    boolean getLive();
    void beKilled();
    Image getImage();
    Image getDeathImage();
    void setGo();
    boolean getJustice();
    void setBattle(boolean battle);
    int getFileId();
    void setFileId(int id);
}
enum direction{up,down,left,right}