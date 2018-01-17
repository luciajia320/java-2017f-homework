package Formation;

public class ArrowFormation extends Formation{

    public ArrowFormation(int r,int c){
        super(r,c);
        for(int i=0;i<row;i++)
            for(int j=0;j< col;j++){
                if(j==3||i+j==3||j-i==3)
                    formation[i][j]=true;
                else
                    formation[i][j]=false;
        }
        LeaderPosX=col/2;
        LeaderPosY=row-1;
    }
}
