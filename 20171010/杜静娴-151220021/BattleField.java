import sun.misc.Queue;

//import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BattleField {
    int X;
    int Y;

    private String content[][] = new String[X][Y];
    private final String placeholder = "🌵";

    private List<Formation> forms = new ArrayList();
    private List<Creature> men = new ArrayList();

    public BattleField(int x, int y) {
        X = x;
        Y = y;
        content = new String[X][Y];
        for(int i = 0; i < X; i++){
            for(int j = 0; j < Y;j++){
                content[i][j] = placeholder;
            }
        }
    }

    public void addFormation(Formation form){
        forms.add(form);
        int px = form.getPosX();
        int py = form.getPosY();
        int x = form.getX();
        int y = form.getY();
        for(int i = px; i < px+x; i ++){
            for(int j = py; j < py+y; j++){
                String a = form.tostring()[i-px][j-py];
                content[i][j] = a;
            }
        }
    }

    public void addItem(Creature man){
        men.add(man);
        int x = man.getX();
        int y = man.getY();
        String a = man.toString();
        content[x][y] = a;
    }

    public void deleteForm(String name){
        List<Formation> temp1 = new ArrayList(forms);
        List<Creature> temp2 = new ArrayList(men);
        forms.clear();
        men.clear();
        for(int i = 0; i < X; i++){
            for(int j = 0; j < Y;j++){
                content[i][j] = placeholder;
            }
        }
        for(Formation i : temp1){
            if(i.getName() != name)
                this.addFormation(i);
        }
        for(Creature i : temp2){
            this.addItem(i);
        }
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i <X; i ++){
            for(int j = 0; j < Y; j ++){
                output+=content[i][j];
                output+=" ";
            }
            output+="\n";
        }
        return output;
    }
}
