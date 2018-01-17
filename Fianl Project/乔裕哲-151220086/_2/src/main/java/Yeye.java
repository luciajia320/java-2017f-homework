import java.io.IOException;

public class Yeye extends Creature{
    Yeye(int x, int y, Field field){
        super(x, y, field);
        type = CreatureType.爷;
        LoadImg();
        capacity = 5;
        justiceCamp = true;
        try {
            if(out != null) out.write(number + " "+ type.ordinal() + " "+ x + " "+ y + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    Yeye(int x, int y, Field field, int creatuenum){
        super(x, y, field, creatuenum);
        type = CreatureType.爷;
        LoadImg();
        capacity = 5;
        justiceCamp = true;
        try {
            if(out != null) out.write(number + " "+ type.ordinal() + " "+ x + " "+ y + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
