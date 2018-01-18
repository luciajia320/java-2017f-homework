package Record;

import Creature.Creature;
import ENUM.STATE;
import Ground.BattleGround;
import Hulu.Anno.AuthorAnno;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

@AuthorAnno(name = "牟星宇", studentNum = 151070053, department = "信管")
public class RecordPlayer implements Serializable{

    private ArrayList<ArrayList<Record>> records;
    private BattleGround battleGround;

    public void setBattleGround(BattleGround battleGround) {
        this.battleGround = battleGround;
    }

    public void setRecords(ArrayList<ArrayList<Record>> records) {
        this.records = records;
    }

    public void readRecords(File f) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        ArrayList<ArrayList<Record>> r = (ArrayList<ArrayList<Record>>) ois.readObject();
        this.records = r;
    }


    public void writeRecords(File f) throws Exception{
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(f));
        oo.writeObject(records);
    }


    public void replay(){

        ArrayList<Creature> cs = battleGround.getCreatures();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int count = 0;
            @Override
            public void run() {

                if(count < records.toArray().length){
                    for(Record record:records.get(count)){
                        Creature creature = cs.get(record.getId());

                        creature.setDirection(record.getDirection());
                        creature.setCurrent_Hp(record.getCurrent_Hp());
                        if(!creature.isDead()){
                            creature.getPosition().cleanHolder();
                            creature.setPosition(battleGround.getPositions()[record.getX()][record.getY()]);
                        }else{
                            creature.setPosition(battleGround.getPositions()[record.getX()][record.getY()]);
                            battleGround.getPositions()[record.getX()][record.getY()].setHolder(
                                    battleGround.getPositions()[record.getX()][record.getY()].getLastHolder());
                        }
                    }
                    battleGround.repaint();
                    count++;
                }else{
                    battleGround.repaint();
                    battleGround.setState(STATE.OVER);
                    timer.cancel();
                    return;
                }

            }

        }, 0, 500);
    }

}
