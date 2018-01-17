import java.io.IOException;

public class Shejing extends Creature{
    Shejing(int x, int y, Field field) {
        super(x,y,field);
        type = CreatureType.蛇;
        LoadImg();
        capacity = 80;
        justiceCamp = false;
        try {
            if(out != null) out.write(number + " "+ type.ordinal() + " "+ x + " "+ y + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    Shejing(int x, int y, Field field, int num) {
        super(x,y,field, num);
        type = CreatureType.蛇;
        LoadImg();
        capacity = 80;
        justiceCamp = false;
        try {
            if(out != null) out.write(number + " "+ type.ordinal() + " "+ x + " "+ y + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
