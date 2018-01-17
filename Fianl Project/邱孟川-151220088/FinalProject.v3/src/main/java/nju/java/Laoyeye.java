package main.java.nju.java;

public class Laoyeye extends Creature {
    private static final int FIGHT_DISTANCE = 1;

    public Laoyeye(Field field, Position position){
        super(field, position);
        this.setSpeed(1);
        this.setStrength(2);
        this.setLegion(1);
        String imagePath = "resources/laoyeye.png";
        this.setImage(imagePath);
    }

    @Override
    public boolean hasEnemy(){
        for(int i = 0; i < this.getField().getYaoguais().length; ++i)
            if(this.getField().getYaoguais()[i].isAlive()) return true;
        return false;
    }

    @Override
    public void fightEnemy(){
        Creature[] yaoguais = this.getField().getYaoguais();
        for(int i = 0; i < yaoguais.length; ++i){
            if(yaoguais[i].isAlive() == false) continue;
            else if(Math.abs(yaoguais[i].getPosition().getX()-this.getPosition().getX()) <= FIGHT_DISTANCE
                    && Math.abs(yaoguais[i].getPosition().getY()-this.getPosition().getY()) <= FIGHT_DISTANCE){
                boolean result = fight(yaoguais[i]);
                if(result){ yaoguais[i].dead(); yaoguais[i].setImage("resources/dead2.png"); yaoguais[i].getPosition().clear();}
                else { this.dead(); this.setImage("resources/dead1.png"); this.getPosition().clear(); break;}
            }
        }
    }

    @Override
    public int currentAliveNumber(){
        int count = 0;
        for(int i = 0; i < this.getField().getHuluwas().length; ++i)
            if(this.getField().getHuluwas()[i].isAlive()) ++count;
        return count;
    }

    @Override
    public int findAliveEnemy(){
        for(int i = 0; i < this.getField().getYaoguais().length; ++i)
            if(this.getField().getYaoguais()[i].isAlive()) return i;
        return -1;
    }
}
