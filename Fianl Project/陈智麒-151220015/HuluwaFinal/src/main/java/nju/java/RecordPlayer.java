package nju.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class RecordPlayer {

    private BufferedReader br = null;
    private enum State{NONE, CREATE, PLAY};
    private State state = State.NONE;
    private List<Creature> creatureList;

    public RecordPlayer(String file, List<Creature> creatureList, final Field field){
        try {

            this.br = new BufferedReader(new FileReader(file));
            this.creatureList = creatureList;

            new Timer(10, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    RecordPlayer.this.next(field);
                }
            }).start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void next(Field field){
        try {
            String str = br.readLine();
            if(str == null)
                return;
            if(str.equals("CREATE_START")){
                state = State.CREATE;
                return;
            } else if(str.equals("CREATE_OVER")){
                state = State.NONE;
                return;
            } else if(str.equals("PLAY_START")){
                state = State.PLAY;
                return;
            } else if(str.equals("PLAY_OVER")){
                state = State.NONE;
                return;
            } else if(str.equals("")){
                return;
            }

            switch (state){
                case CREATE: {
                    String[] strings = str.split("\t");
                    assert(strings.length == 4);
                    String className = strings[0];
                    int index = Integer.parseInt(strings[1]);
                    int x = Integer.parseInt(strings[2]);
                    int y = Integer.parseInt(strings[3]);
                    if(className.equals("Xiezijing")){
                        this.creatureList.add(new Xiezijing(x, y, field));
                    } else if(className.equals("Xiaolouluo1")){
                        this.creatureList.add(new Xiaolouluo1(x, y, field, index));
                    } else if(className.equals("Huluwa")) {
                        this.creatureList.add(new Huluwa(x, y, field, Huluwa.Color.values()[index], Huluwa.Order.values()[index]));
                    } else if(className.equals("Shejing")) {
                        this.creatureList.add(new Shejing(x, y, field));
                    } else if(className.equals("Grandpa")) {
                        this.creatureList.add(new Grandpa(x, y, field));
                    }
                    break;
                }
                case PLAY: {
                    String[] strings = str.split("\t");
                    //assert (strings.length == 6);
                    int time = Integer.parseInt(strings[0].substring(6));
                    String className = strings[1];
                    int index = Integer.parseInt(strings[2]);
                    Creature.State state = getStateFromState(strings[3]);

                    Creature creature = findCreature(creatureList, className, index);

                    if(state == Creature.State.MARCHING) {
                        try {
                            int dx = Integer.parseInt(strings[4]);
                            int dy = Integer.parseInt(strings[5]);
                            creature.setStateWithoutRecord(state);
                            creature.move(dx, dy);
                        } catch (Exception e){

                        }

                    }
                    else if(state == Creature.State.FIGHTING){
                        creature.setStateWithoutRecord(state);
                    }
                    else if(state == Creature.State.DEAD){
                        creature.setStateWithoutRecord(state);
                    }
                    else{
                        System.out.println(state);
                        System.out.println(strings[3]);
                    }
                    break;
                }
                default:
                    System.out.println("shouldn't be here");
                    System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static private Creature.State getStateFromState(String state) {

        Creature.State rt = null;

        if(state.equals("MARCHING"))
            rt = Creature.State.MARCHING;
        else if(state.equals("DEAD"))
            rt = Creature.State.DEAD;
        else if(state.equals("FIGHTING"))
            rt = Creature.State.FIGHTING;

        return rt;

    }

    static private Creature findCreature(List<Creature> creatureList, String className, int index) {
        for(Creature creature : creatureList){
            if(creature.toString().equals(className + "\t" + index)){
                return creature;
            }
        }
        return null;
    }

}
