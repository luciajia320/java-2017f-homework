import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private int N;
    private List formations;
    private List boosters;

    public Map(int n){
        this.N=n;
        formations=new ArrayList();
        boosters=new ArrayList();
    }

    public void addFormation(Formation formation){
        formations.add(formation);
    }

    public void addBooster(Creature creature){
        boosters.add(creature);
    }

    public void clear(){
        formations.clear();
        boosters.clear();
    }

    public void clearFormations(){
        formations.clear();
    }

    public String toString(){
        String[][] content=new String[this.N][this.N];

        for(int i=0;i<this.N;i++){
            for(int j=0;j<this.N;j++){
                content[i][j]="\uD83C\uDF3F";
            }
        }

        for(int n=0;n<formations.size();n++){
            Formation formation=(Formation)formations.get(n);
            Position position=formation.getPosition();
            for(int i=0;i<formation.getWidth();i++){
                for(int j=0;j<formation.getHeight();j++){
                    content[position.getX()+i][position.getY()+j]=formation.getContent()[i][j];
                }
            }
        }

        for(int n=0;n<boosters.size();n++){
            Creature creature=(Creature)boosters.get(n);
            Position position=creature.getPosition();
            content[position.getX()][position.getY()]=creature.toString();
        }

        StringBuilder stringbuilder=new StringBuilder();
        for(int j=0;j<this.N;j++){
            for(int i=0;i<this.N;i++){
                stringbuilder.append(content[i][j]+" ");
            }
            stringbuilder.append("\n");
        }
        return stringbuilder.toString();
    }
}
