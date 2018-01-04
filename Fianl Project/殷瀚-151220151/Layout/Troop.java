/**
 * Troop:表示一方势力的队伍，包括campMember，Leader，Cheerer
 */
package Layout;

import Characters.Cheerer;
import Characters.Creature;
import Characters.Leader;
import Position.Position;
import Types.FormationName;
import Types.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Troop {
    private String campName;
    private List<Creature> creatures = new ArrayList<>();

    private List<Vector2> memberCoordinates = new ArrayList<>();
    private Vector2 anchorCoordinate;
    private Vector2 leaderCoordinate, cheererCoordinate;

    private FormationManager formationManager = new FormationManager();

    private Position[][] battleField;

    public Troop(){

        this.campName = null;
        this.memberCoordinates = null;
        this.leaderCoordinate = new Vector2();
        this.cheererCoordinate = new Vector2();
        this.anchorCoordinate = new Vector2(0,0);
    }

    public Troop(int size, String campName, int anchorX, int anchorY){

        this.campName = campName;
        this.leaderCoordinate = new Vector2();
        this.cheererCoordinate = new Vector2();
        this.anchorCoordinate = new Vector2(anchorX, anchorY);
    }

    public void setAnchorCoordinate(Vector2 anchorCoordinate) {
        this.anchorCoordinate = anchorCoordinate;
    }

    public void setLeaderCoordinate(Vector2 leaderCoordinate) {
        this.leaderCoordinate = leaderCoordinate;
    }

    public void setCheererCoordinate(Vector2 cheererCoordinate) {
        this.cheererCoordinate = cheererCoordinate;
    }

    public void applyTo(Position[][] positions){
        this.battleField = positions;
    }

    public void paintInGraphics(Graphics g) {
        for(Creature creature: creatures) {
            creature.paintInGraphics(g);
        }
    }
    private void setMemberCoordinates(boolean[][] layout){
        memberCoordinates.clear();  //  变换阵型前，先将已有阵型清空

        int rowNum = layout.length;
        int colNum = layout[0].length;
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                if(layout[i][j]){
                    memberCoordinates.add(new Vector2(i, j));
                }
            }
        }
    }

    public void setFormation(FormationName formationName){
        boolean[][] layout = formationManager.getFormationLayoutWithName(formationName);
        this.leaderCoordinate = formationManager.getLeaderCoordinateWithName(formationName);
        this.cheererCoordinate = formationManager.getCheerCoordinateWithName(formationName);
        setMemberCoordinates(layout);
        arrange();
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
        someCreature.setCampName(this.campName);
    }

    public void act(){
        for(int i = 0 ; i < creatures.size() ; i++) {
            creatures.get(i).act();
        }
    }

    private void arrange(){
        int indexOfMemCoor = 0;
        for(int i = 0 ; i < creatures.size() ; i++) {

            Creature someCreature = creatures.get(i);

            Vector2 coordinateInField = new Vector2();
            if(someCreature instanceof Leader){
                coordinateInField = this.leaderCoordinate.add(this.anchorCoordinate);
            }else if(someCreature instanceof Cheerer){
                coordinateInField = this.cheererCoordinate.add(this.anchorCoordinate);
            }else { //  campMember
                coordinateInField = this.memberCoordinates.get(indexOfMemCoor).add(this.anchorCoordinate);

                if(indexOfMemCoor < memberCoordinates.size() - 1)
                    indexOfMemCoor ++;
                else
                    indexOfMemCoor = 0;
            }

            someCreature.moveTo(this.battleField[coordinateInField.getX()][coordinateInField.getY()]);
        }
    }
}
