package Formation;

public class Formation {
    protected  boolean [][]formation;
    protected  int col;
    protected  int row;
    protected  int LeaderPosX;
    protected  int LeaderPosY;

    public boolean[][] getFormation() {
        return formation;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Formation(int r,int c){
        col=c;
        row=r;
        formation=new boolean[row][col];
    }

}
