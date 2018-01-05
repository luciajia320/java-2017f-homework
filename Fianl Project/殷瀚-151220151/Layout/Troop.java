/**
 * Troop:表示一方势力的队伍，包括campMember，Leader，Cheerer
 */
package Layout;

import Characters.Cheerer;
import Characters.Creature;
import Characters.Leader;
import Field.Position;
import Field.Field;
import Types.FormationName;
import Types.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Troop {
    private String campName;
    private List<Creature> creatures = new ArrayList<>();

    private List<Vector2> memberCoordinates = new ArrayList<>();
    private Vector2 anchorCoordinate;
    private Vector2 leaderCoordinate, cheererCoordinate;

    private FormationManager formationManager = new FormationManager();

    private Field field = null;
    //private Position[][] battleField;

    public Troop(){

        this.campName = null;
        this.memberCoordinates = null;
        this.leaderCoordinate = new Vector2();
        this.cheererCoordinate = new Vector2();
        this.anchorCoordinate = new Vector2(0,0);
    }

    public Troop(String campName, int anchorX, int anchorY){

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


    public void enterField(Field field) {
        this.field  = field;
    }

    public void paintInGraphics(Graphics g, int positionWidth, int positionHeight) {
        for(Creature creature: creatures) {
            creature.paintInGraphics(g, positionWidth, positionHeight);
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

    public Position getPositionAt(int x, int y) {
        try {
            return field.getPositions()[x][y];
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (NullPointerException e) {
            System.out.println("Troop.getPositionAt(int, int): 该Troop未加入Field.");
            return null;
        }
    }
    public void addCreatures(Creature[] newCreatures){

        int num = newCreatures.length;
        if(num == 0)
            return;
        //IntStream.range(0, num).forEach(i -> newCreatures[i].setCampName(this.campName));
        for(Creature creature: newCreatures) {
            creature.setTroop(this);
        }
        creatures.addAll(Arrays.asList(newCreatures).subList(0, num));
    }

    public void addOneCreature(Creature someCreature){
        creatures.add(someCreature);
        someCreature.setTroop(this);
    }

    public void act(){
        for(int i = 0 ; i < creatures.size() ; i++) {
            creatures.get(i).act();
        }
    }

    public void startActing() {
        for(Creature creature: creatures) {
            new Thread(creature).start();
        }
    }

    private void arrange(){
        if(this.field == null) {
            System.out.println("Troop.arrange(): 该Troop未加入Field.");
            return;
        }

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

            someCreature.moveTo(this.field.getPositions()[coordinateInField.getX()][coordinateInField.getY()]);
        }
    }

    public String getCampName() {
        return campName;
    }

    public void askFieldToRepaint() {
        try {
            field.repaint();
        } catch (NullPointerException e) {
            System.out.println("Troop.askFieldToRepaint(): 该Troop未加入Field.");
        }
    }

}
