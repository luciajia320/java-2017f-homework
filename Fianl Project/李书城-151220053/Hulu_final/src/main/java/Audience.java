import java.util.Random;
import java.io.*;

public class Audience extends Speices implements Runnable {

    public Audience(int x, int y, Fight fight, String pic) {
        super(x, y, fight, pic);
    }

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();
            try {
                String file = fight.conf;
                FileReader fr = new FileReader(file);
                BufferedReader bf = new BufferedReader(fr);
                String line = "";
                while ((line = bf.readLine()) != null) {
                    Thread.sleep(rand.nextInt(100));
                    fight.explainFile(line);
                    this.repaint();
                }
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}