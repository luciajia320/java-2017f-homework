import java.util.ArrayList;

public class Field {

    private ArrayList<ArrayList<Position>> positions;

    public Field(int size){
        positions = new ArrayList<>();
        for (int i=0; i<size; i++){
            positions.add(new ArrayList<>());
        }
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                positions.get(i).add(new Position(new Coord(i, j)));
            }
        }
    }
    public void clearHolder(){
        for (int i=0; i<positions.size(); i++){
            for (int j=0; j<positions.get(0).size(); j++){
                positions.get(i).get(j).setHolder(null);
            }
        }
    }
    public void putCreatureIn(Creature creature, Coord coord){
        //檢查邊界，檢查holder衝突
        if (coord.isOutOfBound(positions.size(), positions.get(0).size()) || positions.get(coord.getX()).get(coord.getY()).holderOccupied()){
            System.out.println("超出邊界或位置衝突!");
            return;
        }
        positions.get(coord.getX()).get(coord.getY()).setHolder(creature);
        creature.setPosition(positions.get(coord.getX()).get(coord.getY()));
    }
    public void putQueueIn(Queue queue, Coord refCoord){
        ArrayList<Position> copyPositions = queue.getPositions();
        for (int i=0; i<copyPositions.size(); i++){
            Coord actualCoord = copyPositions.get(i).getCoord().plusRefCoord(refCoord);
            if (actualCoord.isOutOfBound(positions.size(), positions.get(0).size()) || positions.get(actualCoord.getX()).get(actualCoord.getY()).holderOccupied()){
                System.out.println("超出邊界或位置衝突!");
                return;
            }
        }
        for (int i=0; i<copyPositions.size(); i++){
            Coord actualCoord = copyPositions.get(i).getCoord().plusRefCoord(refCoord);
            copyPositions.get(i).getHolder().setPosition(positions.get(actualCoord.getX()).get(actualCoord.getY()));
            positions.get(actualCoord.getX()).get(actualCoord.getY()).setHolder(copyPositions.get(i).getHolder());
        }
    }
    public void printSituation(){
        for (int i=0; i<positions.size(); i++){
            for (int j=0; j<positions.get(0).size(); j++){
                if (positions.get(i).get(j).getHolder()!=null){
                    positions.get(i).get(j).getHolder().repoName();
                } else {
                    System.out.print("🌱");
                }
            }
            System.out.println();
        }
    }

}
