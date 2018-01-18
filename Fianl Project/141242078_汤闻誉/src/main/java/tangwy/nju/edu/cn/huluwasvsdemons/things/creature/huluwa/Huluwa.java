package tangwy.nju.edu.cn.huluwasvsdemons.things.creature.huluwa;

import tangwy.nju.edu.cn.huluwasvsdemons.things.creature.Creature;

public class Huluwa extends Creature {
    public Huluwa(){
        super("red.png");
        maxHealth=100;
        health=maxHealth;
        attackRange=60.0;
        attackDamage=30;

    }
    public Huluwa(String imageName){super(imageName);
        maxHealth=100;
        health=maxHealth;
        attackRange=60.0;
        attackDamage=30;
    }

}
