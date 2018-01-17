import java.util.*;

class Queue{

    private ArrayList<Position> positions;
    final int defaultRow = 0;

    public Queue(int length){
        positions = new ArrayList<>();
        for (int i=0; i<length; i++){
            this.positions.add(new Position(new Coord(defaultRow, i)));
        }
    }

    public ArrayList<Position> getPositions(){
        return positions;
    }

    //單人入隊
    public void enqueue(Creature creature){
        int count=0;
        while (positions.get(count).getHolder()!=null && count<positions.size()) count++;
        positions.ensureCapacity(count+1);
        positions.get(count).setHolder(creature);
        creature.setPosition(positions.get(count));
    }

    //多人随机入队
    public void randomEnqueue(ArrayList<? extends Creature> creatures) {
        int count=0;
        while (positions.get(count).getHolder()!=null && count<positions.size()) count++;
        positions.ensureCapacity(count+creatures.size());
        for (int i=0; i<positions.size(); i++){
            int randomIndex = new Random().nextInt(positions.size());
            while (positions.get(randomIndex).getHolder() != null){
                randomIndex = new Random().nextInt(positions.size());
            }
            creatures.get(i).setPosition(this.positions.get(randomIndex));
        }
    }

    public void clearHolder(){
        for (int i=0; i<positions.size(); i++){ positions.get(i).setHolder(null); }
    }
}