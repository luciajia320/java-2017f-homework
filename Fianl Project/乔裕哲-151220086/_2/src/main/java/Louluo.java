import java.io.IOException;

public class Louluo extends Creature{
    Louluo(int x, int y, Field field){
        super(x,y,field);
        type = CreatureType.喽;
        LoadImg();
        capacity = 5;
        justiceCamp = false;
        try {
            if(out != null) out.write(number + " "+ type.ordinal() + " "+ x + " "+ y + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    Louluo(int x, int y, Field field, int num){
        super(x,y,field, num);
        type = CreatureType.喽;
        LoadImg();
        capacity = 5;
        justiceCamp = false;
        try {
            if(out != null) out.write(number + " "+ type.ordinal() + " "+ x + " "+ y + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
