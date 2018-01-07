
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    private Field field;
    protected final int sizeX = 160, sizeY = 160;
    protected boolean alive;
    protected int speed;
    protected int camp;
    protected int attackDistance;
    final int OFFSET = 5;

    public Player(int x, int y, Field field) {
        super(x, y);
        this.field = field;
        alive = true;
        camp = -1;
        //URL url = getClass().getResource("resources/player.png");
        //setPlayerImage(url);
    }

    public void setPlayerImage(URL url) {
        ImageIcon iia = new ImageIcon(url);
        Image img = iia.getImage();
        img = img.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
        iia.setImage(img);
        this.setImage(img);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    public int getCamp() { return this.camp; }

    public boolean isAlive() { return this.alive; }

    public void setDeath() {

        this.alive = false;
        field.getWorld().remove(this);
    }

    public Player getNearestCanAttackPlayers(int nX, int nY, int _camp) {

        Player players = null;
        int min = field.getBoardWidth() * field.getBoardHeight();

        ArrayList<Player> worldCopy = new ArrayList<>(field.world);

        for(Player p : worldCopy) {

            if(_camp != p.getCamp() && p.isAlive()) {

                int attackX = p.x(), attackY = p.y();

                int dist = (int)(Math.sqrt((double)((attackX - nX) * (attackX - nX) + (attackY - nY) * (attackY - nY))));

                if(min > dist) {
                    min = dist;
                    players = p;
                }
            }
        }

        return players;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            this.move(rand.nextInt(40), rand.nextInt(40));
            try {

                Thread.sleep(100);
                this.field.repaint();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}