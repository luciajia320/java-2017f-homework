package main.java.nju.java;

public class Xiezijing extends Creature {
    private static final int FIGHT_DISTANCE = 1;

    Xiezijing(Field field, Position position){
        super(field, position);
        this.setSpeed(3);
        this.setStrength(5);
        this.setLegion(2);
        String imagePath = "resources/xiezijing.png";
        this.setImage(imagePath);
    }

    @Override
    public boolean hasEnemy(){
        for(int i = 0; i < this.getField().getHuluwas().length; ++i)
            if(this.getField().getHuluwas()[i].isAlive()) return true;
        return false;
    }

    @Override
    public void fightEnemy(){
        Creature[] huluwas = this.getField().getHuluwas();
        for(int i = 0; i < huluwas.length; ++i){
            if(huluwas[i].isAlive() == false) continue;
            else if(Math.abs(huluwas[i].getPosition().getX()-this.getPosition().getX()) <= FIGHT_DISTANCE
                    && Math.abs(huluwas[i].getPosition().getY()-this.getPosition().getY()) <= FIGHT_DISTANCE){
                boolean result = fight(huluwas[i]);
                if(result){ huluwas[i].dead(); huluwas[i].setImage("resources/dead1.png"); huluwas[i].getPosition().clear(); }
                else { this.dead(); this.setImage("resources/dead2.png"); this.getPosition().clear(); break;}
            }
        }
    }

    @Override
    public int currentAliveNumber(){
        int count = 0;
        for(int i = 0; i < this.getField().getYaoguais().length; ++i)
            if(this.getField().getYaoguais()[i].isAlive()) ++count;
        return count;
    }

    @Override
    public int findAliveEnemy(){
        for(int i = 0; i < this.getField().getHuluwas().length; ++i)
            if(this.getField().getHuluwas()[i].isAlive()) return i;
        return -1;
    }
}
