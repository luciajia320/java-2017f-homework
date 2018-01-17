package tangwy.nju.edu.cn.huluwasvsdemons;

import tangwy.nju.edu.cn.huluwasvsdemons.things.creature.*;
import tangwy.nju.edu.cn.huluwasvsdemons.things.creature.huluwa.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Battlefield {
    private ArrayList<Creature> team1, team2;
    private Creature[][] holders;
    private final int rowNum = GUIWindow.rowNum;
    private final int columnNum = GUIWindow.columnNum;
    private static final long serialVersionUID = 1L;

    Battlefield() {
        team1 = new ArrayList<Creature>();
        team2 = new ArrayList<Creature>();
        holders = new Creature[rowNum][columnNum];
        Creature[] huluwas = {new DaWa(), new ErWa(), new SanWa(), new SiWa(), new WuWa(), new LiuWa(), new QiWa()};
        Creature[] demons = new Creature[15];
        demons[0] = new Scorpion();
        for (int i = 1; i < demons.length; i++) demons[i] = new MinorDemon();
        add(new Formation(huluwas).changShe(), 7, 3, team1);
        add(new Formation(demons).chongE(), 2, 28, team2);
        add(new Grandpa(), 10, 1, team1);
        add(new Snake(), 10, 30, team2);
    }


    private void add(Creature creature, int row, int column, ArrayList<Creature> team) {
        assert (holders[row][column] == null);
        holders[row][column] = creature;
        creature.setPosition(row, column);
        team.add(creature);
        GUIWindow.exec.execute(new Thread(creature));
    }

    private void add(Creature[] formation, int rowOffset, int columnOffset, ArrayList<Creature> team) {
        for (Creature creature : formation) {
            int row = creature.getRow() + rowOffset;
            int column = creature.getColumn() + columnOffset;
            add(creature, row, column, team);
        }
    }

    public ArrayList<Creature> getTeam1() {
        return team1;
    }

    public ArrayList<Creature> getTeam2() {
        return team2;
    }

    public synchronized void getPosition(Creature creature, int row, int column) {
        if (holders[row][column] == null) {
            holders[row][column] = creature;
            //         System.out.println(creature+"get ("+row+","+column+")");

        }
    }

    public boolean ownPosition(Creature creature) {
        return holders[creature.getRow() + creature.getRowMovement()][creature.getColumn() + creature.getColumnMovement()] == creature;
    }

    public synchronized void releasePosition(Creature creature) {
        int row = creature.getRow();
        int column = creature.getColumn();
        holders[row][column] = null;
    }

    public synchronized void releaseTowardsPosition(Creature creature) {
        if (ownPosition(creature))
            holders[creature.getRow() + creature.getRowMovement()][creature.getColumn() + creature.getColumnMovement()] = null;
    }
}

