package tangwy.nju.edu.cn.huluwasvsdemons.things.creature;

public class Grandpa extends Creature{

    public Grandpa(){
        super("grandpa.png");
    }
    @Override
    protected void act(){

    }

    @Override
    //Grandpa do noy need to move
    protected void move() {
        ;
    }

    @Override
    protected void attack(){

    }

}
