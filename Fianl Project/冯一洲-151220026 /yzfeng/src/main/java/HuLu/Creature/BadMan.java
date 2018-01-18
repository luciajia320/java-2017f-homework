package HuLu.Creature;

import HuLu.Field.Field;
import HuLu.Field.GameState;
import HuLu.Formation.Holder;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public  class BadMan extends Creature {
    protected int turn = -1;
    public BadMan(int id , Field field, CyclicBarrier cyclic) {
        super(id, field, cyclic);
    }
    
    @Override
    public int meetEnemy(){
        int attackRange = 2;
        Holder holders[][] = field.getHolders();
        int x = this.x;
        int y = this.y;

        if(x + attackRange < field.getBoardWidth() && holders[x + attackRange][y].getItem() instanceof GoodMan)
            return 0;

        if(x - attackRange >=0 && holders[x - attackRange][y].getItem() instanceof GoodMan)
            return 1;

        return -1;
    }

    public void run(){
        while (!Thread.interrupted() && field.getGameState() != GameState.END) {
            try {
                if (alive){
                    int rand = new Random().nextInt();
                    if(this.x == 0 || this.x == field.getBoardWidth()-1)
                    {
                        turn = - turn;
                        move(turn, 0);
                    }

                    else if (rand % 2 == 0) {
                        if(validMove(turn, 0))
                            move(turn, 0);
                    }

                }
                cyclic.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                // e.printStackTrace();
                e.getCause();
            }
        }
    }
    public synchronized void attackAt(Creature c){
        int p= new Random().nextInt(100);
        if(p >= 60)
            c.killed();
        else
            killed();
    }
}
