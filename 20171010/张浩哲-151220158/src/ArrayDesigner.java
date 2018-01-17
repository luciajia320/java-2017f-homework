public class ArrayDesigner {
    private Map[] maps;
    ArrayDesigner(){
        maps = new Map[8];
        for (int i = 0;i<8;i++)
            maps[i] = new Map();
        maps[0].name = "鹤翼";
        for (int i = 0; i <  4; ++i) {
            maps[0].battleMap[i][i] = 1;
            maps[0].battleMap[i][ 6 - i] = 1;
        }
        maps[1].name = "衡轭";
        for (int i = 0; i <  6; ++i) {
            maps[1].battleMap[i][(i+1)%2] = 1;
        }
        maps[2].name = "长蛇";
        for (int i = 0; i <  6; ++i) {
            maps[2].battleMap[i][0] = 1;
        }
    }
    public void arrayDesignHeyi(Creature[] creatures,int num,Coordinate temp1,Coordinate temp2){
        int i = 9;
        for (int j = 0;j<12;j++)
            for(int k = 0;k<12;k++){
                if(maps[0].battleMap[j][k] == 1){
                    creatures[i].setPosition(new Position((new Coordinate(j+3,k+3))));
                    i++;
                }
            }
        creatures[7].setPosition(new Position(temp1));
        creatures[8].setPosition(new Position(temp2));
    }
    public void arrayDesignHenge(Creature[] creatures,int num,Coordinate temp1,Coordinate temp2){
        int i = 9;
        for (int j = 0;j<12;j++)
            for(int k = 0;k<12;k++){
                if(maps[1].battleMap[j][k] == 1){
                    creatures[i++].setPosition(new Position((new Coordinate(j+3,k+3))));
                }
            }
        creatures[7].setPosition(new Position(temp1));
        creatures[8].setPosition(new Position(temp2));
    }
}
class Map{
    public int battleMap[][];
    public String name;
    public
    Map(){
        battleMap = new int[12][];
        for(int i = 0;i<12;i++)
            battleMap[i] = new int [12];
    }
}