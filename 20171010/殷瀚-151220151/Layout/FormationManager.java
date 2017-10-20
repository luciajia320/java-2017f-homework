package Layout;

import Types.Vector2;

import java.util.HashMap;

/**
 * 管理所有的阵型
 * 暂时全用成员变量来存储阵型
 */
public class FormationManager {
    private HashMap<String, boolean[][]> formationLayouts = new HashMap<>();
    private HashMap<String, Vector2> leaderCoordinates = new HashMap<>();
    private HashMap<String, Vector2> cheerCoordinates = new HashMap<>();


    public FormationManager(){

        /*录入阵型，领导者和加油者的位置*/

        boolean[][][] FormationsLayouts={
                {
                        {false, false, true, false, false},
                        {false, true, true, true, false},
                        {true, false, true, false, true},
                        {false, false, true, false, false},
                        {false, false, true, false, false}
                },
                {
                        {false, false, true, false, false},
                        {false, false, true, false, false},
                        {false, false, true, false, false},
                        {false, false, true, false, false},
                        {false, false, true, false, false}
                }
        };
        Vector2[] learderCoordinates = new Vector2[1];
        Vector2[] cheerCoordinates = new Vector2[1];
        learderCoordinates[0] = new Vector2(2, 0);
        cheerCoordinates[0] = new Vector2(2, 5);
        learderCoordinates[0] = new Vector2(2, 0);
        cheerCoordinates[0] = new Vector2(2, 5);

        this.formationLayouts.put("锋矢", FormationsLayouts[0]);
        this.leaderCoordinates.put("锋矢", learderCoordinates[0]);
        this.cheerCoordinates.put("锋矢", cheerCoordinates[0]);
        this.formationLayouts.put("长蛇", FormationsLayouts[1]);
        this.leaderCoordinates.put("长蛇", learderCoordinates[1]);
        this.cheerCoordinates.put("长蛇", cheerCoordinates[1]);
    }

    public boolean[][] getFormationLayoutWithName(String formationName){
        return formationLayouts.get(formationName);
    }
    public Vector2 getLeaderCoordinateWithName(String formationName){
        return leaderCoordinates.get(formationName);
    }
    public Vector2 getCheerCoordinateWithName(String formationName){
        return cheerCoordinates.get(formationName);
    }
}


