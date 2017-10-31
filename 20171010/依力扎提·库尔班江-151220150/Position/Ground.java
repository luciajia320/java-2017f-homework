package Position;

import Position.Position;
import Creatures.CheeringCharacter;

public class Ground {
    Position[][] scenery;
    int maxX,maxY;

    public Ground(int x,int y) {
        scenery=new Position[x][y];
        maxX=x;
        maxY=y;
        for(int i=0;i<maxX;i++)
            for(int j=0;j<maxY;j++)
                scenery[i][j]=new Position(i,j);
    }

    public Position[][] getScenery() {
        return scenery;
    }

    public void output()
    {
        for(int i=0;i<maxX;i++) {
            for (int j = 0; j < maxY; j++) {
                if (scenery[i][j].getSomeone() == null)
                    System.out.print("  ");
                else {
                    if(scenery[i][j].getSomeone().getClass()!=CheeringCharacter.class)
                        scenery[i][j].getSomeone().speak();
                    else
                        ((CheeringCharacter)(scenery[i][j].getSomeone())).cheer();
                }
            }
            System.out.println();
        }
    }
}
