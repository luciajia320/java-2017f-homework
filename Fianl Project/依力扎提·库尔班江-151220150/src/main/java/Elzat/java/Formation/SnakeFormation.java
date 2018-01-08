package Formation;

public class SnakeFormation extends Formation {

    public SnakeFormation(){
        super(60,1);
        for(int i=0,j=0;i<row;i+=8,j++) {
            formation[i][0] = true;
            if(j==3) {
                LeaderPosY = i;
                LeaderPosX = 0;
            }
        }

    }
}