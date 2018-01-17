package Queue;

import Creatures.Creature;
import Position.Position;
import Formation.Formation;
import Position.Ground;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Queue {

    private Position[] positions;
    private Creature[] creatures;

    public Position[] getPositions() {
        return positions;
    }

    public Creature[] getCreatures() {
        return creatures;
    }


    public  void append(Creature c)
    {
        Position []pTemp=new Position[positions.length+1];
        Creature[]cTemp=new Creature[creatures.length+1];
        for(int i=0;i<pTemp.length;i++) {
            if(i>0)
            {
                pTemp[i]=positions[i-1];
                cTemp[i]=creatures[i-1];
            }
            else {
                pTemp[i] = c.getPosition();
                cTemp[i]=c;
            }
        }
        positions=pTemp;
        creatures=cTemp;
    }

    public Queue(Creature[] c) {


        this.positions = new Position[c.length];
        this.creatures = c;

        for (int i = 0; i < c.length; i++) {

            this.positions[i] =c[i].getPosition();
            creatures[i]=c[i];
        }
    }


    public void rollCall() {
 /*       for (Creatures.CREATURES.Creatures.Creature creature : this.creatures) {
            creature.speak();
        }
        System.out.println();
        System.out.flush();*/

        for (Position position : this.positions) {

            position.getSomeone().speak();
        }

        System.out.println("\n");
        System.out.flush();
    }

    public void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = creatures.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Position position = creatures[index].getPosition();
            creatures[index].setPosition(creatures[i].getPosition());
            creatures[i].setPosition(position);
        }
    }

    public void setFormation(Formation formation, int  OffsetX, int OffsetY,Ground scenery ) {
        int creatureCounter = 0;
        for (int i = 0; i < formation.getRow(); i++)
            for (int j = 0; j < formation.getCol(); j++)
                if (formation.getFormation()[i][j] == true) {
                    if(creatureCounter>=creatures.length)
                        break;
                    creatures[creatureCounter].setPosition(scenery.getScenery()[OffsetX + i][OffsetY + j]);
                    this.positions[creatureCounter]=scenery.getScenery()[OffsetX + i][OffsetY + j];
                    creatureCounter++;
                }
    }

}
