package nju.java;


public class BadMan extends Creature {
    public BadMan(int x, int y, Field field, String url) {
        super(x, y, field, url);
    }

    void oneStep() throws Exception {
        Direction d = this.field.findNearEnemgy(this);

        if(d == null){
            return;
        }

        oneStep(d);

        for(Creature creature : this.field.getCreatureList()){
            if(creature instanceof GoodMan){
                if(!creature.isDead() && LittleUtils.distanceTooClose(creature, this)){
                    this.fight(creature);
                    break;
                }
            }
        }

    }


}
