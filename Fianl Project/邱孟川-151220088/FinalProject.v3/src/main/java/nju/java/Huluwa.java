package main.java.nju.java;

enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}

public class Huluwa extends Creature {
    private static final int FIGHT_DISTANCE = 1;

    private COLOR color;
    private SENIORITY seniority;
    public Huluwa(Field field, Position position, COLOR color, SENIORITY seiority){
        super(field, position);
        this.color = color;
        this.seniority = seiority;
        this.setSpeed(2);
        this.setStrength(4);
        this.setLegion(1);
        int ordinal = this.color.ordinal() + 1 ;
        String imagePath = "resources/huluwa" + ordinal + ".png";
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
