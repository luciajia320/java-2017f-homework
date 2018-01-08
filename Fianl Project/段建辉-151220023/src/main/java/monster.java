import java.net.URL;
import java.util.Random;

class monster extends Player implements creature, Runnable {
    private int rank;
    private String name;
    Field field;
    int reliveCount;
    monster(int x, int y, Field field){
        super(x,y, field);
        rank = 10;
        name = "MONSTER";
        this.field = field;
        speed = 2;
        camp = 1;
        attackDistance = 50;
        reliveCount = 0;
        URL url = getClass().getResource("player.png");
        setPlayerImage(url);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {

            Random rand = new Random();
            int directionX = 0, directionY = 0;

            if(isAlive()) {
                Player nearestCanAttack = getNearestCanAttackPlayers(this.x(), this.y(), this.camp);
                if(nearestCanAttack == null) {

                    field.setCompleted();
                    this.move(0, 0);
                }
                else {

                    int attackX = nearestCanAttack.x(), attackY = nearestCanAttack.y();

                    if((int)(Math.sqrt((double)((attackX - this.x()) * (attackX - this.x()) + (attackY - this.y()) * (attackY - this.y())))) <= this.attackDistance) {

                        double probability = rand.nextDouble();
                        if(probability > 0.2)
                            nearestCanAttack.setDeath();

                    } else {

                        directionX = attackX - this.x() > 0 ? 1 : -1;

                        directionY = attackY - this.y() > 0 ? 1 : -1;

                        this.move(directionX * speed * OFFSET, directionY * speed * OFFSET);
                    }
                }

                try {

                    Thread.sleep(100);
                    this.field.repaint();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(reliveCount < 3) {
                    int reliveX = rand.nextInt(field.getBoardWidth()), reliveY = rand.nextInt(field.getBoardHeight());
                    this.setX(reliveX);
                    this.setY(reliveY);
                    alive = true;
                    reliveCount++;
                }
                else {
                    Thread.yield();
                }

            }

        }
        System.out.println("受死吧！");
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

class Scorpion extends monster {
    private int rank;
    private String name;
    Scorpion(int x, int y, Field field){
        super(x,y, field);
        rank = 8;
        camp = 1;

        name = "🦂";
        this.field = field;
        attackDistance = 80;

        URL url = getClass().getResource("Scorpion.png");
        setPlayerImage(url);
    }
    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

class Snake extends monster {
    private int rank;
    private String name;
    Snake(int x, int y, Field field){
        super(x,y, field);
        rank = 9;
        camp = 1;

        name = "🐍";
        this.field = field;
        attackDistance = 80;

        URL url = getClass().getResource("Snake.png");
        setPlayerImage(url);
    }
    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

class Soldier extends monster {
    private int rank;
    private String name;
    Soldier(int x, int y, Field field){
        super(x,y, field);
        rank = 10;
        camp = 1;

        name = "🕷️🐷";
        this.field = field;
        URL url = getClass().getResource("soldier.png");
        setPlayerImage(url);
    }
    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public String getName() {
        return this.name;
    }

}