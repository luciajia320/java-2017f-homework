import java.io.IOException;

public class Xiezi extends Creature {
    Xiezi (int x, int y, Field field) {
        super(x, y, field);
        type = CreatureType.蝎;
        LoadImg();
        capacity = 80;
        justiceCamp = false;

        try {
            if(out != null) out.write(number + " "+ type.ordinal() + " "+ x + " "+ y + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    Xiezi (int x, int y, Field field, int num) {
        super(x, y, field, num);
        type = CreatureType.蝎;
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
