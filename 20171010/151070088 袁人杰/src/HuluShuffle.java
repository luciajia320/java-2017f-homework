import java.util.Random;

public class HuluShuffle implements Shuffle {
    @Override
    public void arrRandom(Creature[] creatures) {
        System.out.println("打乱顺序");
        Random random = new Random();
        for (int i = creatures.length - 1; i > 0; i--) {
            int ranIndex = random.nextInt(i + 1);
            Grid nGrid = creatures[ranIndex].getGrid();
            creatures[ranIndex].setGrid(creatures[i].getGrid());
            creatures[i].setGrid(nGrid);
        }
    }

    public void arrRandom2(Queue queue) {
        System.out.println("打乱顺序");
        Random random = new Random();
        for (int i = queue.getLength() - 1; i > 0; i--) {
            int ranIndex = random.nextInt(i + 1);
            Grid nGrid = queue.getGrid(ranIndex);
            queue.getGrid(ranIndex).setHolder(queue.getGrid(i).getHolder());
            //creatures[ranIndex].setGrid(creatures[i].getGrid());
            queue.getGrid(i).setHolder(nGrid.getHolder());
        }
    }
}
