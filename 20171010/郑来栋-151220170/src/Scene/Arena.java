package Scene;

import roles.Creature;

public class Arena {
    private Position<Creature>[][] positions = null;

    public Arena(int N)
    {
        positions = new Position[N][N];
        for (int i =0 ;i< N;i++)
            for (int j=0;j<N;j++)
                positions[i][j] = new Position<>();
    }

    public boolean setPosition(int i,int j,Creature creature)
    {
        if (positions[i][j].getHolder() != null)
            return false;
        positions[i][j].setHolder(creature);
        return true;
    }

    public void clearPosition(int i,int j)
    {
        positions[i][j].setHolder(null);
    }

    @Override public String toString() {
        String ret = "";
        for (int i = 0; i < positions.length;i++)
        {
            for (int j =0 ;j< positions.length;j++)
                ret = ret + positions[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }
}
