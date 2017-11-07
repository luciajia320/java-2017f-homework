import java.util.Random;

public class Planar{
    private int planarSize;
    private Grid[][] grids;

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

    public void layout(Formations formation,Coordinate start) {
        int x = start.getX();
        int y = start.getY();
        if (start.getX()>planarSize||start.getY()>planarSize){
            System.out.println("出界");
            return;
        }
        else {
            for(int i=0;i<formation.width;i++) {
                for (int j=0;j<formation.height;j++) {

                    grids[x+i][y+j].setHolder(formation.getGridof(i,j).getHolder());
                }
            }
        }
    }

    public void enterRow(Creature[] creatures,Coordinate start){
        if ((start.getY()+creatures.length)>planarSize){
            System.out.println("出界");
            return;
        }
        else{
            for (int i=0;i<creatures.length;i++){
                enterCreature(creatures[i],new Coordinate(start.getX(),start.getY()+1));
            }
        }
    }


}
