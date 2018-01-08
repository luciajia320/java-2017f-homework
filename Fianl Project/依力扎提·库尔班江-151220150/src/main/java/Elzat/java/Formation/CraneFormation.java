package Formation;

public class CraneFormation extends Formation {
    public CraneFormation(){
        super(60,50);
        formation[0][0]=true;
        formation[8][9]=true;
        formation[16][18]=true;
        formation[24] [25]=true;
        formation[32][32]=true;
        formation[40][18]=true;
        formation[48][9]=true;
        formation[56][0]=true;
        LeaderPosX=16;
        LeaderPosY=16;
    }

}