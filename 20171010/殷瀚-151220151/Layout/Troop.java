/**
 * Troop:表示一方势力的队伍，包括campMember，Leader，Cheerer
 */
package Layout;

import Characters.Cheerer;
import Characters.Creature;
import Characters.Leader;
import Position.Position;
import Types.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Troop {
    private String campName;
    private String formationName;
    private int size;
    private Vector2 anchorCoordinate;
    private List<Vector2> memberCoordinates = new ArrayList<>();
    private Vector2 leaderCoordinate, cheerCoordinate;
    private FormationManager formationManager = new FormationManager();
    private List<Creature> creatures = new ArrayList<>();

    public Troop(){

        this.formationName = null;
        this.campName = null;
        this.size = 0;
        this.memberCoordinates = null;
        this.leaderCoordinate = new Vector2();
        this.cheerCoordinate = new Vector2();
        this.anchorCoordinate = new Vector2(0,0);

    }

    public Troop(int size, String campName, int anchorX, int anchorY){

        this.campName = campName;
        this.formationName = null;
        this.size = size;
        this.leaderCoordinate = new Vector2();
        this.cheerCoordinate = new Vector2();
        this.anchorCoordinate = new Vector2(anchorX, anchorY);
    }

    private void setMemberCoordinates(boolean[][] layout){
        int rowNum = layout.length;
        int colNum = layout[0].length;
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                if(layout[i][j]){
                    Vector2 newMemCoor = new Vector2(i, j);
                    memberCoordinates.add(newMemCoor);
                }
            }
        }
    }

    public void setFormation(String formationName){
        this.formationName = formationName;
        boolean[][] layout = formationManager.getFormationLayoutWithName(formationName);
        this.leaderCoordinate = formationManager.getLeaderCoordinateWithName(formationName);
        this.cheerCoordinate = formationManager.getCheerCoordinateWithName(formationName);
        setMemberCoordinates(layout);
    }

    public void addCreatures(Creature[] newCreatures){

        int num = newCreatures.length;
        if(num == 0)
            return;
        IntStream.range(0, num).forEach(i -> newCreatures[i].setCampName(this.campName));
        creatures.addAll(Arrays.asList(newCreatures).subList(0, num));
    }

    public void addOneCreature(Creature someCreature){
        creatures.add(someCreature);
    }

    public void arrange(Position[][] positions){
        int indexOfMemCoor = 0;
        for(int i = 0 ; i < creatures.size() ; i++) {
            Creature someCreature = creatures.get(i);
            Vector2 coordinateInField = new Vector2();
            if(someCreature instanceof Leader){
                coordinateInField = this.leaderCoordinate.add(this.anchorCoordinate);
            }else if(someCreature instanceof Cheerer){
                coordinateInField = this.cheerCoordinate.add(this.anchorCoordinate);
            }else { //  campMember
                coordinateInField = this.memberCoordinates.get(indexOfMemCoor).add(this.anchorCoordinate);

                if(indexOfMemCoor < memberCoordinates.size() - 1)
                    indexOfMemCoor ++;
                else
                    indexOfMemCoor = 0;
            }

            someCreature.setPosition(positions[coordinateInField.getX()][coordinateInField.getY()]);
        }
    }
}
