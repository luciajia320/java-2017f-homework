import javax.swing.plaf.PanelUI;
import java.util.ArrayList;

public class Formation {
    private ArrayList<Position> positions;

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public Formation(int num){
        this.positions = new ArrayList<Position>(num);
        for(int i = 0 ; i < num ; i++){
                this.positions.add(i, new Position(0, 0));
        }
    }
    public Formation GoGoGo(){
        positions.get(0).setPositon(0,0);
        positions.get(1).setPositon(0,1);
        return this;
    }

    public Formation HeYi() {
        int num= positions.size();
        this.positions.get(0).setPositon( num / 2,0);
        for (int i = 1, j = 1; i < num; i ++) {
            switch(i%2){
                case 0:
                    positions.get(i).setPositon(num / 2 + j,j);j++;break;
                case 1:
                    positions.get(i).setPositon(num / 2 - j,j);break;
            }

        }
        return this;
    }

    public Formation YanXing() {
        int num = positions.size();
        for (int i = num - 1, j = 1; i >= 0; i--, j++) {
            positions.get(j - 1).setPositon(i, j);
        }
        return this;
    }

    public Formation ChongE() {
        int num = positions.size();
        for (int i = 0, j = 0; i < num; i ++, j++) {
            int z = j % 2 == 0 ? 1 : 0;
            switch(i%3) {
                case 0:
                    positions.get(i).setPositon(j, z);
                case 1:
                    positions.get(i).setPositon(j, z + 2);
                case 2:
                    positions.get(i).setPositon(j, z + 2);
            }
        }
        return  this;
    }

    public Formation YuLin(){
        int num = positions.size();
        double max = Math.sqrt(num*2);

        int z = 0;
        for(int i =0; i < max&&z < positions.size();i++){
            for(int j = 0;j <=i&&z < positions.size();j++,z++){
                positions.get(z).setPositon(i,(int)(max - j)+i-j);
            }
        }

        positions.get(2).setPositon((int)max,positions.get(0).getY());
        return this;
    }

}
