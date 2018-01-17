package Formation;

public class CraneFormation extends Formation {
    public CraneFormation(int r,int c){
        super(r,c);
        for(int i=0;i<row;i++)
            for(int j=0;j< col;j++)
                if(i==j||(i+j)==col-1)
                    formation[i][j]=true;
                else
                    formation[i][j]=false;
        LeaderPosX=col/2;
        LeaderPosY=row-1;
    }

}
