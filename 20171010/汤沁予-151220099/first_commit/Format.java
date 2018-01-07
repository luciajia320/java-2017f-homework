public class Format
{
    private Position[][] ground;
    private int height;
    private int width;
    private Position pos;//阵型左上角的位置

    public Format(int row,int col,int x0,int y0)
    {
        width = col;
        height = row;
        InitGround(height,width);
        pos = new Position(x0,y0);
    }

    private void InitGround(int x,int y)
    {
        ground = new Position[x][y];
        for(int i = 0;i<x;i++)
            for(int j = 0;j<y;j++)
                ground[i][j] = new Position(i,j);
    }

    public void PutACreature(Creature c,Position pos)
    {
        pos.setHolder(c);
        c.setposition(pos);
    }

    public int getHeight()
    {
        return this.height;
    }
    public int getWidth()
    {
        return this.width;
    }
    public Position[][] getGround()
    {
        return this.ground;
    }
    public Position getPos()
    {
        return pos;
    }
    public void PutCreatures(){;}
}
