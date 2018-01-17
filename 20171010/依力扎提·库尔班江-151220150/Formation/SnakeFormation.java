package Formation;

public class SnakeFormation extends Formation {

    public SnakeFormation(int r,int c){
        super(r,c);
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                formation[i][j]=true;

        LeaderPosY=row/2;
        LeaderPosX=0;
    }
}
