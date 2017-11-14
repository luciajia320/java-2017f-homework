import java.util.ArrayList;
import java.util.List;

public class Planar{
    private int planarSize;
    private Grid[][] grids;
    private  List forms;

    public static final String PLACE_HOLDER = "\uD83C\uDF3F";

    public Grid getGridOF(int x,int y) {
        return grids[x][y];
    }

    public Planar(int size) {
        this.planarSize =size;
        this.grids = new Grid[size][size];

        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                this.grids[i][j]=new Grid(i,j);
            }
        }

        this.forms = new ArrayList();
    }

    public int getPlanarSize() {
        return planarSize;
    }

    public void cleanPlanar(){
        for(int i = 0; i< planarSize; i++){
            for (int j = 0; j< planarSize; j++){
                grids[i][j].setNull();
            }
        }
    }

    public void printPlanar(){
        System.out.println("---------------------------------------------------------------------------");
        for(int i=0;i<planarSize;i++){
            System.out.print("|");
            for (int j=0;j<planarSize;j++){
                if(grids[i][j].isOccupied()){
                    System.out.print(grids[i][j].getHolder().getName()+" ");
                }
                else
                    System.out.print("\uD83C\uDF3F ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------------------");

    }

    private boolean contains(Formations f1, Formations f2) {
        if ((f1.getCoordinate().getX() >= f2.getCoordinate().getX())
                && (f1.getCoordinate().getY() >= f2.getCoordinate().getY())
                && (f1.getCoordinate().getX() + f1.getWidth() <= f2.getCoordinate().getX() + f2.getWidth())
                && (f1.getCoordinate().getY() + f1.getWidth() <= f2.getCoordinate().getY() + f2.getHeight()))
            return true;
        else
            return false;
    }

    private boolean overlap(Formations f1, Formations f2) {
        if (((f1.getCoordinate().getX() + f1.getWidth() >= f2.getCoordinate().getX()))
                && (f1.getCoordinate().getY() + f1.getHeight() >= f2.getCoordinate().getY())
                && (f1.getCoordinate().getX() <= f2.getCoordinate().getX())
                && (f1.getCoordinate().getY() <= f2.getCoordinate().getY()))
            return true;
        return false;
    }


    private boolean conflict(Formations f1, Formations f2) {
        if (contains(f1, f2))
            return true;

        if (contains(f2, f1))
            return true;

        if (overlap(f1, f2))
            return true;

        if (overlap(f2, f1))
            return true;

        return false;
    }

    public void enterCreature(Creature creature,Coordinate start){
        if (start.getX()>planarSize||start.getY()>planarSize){
            System.out.println("出界");
            return;
        }
        else{
            creature.setGrid(grids[start.getX()][start.getY()]);
            grids[start.getX()][start.getY()].setHolder(creature);
        }
    }

    public void layout(Formations formation,Coordinate start) throws TooCrowdedException{
        int x = start.getX();
        int y = start.getY();

        if ((x+formation.getWidth()>planarSize)||
                (y+formation.getHeight()>planarSize||
                        start.getX()>planarSize||start.getY()>planarSize)) {
            throw new TooCrowdedException("Out of bounds!");
        }

        Coordinate coo = formation.getCoordinate();
        formation.setCoordinate(start);

        for (Object exist : forms) {
            if (conflict((Formations) exist, formation)) {
                formation.setCoordinate(coo);
                throw new TooCrowdedException("Conflicted");
            }
        }

        forms.add(formation);

        for(int i=0;i<formation.width;i++) {
            for (int j=0;j<formation.height;j++) {
                grids[x+i][y+j].setHolder(formation.getGridof(i,j).getHolder());
            }
        }

    }



}
