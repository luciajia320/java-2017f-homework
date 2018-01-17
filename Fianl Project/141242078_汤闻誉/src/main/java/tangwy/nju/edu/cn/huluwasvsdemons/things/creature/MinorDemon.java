package tangwy.nju.edu.cn.huluwasvsdemons.things.creature;

public class MinorDemon extends Creature{

    public MinorDemon(){
        super("minordemon.png");
        maxHealth=40;
        health=maxHealth;
        attackRange=50.0;
        attackDamage=10;
    }
}
