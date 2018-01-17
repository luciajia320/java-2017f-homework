import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Replay {
    private FileReader fileReader;
    private BufferedReader in;
    Field field;
    ArrayList <Creature> creatures;

    public Replay(Field field) {
        open();

        this.field = field;
        this.creatures = field.getCreatures();
        LoadCreatures();

    }

/*    public boolean success(){
        if()
    }*/

    public boolean next(){
        String data = null;
        try {
            data = in.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        if(data == null) return false;
        String Count[] = data.split("\\s+");
        int creatureNum = Integer.parseInt(Count[0]);
        int action = Integer.parseInt(Count[1]);
        Creature creature = findCreature(creatureNum);

        if(action == 1){
            int x = Integer.parseInt(Count[2]);
            int y = Integer.parseInt(Count[3]);
            creature.move(x, y);
        }else if(action == 2){
            creature.dead();
        }

        return true;
    }

    private void LoadCreatures(){
        creatures.clear();
        for(int i = 0; i < 16; ++i) {

            try {
                String data;
                data = in.readLine();
                if(data == null)
                    System.out.println("data == null");
                String Count[] = data.split("\\s");
                int creatureNum = Integer.parseInt(Count[0]);
                int creatureType = Integer.parseInt(Count[1]);
                int x = Integer.parseInt(Count[2]);
                int y = Integer.parseInt(Count[3]);
                int huluOridinary;
                switch (creatureType){
                    case 0:
                        //creatures.add(new Yeye(x, y, field));
                        creatures.add(new Yeye(x, y, field, creatureNum));
                        break;
                    case 1:
                        huluOridinary = Integer.parseInt(Count[4]);
                        creatures.add(new HuluWa(HuluColor.values()[huluOridinary], Name.values()[huluOridinary], x, y, field, creatureNum));
                        break;
                    case 2:
                        creatures.add(new Xiezi(x, y, field, creatureNum));
                        break;
                    case 3:
                        creatures.add(new Shejing(x, y, field, creatureNum));
                        break;
                    case 4:
                        creatures.add(new Louluo(x, y, field, creatureNum));
                        break;
                    default:
                        break;
                }

            } catch (IOException e){
                System.out.println("i = " + i);
                e.printStackTrace();
            }
        }
    }

    public void close(){
        try{
            in.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private Creature findCreature(int i){
        for(Creature c : creatures)
            if(c.getNumber() == i) return c;
        return null;
    }

    public void open(){
        FileDialog dialog;
        String filename = null;
        do {
            dialog = new FileDialog(new Frame(), "选择存放位置", FileDialog.LOAD);
            dialog.setVisible(true);
            filename = dialog.getFile();

        }while(filename == null);

        try {

            fileReader = new FileReader(filename);
            in = new BufferedReader(fileReader);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
