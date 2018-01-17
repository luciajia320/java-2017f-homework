package HL;
import HL.creature.Creature;


public class Field {
    private int N;
    private Position positions[][];

    public Field(int N) {
        this.N = N;

        positions = new Position[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                positions[i][j] = new Position(i, j);
                positions[i][j].setHolder(null);
            }
        }
    }

    public void setCreature(Creature creature, Position p)
    {
        positions[p.getCoorinate().getX()][p.getCoorinate().getY()].setHolder(creature);
    }

    public void setCreature(Creature creature, int x, int y)
    {
        positions[x][y].setHolder(creature);
    }

    public Creature getCreature(int x, int y)
    {
        return positions[x][y].getHolder();
    }

    public void display()
    {
        for(int i = 0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if (positions[i][j].getHolder() != null)
                    positions[i][j].getHolder().report();
                else
                    System.out.print("🌲");
            }
            System.out.println();
        }
    }

    public void setNull()
    {
        for(int i = 0; i<N; i++)
            for(int j = 0; j<N; j++)
            {
                positions[i][j].setHolder(null);
            }
    }

    public int getLength()
    {
        return N;
    }


}
