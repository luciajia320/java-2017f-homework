package Ground;


import Creature.*;
import ENUM.COLOR;
import ENUM.NAME;
import ENUM.STATE;
import UI.*;
import Formation.*;
import Record.*;
import  Exception.TooCrowdedException;

import Hulu.Anno.AuthorAnno;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@AuthorAnno(name = "牟星宇", studentNum = 151070053, department = "信管")
public class BattleGround extends JPanel implements Runnable{

    private int w;//宽（列数）
    private int h;//高（行数）

    private ArrayList<Formation> formations;
    private ArrayList<Creature> creatures;
    private Position[][] positions;

    private ArrayList<ArrayList<Record>> records;//记录

    //图像
    private Image battleground;
    private Image background ;

    private static int saveCount = 0;//储存计数

    private BattleFrame battleFrame;

    private static ExecutorService exec ;

    private STATE state = STATE.READY;
    private STATE lastState;//记录上一个状态

    public STATE getState() { return state; }

    public synchronized void setState(STATE state) {
        lastState = this.state;
        this.state = state;
    }


    public static RecordPlayer recordPlayer = new RecordPlayer();

    public static RecordPlayer getRecordPlayer() {
        return recordPlayer;
    }

    public ArrayList<ArrayList<Record>> getRecords() {
        return records;
    }

    public BattleGround(int width, int height){



        this.background = BattleImage.getImage("Images/background.png");
        this.battleground = BattleImage.getImage("Images/battleground.png");;


        this.w = width;
        this.h = height;
        formations = new ArrayList<Formation>();
        creatures = new ArrayList<Creature>();
        records = new ArrayList<>();
        positions = new Position[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                positions[i][j] = new Position(i,j);
            }
        }
        //放置生物
        try {
            layout(Grandfa.getGrandfa(), new Position(2, 5));

            Brother[] brothers = new Brother[7];
            for (int i = 0; i < brothers.length; i++) {
                brothers[i] = new Brother(COLOR.values()[i], NAME.values()[i]);
            }
            SnakeFormation sf = new SnakeFormation(brothers);
            layout(sf, new Position(4, 2));

            Monster[]monsters = new Monster[7];
            monsters[0] = Xiezi.getXieZ();
            for(int i = 1; i < monsters.length; i++){
                monsters[i] = new Monster();
            }
            CraneFormation cf = new CraneFormation(monsters);
            layout(cf, new Position(8,2));

            layout(Shejing.getSheJ(), new Position(11, 5));
        }catch(Exception e){
            e.printStackTrace();
        }

        exec = Executors.newCachedThreadPool();

