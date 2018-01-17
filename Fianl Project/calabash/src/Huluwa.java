public class Huluwa extends Creature implements Runnable{
    private int id ;
    public Huluwa(BattleField fHandler, String picPath, int x, int y, int id) {
        super(fHandler, picPath, x, y);
        this.id = id;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try{

            }catch(Exception e){

            }
        }
    }
}
