import lyc.hw.Location;
import lyc.hw.SnakeFormation;
import lyc.hw.Ground;


public class Main {
    public static void main(String[] args) {
        Ground gnd = new Ground(10,10);
        SnakeFormation snake1 = new SnakeFormation("\uD83D\uDC38");
        SnakeFormation snake2 = new SnakeFormation("\uD83D\uDC38");
        gnd.layout(snake1,new Location(1,0));
        gnd.layout(snake2,new Location(1,9));

        System.out.println(gnd);
    }
}