        recordPlayer.setBattleGround(this);

    }



    public void run() {
        while(!battleFrame.getThread().isInterrupted()){
            if(state == STATE.OVER){
                stop();
                battleFrame.mainOptions(gameOver());
                return;
            }else if(state == STATE.RUNNING){
                this.save();
                this.repaint();
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void setBattleFrame(BattleFrame battleFrame) {
        this.battleFrame = battleFrame;
    }

    public void pauseAll(){
        if(state == STATE.RUNNING){ this.setState(STATE.PAUSE);}
        else if(state == STATE.PAUSE){this.setState(lastState);}
        else return;

        for(Creature creature:creatures){
            creature.reversePause();
            synchronized (creature){
                creature.notifyAll();
            }
        }
    }

    public void start(){
        this.setState(STATE.RUNNING);

        for(Creature creature:creatures){
            exec.execute(creature);
        }
    };

    public void exit(){
        System.exit(0);
    };

    public void save(File file){
        try{
            recordPlayer.setRecords(records);
            recordPlayer.writeRecords(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void save(){
        ArrayList<Record> cs = new ArrayList<>();
        for(Creature creature:creatures){
            cs.add(new Record(creature));
        }
        records.add(cs);
        saveCount++;
    }

    public void readSave(File file){
        try {
            initial();
            recordPlayer.readRecords(file);
            state = STATE.REPLAY;
            repaint();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void restart(){
        initial();
        repaint();
    }

    public void stop(){
        exec.shutdownNow();
    }

    public String gameOver(){
        stop();
        Creature c = null;
        for(Creature creature:creatures){
            if(!creature.isDead()){
                c = creature;
                break;
            }
        }
        if(c instanceof Brother || c instanceof Grandfa){
            return "葫芦娃方获胜!";
        }else if (c instanceof Monster){
            return "妖怪方获胜!";
        }else return "双方全灭!";

    }


    public void battling(Creature C1, Creature C2){

        C1.wounded(C2.damage());
        C2.wounded(C1.damage());
    }

    public ArrayList<Creature> groundClear(){
        ArrayList<Creature> cs = new ArrayList<>();
        for(Creature creature:creatures){
            cs.add(creature);
        }
        creatures.clear();
        for(int i=0; i<this.w; i++){
            for(int j=0; j<this.h; j++){
                this.positions[i][j].cleanHolder();
            }
        }
        return cs;
    }


    public final void initial() {

        Creature.setDeathNum(0);

        for(Creature creature:creatures){ creature.initial();}

        ArrayList<Formation> formations1 = new ArrayList<>();
        for(Formation f:this.formations){
            formations1.add(f);
            f.initial();
        }
        formations.clear();
        creatures.clear();
        for(int i=0; i<this.w; i++){
            for(int j=0; j<this.h; j++){
                this.positions[i][j].cleanHolder();
            }
        }

        Grandfa.getGrandfa().initial();
        Shejing.getSheJ().initial();
        Xiezi.getXieZ().initial();

        try{
            layout(Grandfa.getGrandfa(), new Position(2, 5));
            layout(formations1.get(0), new Position(4, 2));
            layout(formations1.get(1), new Position(8,2));
            layout(Shejing.getSheJ(), new Position(11, 5));

        }catch (Exception e){
            e.printStackTrace();
        }

        exec.shutdown();
        exec = Executors.newCachedThreadPool();

        setState(STATE.READY);

        records.clear();

    }

    public void buildWorld(Graphics g) {

        g.drawImage(background,0, 0,this.getWidth(),this.getHeight(),this);
        g.drawImage(battleground, 50, 50,battleground.getWidth(this),battleground.getHeight(this) , this);
        //先画阵亡的

        for(Creature creature : creatures) {
            if (creature.isDead()) {
                g.drawImage(creature.getImage(), 100 + creature.getPosition().getX() * 50, 100 + creature.getPosition().getY() * 50, 50, 50, this);
            }
        }
        for(Creature creature : creatures) {
            if (!creature.isDead()) {
                g.drawImage(creature.getImage(), 100 + creature.getPosition().getX() * 50, 100 + creature.getPosition().getY() * 50, 50, 50, this);
            }
        }



    }

    public Creature getClosestEnemy(Creature creature){
        if(creature instanceof Brother ||creature instanceof Grandfa){
            Monster monster = new Monster();
            monster.setPosition(new Position(100,100));
            for(Creature c : creatures){
                if(c instanceof Monster && !c.isDead()){
                    if(creature.getPosition().distanceFrom(c.getPosition()) <
                            creature.getPosition().distanceFrom(monster.getPosition())){
                        monster = (Monster) c;
                    }
                }
            }
            if(monster.getPosition().getX() == 100) return creature;//没有活着的敌人时，返回自身
            return monster;
        }
        else{
            Creature b = new Brother(COLOR.blue, NAME.七娃);
            b.setPosition(new Position(100,100));
            for(Creature c : creatures){
                if((c instanceof Brother || c instanceof Grandfa) && !c.isDead()){
                    if(creature.getPosition().distanceFrom(c.getPosition()) <
                            creature.getPosition().distanceFrom(b.getPosition())){
                        b = c;
                    }
                }
            }
            if(b.getPosition().getX() == 100) return creature;
            return b;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }



    public void removeCreature(Creature creature){
        if(creatures.contains(creature)){
            if(!creature.isDead()){
                creature.getPosition().cleanHolder();
            }
            creatures.remove(creature);
        }
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Formation> getFormations() {
        return formations;
    }


    public Position[][] getPositions() {
        return positions;
    }

    public void removeFormation(Formation formation){
        if(formations.contains(formation)){
            for(Creature creature : formation.getCreatures()){
                creature.getPosition().cleanHolder();
                this.creatures.remove(creature);
            }
            formation.initial();
            formations.remove(formation);
        }
    }


    public void layout(Creature creature, Position position) throws TooCrowdedException {
        if(this.alreadyLaid(creature)){
            throw new TooCrowdedException("Creature Already Laid");
        }
        if(position.getX() >= this.w || position.getY() >= this.h){
            throw new TooCrowdedException("Out of Bounds");
        }

        if(conflicts(position)){
            throw  new TooCrowdedException("Positions Conflict");
        }
        creature.getPosition().cleanHolder();
        creature.setPosition(positions[position.getX()][position.getY()]);
        creature.setBattleGround(this);
        this.creatures.add(creature);
    }

    public void layout(Formation formation, Position position) throws TooCrowdedException {

        if(this.alreadyLaid(formation)){
            throw new TooCrowdedException("Creatures or Formation.Formation Already Laid");
        }
        if(position.getY() + formation.getHeight() > this.h ||
                position.getX() + formation.getWidth() > this.w) {
            throw new TooCrowdedException("Out of bounds");
        }

        Position position1 = formation.getPosition();
        formation.setPosition(position);

        if(conflicts(formation)){
            formation.setPosition(position1);
            throw new TooCrowdedException("Positions Conflict");
        }

        for(Creature creature : formation.getCreatures()){
            this.layout(creature, new Position((creature.getPosition().getX() + position.getX()), (creature.getPosition().getY() + position.getY())));
        }

        this.formations.add(formation);
    }

    public boolean conflicts(Position position){
        if(positions[position.getX()][position.getY()].isEmpty())
            return false;
        return true;
    }

    public boolean conflicts(Formation formation) {
        for (int i = 0; i < formation.getWidth(); i++) {
            for (int j = 0; j < formation.getHeight(); j++) {
                if (conflicts(positions[formation.getPosition().getX() + i][formation.getPosition().getY() + j])) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean alreadyLaid(Creature creature){
        if(this.creatures.contains(creature))
            return true;
        return false;
    }

    public boolean alreadyLaid(Formation formation){
        if(this.formations.contains(formation))
            return true;
        for(Creature creature : formation.getCreatures()){
            for(Formation f : this.formations) {
                if(f.getCreatures().contains(creature))
                    return true;
            }
        }
        return false;
    }

}

