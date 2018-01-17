package tangwy.nju.edu.cn.huluwasvsdemons;

import tangwy.nju.edu.cn.huluwasvsdemons.things.creature.Creature;

public class  Formation {
    private Creature[] creatures;

    public Formation(Creature[] creatures) {
        this.creatures = creatures;
    }

    public Creature[] getCreatures() {
        return creatures;
    }

    public  Creature[] changShe(){
        for(int i=0;i<creatures.length;i++){
            creatures[i].setPosition(i,0);
        }
        return creatures;
    }

    public  Creature[] yanXing(){
        for(int i=0;i<creatures.length;i++){
            creatures[i].setPosition(i,i);
        }
        return creatures;
    }

    public  Creature[] heYi(){
        int i;
        for(i=0;i<creatures.length/2;i++){
            creatures[i].setPosition(i,i);
        }
        for(;i<creatures.length;i++){
            creatures[i].setPosition(i,creatures.length-i-1);
        }
        return creatures;
    }

    public  Creature[] chongE(){
        for(int i=0;i<creatures.length;i++){
            creatures[i].setPosition(i,i%2);
        }
        return creatures;
    }

    public void stepIn(int rowOffset,int lineOffset,int maxRow,int maxLine){
        for(Creature creature:creatures){

        }
    }
}
