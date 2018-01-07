import java.net.URL;

public class grandFather extends Player implements creature, Runnable {
    private int rank;
    String name;
    private Position position;
    Field field;

    grandFather(int x, int y, Field field) {
        super(x,y, field);
        rank = 11;
        camp = 0;
        name = "👴";
        this.field = field;
        URL url = getClass().getResource("grandFather.png");
        setPlayerImage(url);
    }

    @Override
    public void run() {
        System.out.println("葫芦娃加油V(^_^)V！");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getRank() {
        return this.rank;
    }
}
