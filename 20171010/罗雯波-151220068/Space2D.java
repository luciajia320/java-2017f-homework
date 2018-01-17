import java.util.ArrayList;

public class Space2D implements Space {
    private ArrayList<Queue> queueArrayList = new ArrayList<>();
    private int maxX, maxY;

    public Space2D(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    @Override
    public Space add(Queue queue) {
        queueArrayList.add(queue);
        return this;
    }

    @Override
    public void remove(Queue queue) {
        queueArrayList.remove(queue);
    }

    @Override
    public void display() {
        Creature[][] holders =  new Creature[maxX][maxY];
        for (Queue queue : queueArrayList) {
            Position[] positions = queue.getPositions();
            for (Position position : positions) {
                PosCoord posCoord = position.getPosCoord();
                int x = posCoord.getX(), y = posCoord.getY();
                if (x >= 0 && x < maxX && y >= 0 && y < maxY) {
                    holders[x][y] = position.getHolder();
                }
            }
        }
        System.out.println("---Space 2D---");
        for (int y = maxY - 1; y >= 0; y --) {
            for (int x = 0; x < maxX; x ++) {
                Creature creature = holders[x][y];
                if (creature == null) {
                    System.out.printf("%s|", "。。");
                }
                else {
                    System.out.printf("%2s|", creature.toString());
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

