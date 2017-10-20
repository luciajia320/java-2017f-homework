import Characters.Creature;
import Layout.Troop;
import Position.Position;

public class Field {
    private int fieldSize, troopNum;
    private Position[][] positions;
    private Creature[] creatures;
    private Troop[] troops;

    public Field(int fieldSize, int troopNum){
        this.fieldSize = fieldSize;
        this.troopNum = troopNum;

        positions = new Position[fieldSize][fieldSize];
        troops = new Troop[troopNum];
    }

    public void arrange(){  // 根据每个troop的信息放置creature

    }

    public void show(){ //  依次显示每个position
        for(int i = 0; i < fieldSize; i++){
            for(int j = 0; j < fieldSize; j++){
                if(positions[i][j].getHolder() == null){
                    System.out.print("___");
                }else{
                    System.out.print(positions[i][j].getHolder().toString());
                }
            }
        }
    }

    public static void main(String[] args) {
        //初始化场地，10*10方阵，有2方势力
        Field field = new Field(10, 2);
        field.arrange();
        field.show();
    }
}
