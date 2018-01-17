public class Enemy extends Creature implements Runnable {
    private int id;
    public Enemy(BattleField fHandler, String picPath, int x, int y ,int id) {
        super(fHandler, picPath, x, y);
        this.id = id;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try{
                if(!alive) break;

            }catch(Exception e){

            }
        }
    }
}
