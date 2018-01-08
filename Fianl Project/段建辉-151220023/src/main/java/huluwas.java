import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

enum Color {COLORFUL, RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, PURPLE};

class Xiaojingang extends Player implements creature, Runnable {
    int rank;
    Color color;
    String name;
    Field field;

    Xiaojingang(int x, int y, Field field) {
        super(x,y,field);
        rank = 0;
        color = Color.COLORFUL;
        name = "🐉";
        speed = 4;
        camp = 0;
        attackDistance = 60;

        this.field = field;
        URL url = getClass().getResource("player.png");
        setPlayerImage(url);
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {

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
                Thread.yield();
            }
        }
        // System.out.println("我可以打败蛇精！");
    }


    Color getColor() {
        return color;
    }
}

class Dawa<T> extends Xiaojingang {

    Random rand = new Random();
    Dawa(int x, int y, Field field) {
        super(x,y,field);
        rank = 1;
        camp = 0;
        color = Color.RED;
        name = "❤️";
        this.field = field;
        attackDistance = 90;
        URL url = getClass().getResource("Dawa.png");
        setPlayerImage(url);
    }
}

class Erwa extends Xiaojingang {
    Erwa(int x, int y, Field field) {
        super(x,y,field);
        rank = 2;
        camp = 0;
        color = Color.ORANGE;
        name = "🧡";
        URL url = getClass().getResource("Erwa.png");
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

                        nearestCanAttack.setDeath();

                        int moveX = rand.nextInt(field.getBoardWidth()), moveY = rand.nextInt(field.getBoardHeight());
                        this.move(moveX - x(), moveY - y());

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
                Thread.yield();
            }
        }
    }
}

class Sanwa extends Xiaojingang {

    boolean miss = false;
    Sanwa(int x, int y, Field field) {
        super(x,y,field);
        rank = 3;
        camp = 0;
        color = Color.YELLOW;
        name = "💛";
        URL url = getClass().getResource("Sanwa.png");
        setPlayerImage(url);
    }
}

class Siwa extends Xiaojingang {
    Siwa(int x, int y, Field field) {
        super(x,y,field);
        rank = 4;
        camp = 0;

        color = Color.GREEN;
        name = "💚";
        URL url = getClass().getResource("Siwa.png");
        setPlayerImage(url);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {

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

                        nearestCanAttack.setDeath();

                    } else {

                        directionX = attackX - this.x() > 0 ? 1 : -1;

                        directionY = attackY - this.y() > 0 ? 1 : -1;

                        if(Math.abs(attackY - this.y()) > 3 * OFFSET) {
                            this.move(speed * OFFSET, 0);
                        } else {
                            this.move(directionX * speed * OFFSET, directionY * speed * OFFSET);
                        }
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
                Thread.yield();
            }
        }
    }
}

class Wuwa extends Xiaojingang {
    Wuwa(int x, int y, Field field) {
        super(x,y,field);
        rank = 5;
        camp = 0;

        color = Color.CYAN;
        name = "🖤";
        URL url = getClass().getResource("Wuwa.png");
        setPlayerImage(url);
    }

    /*@Override
    public void run() {
        System.out.println("我有洪击、蓄水、闪电的技能！(●′ω`●)");
    }*/
}

class Liuwa extends Xiaojingang {
    Liuwa(int x, int y, Field field) {
        super(x,y,field);
        rank = 6;
        camp = 0;

        color = Color.BLUE;
        name = "💙";
        URL url = getClass().getResource("Liuwa.png");
        setPlayerImage(url);
    }

    /*@Override
    public void run() {
        System.out.println("我有隐身的技能！(*・・*)");
    }*/
}

class Qiwa extends Xiaojingang {
    Qiwa(int x, int y, Field field) {
        super(x,y,field);
        rank = 7;
        camp = 0;

        color = Color.PURPLE;
        name = "💜";
        URL url = getClass().getResource("Qiwa.png");
        setPlayerImage(url);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {

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

                            nearestCanAttack.setDeath();

                    } else {

                        Random rand = new Random();
                        float hitShenHuLu = rand.nextFloat();
                        float setLongDeath = rand.nextFloat();

                        if(hitShenHuLu > 0.99) {
                            if (setLongDeath > 0.99) {
                                for(Player p : field.getWorld()) {
                                    if(p.isAlive() && p.getCamp() != this.camp) {
                                        p.setDeath();
                                    }
                                }
                            }
                        }

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
                Thread.yield();
            }
        }
    }
}

