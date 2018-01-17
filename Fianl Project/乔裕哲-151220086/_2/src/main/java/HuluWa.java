import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

class HuluWa extends Creature{

    private HuluColor color;
    private Name name;

    public HuluWa(HuluColor color, Name name, int x, int y, Field field) {
        super(x,y,field);
        this.type = CreatureType.娃;
        this.color = color;
        this.name = name;
        LoadImg();
        capacity = 50;
        justiceCamp = true;
        try {
            if(out != null) out.write(number + " "+ type.ordinal() + " "+ x + " "+ y + " " + color.ordinal() + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public HuluWa(HuluColor color, Name name, int x, int y, Field field, int num) {
        super(x,y,field, num);
        this.type = CreatureType.娃;
        this.color = color;
        this.name = name;
        LoadImg();
        capacity = 50;
        justiceCamp = true;
        try {
            if(out != null) out.write(number + " "+ type.ordinal() + " "+ x + " "+ y + " " + color.ordinal() + "\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void LoadImg(){
        URL loc = this.getClass().getClassLoader().getResource(this.color.toString() + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public HuluColor getColor() {
        return color;
    }

    public Name getName() {
        return name;
    }
}

enum HuluColor{红, 橙, 黄, 绿, 青, 蓝, 紫};

enum Name{大, 二, 三, 四, 五, 六, 七};
