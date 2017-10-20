/**
 * Troop:表示一方势力的队伍，包括成员，Leader，Cheerer
 */
package Layout;

import Types.Vector2;

public class Troop {
    private String formationName;
    private int size;
    private boolean[][] layout;
    private Vector2 leaderCoordinate, cheerCoordiante;
    private FormationManager formationManager;

    public Troop(){
        formationManager = new FormationManager();

        this.formationName = null;
        this.size = 0;
        this.layout = null;
        this.leaderCoordinate = new Vector2();
        this.cheerCoordiante = new Vector2();

    }
    public Troop(String formationName, int size){
        formationManager = new FormationManager();

        this.formationName = formationName;
        this.size = size;
        this.layout = formationManager.getFormationLayoutWithName(formationName);
        this.leaderCoordinate = formationManager.getLeaderCoordinateWithName(formationName);
        this.cheerCoordiante = formationManager.getCheerCoordinateWithName(formationName);

    }
}
