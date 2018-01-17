package tangwy.nju.edu.cn.huluwasvsdemons.things.creature;

public class Scorpion extends Creature {

    public Scorpion(){
        super("scorpion.png");
        maxHealth=120;
        health=maxHealth;
        attackRange=50.0;
        attackDamage=30;
    }
}
