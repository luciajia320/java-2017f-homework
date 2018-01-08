package Battle;

import Position.Position;
import UI.Field;
import creature.Creature;
import Formation.Formation;

//势力队列类
public class Queue {

    private Field field;

    //该势力下的生物及其位置
    private Position[] positions;
    private Creature[] creatures;

    public Position[] getPositions() {
        return positions;
    }

    public Creature[] getCreatures() {
        return creatures;
    }

    public int getSize(){return creatures.length;}

    //向势力添加生物
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

    //形成势力
    public Queue(Creature[] c,Field field) {
        this.field=field;

        this.positions = new Position[c.length];
        this.creatures = c;

        for (int i = 0; i < c.length; i++) {

            this.positions[i] =c[i].getPosition();
            creatures[i]=c[i];
        }
    }

    //判断某生物是否在此势力中，若在返回其索引
    public int isThere(Creature creature)
    {
        for(int i=0;i<creatures.length;i++)
            if(creatures[i]==creature)
                return i;
        return -1;
    }

    //势力排成阵型
    public void setFormation(Formation formation, int  OffsetX, int OffsetY) {
        int creatureCounter = 0;
        for (int i = 0; i < formation.getRow(); i++)
            for (int j = 0; j < formation.getCol(); j++)
                if (formation.getFormation()[i][j] == true) {
                    if(creatureCounter>=creatures.length)
                        break;
                    creatures[creatureCounter].moveto(OffsetX + i,OffsetY + j);
                    positions[creatureCounter]=field.getScenery()[OffsetX + i][OffsetY + j];
                    creatureCounter++;
                }
    }

}
