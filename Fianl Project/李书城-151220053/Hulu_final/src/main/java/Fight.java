import java.io.*;
import javax.swing.*;
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JFileChooser;
import java.util.concurrent.locks.*;

public class Fight extends JPanel {

    //各战斗种族的编号
    private final int HULUWA = 0;
    private final int SNAKE = 1;
    private final int SCORPION = 2;
    private final int LACKEY = 3;
    private final int GRANDPA = 4;

    public final int MAXVALUE = 1000000000;

    public String conf;
    public String putsFile ="";

    private FileOutputStream out;
    BufferedWriter printOut;

    private final int OFFSET = 30;
    private final int SPACE = 20;

    private final int MOVEMENT = 111;
    private final int DEATH = 222;


    private ArrayList thread = new ArrayList();
    private ArrayList tiles = new ArrayList();

    //使用集合框架，加入各种族对象
    private ArrayList<Snake> snakes = new ArrayList<Snake>();
    private ArrayList<Huluwa> huluwas = new ArrayList<Huluwa>();
    private ArrayList<Scorpion> scorpions = new ArrayList<Scorpion>();
    private ArrayList<Lackey> lackeys = new ArrayList<Lackey>();
    private ArrayList<Grandpa> grandpas = new ArrayList<Grandpa>();

    private Audience audience;
  //  private Referee referee;

    private int huluwa_num, snake_num,lackey_num,scorpion_num,grandpa_num;

    public Lock lock = new ReentrantLock();

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level =
                    ".........................\n" +
                    "...................lll...\n" +
                    "......1...........lll....\n" +
                    "......2..........lll.....\n" +
                    "......3........llll......\n" +
                    "......4.g...o.sllll......\n" +
                    "......5........llll......\n" +
                    "......6..........lll.....\n" +
                    "......7...........lll....\n" +
                    "...................lll...\n" +
                    ".........................\n";

    public Fight() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();

    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        int x = OFFSET;
        int y = OFFSET;

        Tile tile_object;

        Huluwa huluwa;
        huluwa_num =0;
        Snake snake;
        snake_num = 0;
        Lackey lackey;
        lackey_num = 0;
        Grandpa grandpa;
        grandpa_num = 0;
        Scorpion scorpion;
        scorpion_num = 0;


        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            }
            else {
                tile_object = new Tile(x, y);
                tiles.add(tile_object);
                if (item == '1') {
                    huluwa = new Huluwa( x, y, huluwa_num++,item - '0',this, "1.png");
                    huluwas.add(huluwa);
                }
                else if(item == '2'){
                    huluwa = new Huluwa( x, y, huluwa_num++,item - '0' ,this, "2.png");
                    huluwas.add(huluwa);
                }
                else if(item == '3'){
                    huluwa = new Huluwa( x, y, huluwa_num++, item - '0',this, "3.png");
                    huluwas.add(huluwa);
                }
                else if(item == '4'){
                    huluwa = new Huluwa( x, y, huluwa_num++,item - '0', this, "4.png");
                    huluwas.add (huluwa);
                }
                else if(item == '5'){
                    huluwa = new Huluwa( x, y, huluwa_num++,item - '0', this, "5.png");
                    huluwas.add(huluwa);
                }
                else if(item == '6'){
                    huluwa = new Huluwa( x, y, huluwa_num++,item - '0', this, "6.png");
                    huluwas.add(huluwa);
                }
                else if(item == '7'){
                    huluwa = new Huluwa( x, y, huluwa_num++,item - '0', this, "7.png");
                    huluwas.add(huluwa);
                }
                else if(item == 'l'){
                    lackey = new Lackey( x, y, lackey_num++, this, "lac.png");
                    lackeys.add(lackey);
                }
                else if(item == 's'){
                    snake = new Snake(x,y,snake_num++,this,"sna.png");
                    snakes.add(snake);
                }
                else if(item == 'g'){
                    grandpa = new Grandpa(x,y,grandpa_num++,this,"gra.png");
                    grandpas.add(grandpa);
                }
                else if(item == 'o'){
                    scorpion = new Scorpion(x,y,scorpion_num++,this,"sco.png");
                    scorpions.add(scorpion);
                }
                x += SPACE;
            }

            h = y;
        }
       // referee = new Referee(-100,-100,this,"player.png");
        audience = new Audience(-100,-100,this, "player.png");
    }

    public void buildWorld(Graphics g) {
        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();

        world.addAll(tiles);
        world.addAll(huluwas);
        world.addAll(grandpas);
        world.addAll(scorpions);
        world.addAll(lackeys);
        world.addAll(snakes);

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }
        }
     //   if(referee.getWin() == 1)
          //  g.drawString("Hulus Win!!!", 150, 40);
     //   else if(referee.getLose() == 1)
          //  g.drawString("Monsters Win!!!",150,40);
    }

    public ArrayList getGrandpas() {
        return grandpas;
    }

    public ArrayList getHuluwas() {
        return huluwas;
    }

    public ArrayList getLackeys() {
        return lackeys;
    }

    public ArrayList getScorpions() {
        return scorpions;
    }

    public ArrayList getSnakes() {
        return snakes;
    }

    public boolean enemyAllDead(){
        boolean ret = true;
        for(int index = 0;index < snakes.size();++index){
            if(snakes.get(index).dead == 0)
                ret = false;
        }
        for(int index = 0;index < lackeys.size();++index){
            if(lackeys.get(index).dead == 0)
                ret = false;
        }
        for(int index = 0;index < scorpions.size();++index){
            if(scorpions.get(index).dead == 0)
                ret = false;
        }
        return ret;
    }
    public boolean friendsAllDead(){
        boolean ret = true;
        for(int index = 0;index < grandpas.size();++index){
            if(grandpas.get(index).dead == 0)
                ret = false;
        }
        for(int index = 0;index < huluwas.size();++index){
            if(huluwas.get(index).dead == 0)
                ret = false;
        }
        return ret;
    }
    //描述各种族与敌人的作战能力
    void huluwaStrength(Thing2D t, Huluwa newHuluwa){
        //加锁
        lock.lock();
        try {
            int flag=1;
            for(int i=0;i<huluwas.size();++i){
                Huluwa m = (Huluwa)huluwas.get(i);
                if(m.x() == t.x() && m.y() == t.y()){
                    flag=0; break;
                }
            }
            if(flag==1) {
                if(newHuluwa.x()!=t.x() || newHuluwa.y()!=t.y()) {
                    Thing2D tc = new Thing2D(newHuluwa.x(), newHuluwa.y());
                    move(HULUWA, newHuluwa.cnt, tc, t);
                }
                newHuluwa.setX(t.x());
                newHuluwa.setY(t.y());
                for(int i=0;i<lackeys.size();++i){
                    Lackey newLackey= (Lackey) lackeys.get(i);
                    if(newLackey.x() == newHuluwa.x() && newLackey.y() == newHuluwa.y()){
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if( r + (newHuluwa.getBlood() - newLackey.getBlood())/4> 900) {
                            defeat(newHuluwa);
                            newLackey.getWounded(100);
                        }
                        else {
                            defeat(newLackey);
                            newHuluwa.getWounded(100);
                        }
                    }
                }

                for(int i=0;i<snakes.size();++i){
                    Snake newSnake= (Snake) snakes.get(i);
                    if(newSnake.x() == newHuluwa.x() && newSnake.y() == newHuluwa.y()){
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r + newHuluwa.getBlood() - newSnake.getBlood() > 500) {
                            defeat(newHuluwa);
                            newSnake.getWounded(100);
                        }
                        else {
                            defeat(newSnake);
                            newHuluwa.getWounded(100);
                        }
                    }
                }

                for(int i=0;i<scorpions.size();++i){
                    Scorpion newScorpion= (Scorpion) scorpions.get(i);
                    if(newScorpion.x() == newHuluwa.x() && newScorpion.y() == newHuluwa.y()){
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r+newHuluwa.getBlood()-newScorpion.getBlood() > 700) {
                            defeat(newHuluwa);
                            newScorpion.getWounded(100);
                        }
                        else {
                            defeat(newScorpion);
                            newHuluwa.getWounded(100);
                        }
                    }
                }
            }
        }finally {
            lock.unlock();
        }
    }
    void grandpaStrength(Thing2D t, Grandpa newGrandpa){
        lock.lock();
        try {
            int flag=1;
            for(int i=0;i<grandpas.size();++i){
                Grandpa m = (Grandpa) grandpas.get(i);
                if(m.x() == t.x() && m.y() == t.y()){
                    flag=0; break;
                }
            }
            if(flag==1) {
                if(newGrandpa.x()!=t.x() || newGrandpa.y()!=t.y()) {
                    Thing2D tc = new Thing2D(newGrandpa.x(), newGrandpa.y());
                    move(GRANDPA, newGrandpa.cnt, tc, t);
                }
                newGrandpa.setX(t.x());
                newGrandpa.setY(t.y());
                for(int i=0;i<lackeys.size();++i){
                    Lackey newLackey= (Lackey) lackeys.get(i);
                    if(newLackey.x() == newGrandpa.x() && newLackey.y() == newGrandpa.y()){
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if( r + (newGrandpa.getBlood() - newLackey.getBlood())/2> 900) {
                            defeat(newGrandpa);
                            newLackey.getWounded(100);
                        }
                        else {
                            defeat(newLackey);
                            newGrandpa.getWounded(100);
                        }
                    }
                }

                for(int i=0;i<snakes.size();++i){
                    Snake newSnake= (Snake) snakes.get(i);
                    if(newSnake.x() == newGrandpa.x() && newSnake.y() == newGrandpa.y()){
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r + newGrandpa.getBlood() - newSnake.getBlood() > 400) {
                            defeat(newGrandpa);
                            newSnake.getWounded(100);
                        }
                        else {
                            defeat(newSnake);
                            newGrandpa.getWounded(100);
                        }
                    }
                }

                for(int i=0;i<scorpions.size();++i){
                    Scorpion newScorpion= (Scorpion) scorpions.get(i);
                    if(newScorpion.x() == newGrandpa.x() && newScorpion.y() == newGrandpa.y()){
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r+newGrandpa.getBlood()-newScorpion.getBlood() > 500) {
                            defeat(newGrandpa);
                            newScorpion.getWounded(100);
                        }
                        else {
                            defeat(newScorpion);
                            newGrandpa.getWounded(100);
                        }
                    }
                }
            }
        }finally {
            lock.unlock();
        }
    }
    void lackeyStrength(Thing2D t, Lackey newLackey){
        lock.lock();
        try {
            int flag=1;
            for(int i=0;i<lackeys.size();++i){
                Lackey m  = (Lackey) lackeys.get(i);
                if(m.x() == t.x() && m.y() == t.y()){
                    flag=0; break;
                }
            }
            if(flag==1) {
                if(newLackey.x()!=t.x() || newLackey.y()!=t.y()) {
                    Thing2D tc = new Thing2D(newLackey.x(), newLackey.y());
                    move(LACKEY, newLackey.cnt, tc, t);
                }
                newLackey.setX(t.x());
                newLackey.setY(t.y());
                for(int i=0;i<huluwas.size();++i) {
                    Huluwa ca = (Huluwa) huluwas.get(i);
                    if (ca.x() == newLackey.x() && ca.y() == newLackey.y()) {
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r > 800) {
                            defeat(ca);
                            newLackey.getWounded(100);
                        }
                        else {
                            defeat(newLackey);
                            ca.getWounded(50);
                        }
                    }
                }
                for(int i=0;i<grandpas.size();++i) {
                    Grandpa ca = (Grandpa) grandpas.get(i);
                    if (ca.x() == newLackey.x() && ca.y() == newLackey.y()) {
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r > 700) {
                            defeat(ca);
                            newLackey.getWounded(100);
                        }
                        else {
                            defeat(newLackey);
                            ca.getWounded(50);
                        }
                    }
                }
            }
        }finally {
            lock.unlock();
        }
    }
    void snakeStrength(Thing2D t, Snake newSnake){
        lock.lock();
        try {
            int flag=1;
            for(int i=0;i<snakes.size();++i){
                Snake m  = (Snake) snakes.get(i);
                if(m.x() == t.x() && m.y() == t.y()){
                    flag=0; break;
                }
            }
            if(flag==1) {
                if(newSnake.x()!=t.x() || newSnake.y()!=t.y()) {
                    Thing2D tc = new Thing2D(newSnake.x(), newSnake.y());
                    move(SNAKE, newSnake.cnt, tc, t);
                }
                newSnake.setX(t.x());
                newSnake.setY(t.y());
                for(int i=0;i<huluwas.size();++i) {
                    Huluwa ca = (Huluwa) huluwas.get(i);
                    if (ca.x() == newSnake.x() && ca.y() == newSnake.y()) {
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r+newSnake.getBlood() - ca.getBlood() > 400) {
                            defeat(ca);
                            newSnake.getWounded(50);
                        }
                        else {
                            defeat(newSnake);
                            ca.getWounded(100);
                        }
                    }
                }
                for(int i=0;i<grandpas.size();++i) {
                    Grandpa ca = (Grandpa) grandpas.get(i);
                    if (ca.x() == newSnake.x() && ca.y() == newSnake.y()) {
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r+newSnake.getBlood() - ca.getBlood() > 700) {
                            defeat(ca);
                            newSnake.getWounded(50);
                        }
                        else {
                            defeat(newSnake);
                            ca.getWounded(100);
                        }
                    }
                }
            }
        }finally {
            lock.unlock();
        }
    }
    void scorpionStrength(Thing2D t, Scorpion newScorpion){
        lock.lock();
        try {
            int flag=1;
            for(int i=0;i<scorpions.size();++i){
                Scorpion m  = (Scorpion) scorpions.get(i);
                if(m.x() == t.x() && m.y() == t.y()){
                    flag=0; break;
                }
            }
            if(flag==1) {
                if(newScorpion.x()!=t.x() || newScorpion.y()!=t.y()) {
                    Thing2D tc = new Thing2D(newScorpion.x(), newScorpion.y());
                    move(SCORPION, newScorpion.cnt, tc, t);
                }
                newScorpion.setX(t.x());
                newScorpion.setY(t.y());
                for(int i=0;i<huluwas.size();++i) {
                    Huluwa ca = (Huluwa) huluwas.get(i);
                    if (ca.x() == newScorpion.x() && ca.y() == newScorpion.y()) {
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r +newScorpion.getBlood() - ca.getBlood()> 700) {
                            defeat(ca);
                            newScorpion.getWounded(100);
                        }
                        else {
                            defeat(newScorpion);
                            ca.getWounded(50);
                        }
                    }
                }
                for(int i=0;i<grandpas.size();++i) {
                    Grandpa ca = (Grandpa) grandpas.get(i);
                    if (ca.x() == newScorpion.x() && ca.y() == newScorpion.y()) {
                        Random rand = new Random();
                        int r= rand.nextInt(1000);
                        if(r +newScorpion.getBlood() - ca.getBlood()> 500) {
                            defeat(ca);
                            newScorpion.getWounded(100);
                        }
                        else {
                            defeat(newScorpion);
                            ca.getWounded(50);
                        }
                    }
                }
            }
        }finally {
            lock.unlock();
        }
    }

    //被击败
    void defeat( Huluwa newHuluwa){
        int id = -1, type = HULUWA;
        Iterator<Huluwa> it = huluwas.iterator();
        while(it.hasNext()){
            Huluwa tc = it.next();
            if(tc == newHuluwa){
                id=newHuluwa.cnt; newHuluwa.dead =1;
                it.remove(); break;
            }
        }
        if(id!=-1) {
            try {
                printOut.write(DEATH + " " + type + " " + id + "\n");
            }
            catch (Exception e){

            }
        }
    }
    void defeat( Grandpa newGrandpa){
        int id = -1, type = GRANDPA;
        Iterator<Grandpa> it = grandpas.iterator();
        while(it.hasNext()){
            Grandpa tc = it.next();
            if(tc == newGrandpa){
                id=newGrandpa.cnt; newGrandpa.dead =1;
                it.remove(); break;
            }
        }
        if(id!=-1) {
            try {
                printOut.write(DEATH + " " + type + " " + id + "\n");
            }
            catch (Exception e){

            }
        }
    }
    void defeat( Snake newSnake){
        int id = -1, type = SNAKE;
        Iterator<Snake> it = snakes.iterator();
        while(it.hasNext()){
            Snake tc = it.next();
            if(tc == newSnake){
                id=newSnake.cnt; newSnake.dead =1;
                it.remove(); break;
            }
        }
        if(id!=-1) {
            try {
                printOut.write(DEATH + " " + type + " " + id + "\n");
            }
            catch (Exception e){

            }
        }
    }
    void defeat( Scorpion newScorpion){
        int id = -1, type = SCORPION;
        Iterator<Scorpion> it = scorpions.iterator();
        while(it.hasNext()){
            Scorpion tc = it.next();
            if(tc == newScorpion){
                id=newScorpion.cnt; newScorpion.dead =1;
                it.remove(); break;
            }
        }
        if(id!=-1) {
            try {
                printOut.write(DEATH + " " + type + " " + id + "\n");
            }
            catch (Exception e){

            }
        }
    }
    void defeat(Lackey newLackey){
        int id = -1, type = LACKEY;
        Iterator<Lackey> it = lackeys.iterator();
        while(it.hasNext()){
            Lackey tc = it.next();
            if(tc == newLackey){
                id=newLackey.cnt; newLackey.dead =1;
                it.remove(); break;
            }
        }
        if(id!=-1) {
            try {
                printOut.write(DEATH + " " + type + " " + id + "\n");
            } catch (Exception e) {

            }
        }
    }

    void move(int type, int id, Thing2D c, Thing2D t){
        try {
            printOut.write(MOVEMENT +" "+type+" "+id+" ");
            printOut.write(c.x()+" "+c.y()+" "+t.x()+" "+t.y()+"\n");
        }
        catch (Exception e){

        }
    }

    //正邪火并
    public Thing2D fightWithEnemy(Grandpa newGrandpa){
        lock.lock();
        Thing2D t = new Thing2D(newGrandpa.x(), newGrandpa.y());
        double min_dis= MAXVALUE;
        Thing2D nt = new Thing2D(t.x(), t.y());
        try{
            for(int i=0;i<lackeys.size();++i){
                Lackey m = (Lackey) lackeys.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
            for(int i=0;i<snakes.size();++i){
                Snake m = (Snake) snakes.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
            for(int i=0;i<scorpions.size();++i){
                Scorpion m = (Scorpion) scorpions.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
        }finally {
            lock.unlock();
        }
        return nt;
    }
    public Thing2D fightWithEnemy(Huluwa newHuluwa){
        lock.lock();
        Thing2D t = new Thing2D(newHuluwa.x(), newHuluwa.y());
        double min_dis= MAXVALUE;
        Thing2D nt = new Thing2D(t.x(), t.y());
        try{
            for(int i=0;i<lackeys.size();++i){
                Lackey m = (Lackey) lackeys.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
            for(int i=0;i<snakes.size();++i){
                Snake m = (Snake) snakes.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
            for(int i=0;i<scorpions.size();++i){
                Scorpion m = (Scorpion) scorpions.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
        }finally {
            lock.unlock();
        }
        return nt;
    }
    public Thing2D fightWithJustice(Scorpion newScorpion){
        lock.lock();
        Thing2D t = new Thing2D(newScorpion.x(), newScorpion.y());
        double min_dis= MAXVALUE;
        Thing2D nt = new Thing2D(t.x(), t.y());
        try{
            for(int i=0;i<huluwas.size();++i){
                Huluwa m = (Huluwa) huluwas.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
            for(int i=0;i<grandpas.size();++i){
                Grandpa m = (Grandpa) grandpas.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
        }finally {
            lock.unlock();
        }
        return nt;
    }
    public Thing2D fightWithJustice(Snake newSnake){
        lock.lock();
        Thing2D t = new Thing2D(newSnake.x(), newSnake.y());
        double min_dis= MAXVALUE;
        Thing2D nt = new Thing2D(t.x(), t.y());
        try{
            for(int i=0;i<huluwas.size();++i){
                Huluwa m = (Huluwa) huluwas.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
            for(int i=0;i<grandpas.size();++i){
                Grandpa m = (Grandpa) grandpas.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
        }finally {
            lock.unlock();
        }
        return nt;
    }
    public Thing2D fightWithJustice(Lackey newLackey){
        lock.lock();
        Thing2D t = new Thing2D(newLackey.x(), newLackey.y());
        double min_dis= MAXVALUE;
        Thing2D nt = new Thing2D(t.x(), t.y());
        try{
            for(int i=0;i<huluwas.size();++i){
                Huluwa m = (Huluwa) huluwas.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
            for(int i=0;i<grandpas.size();++i){
                Grandpa m = (Grandpa) grandpas.get(i);
                double x = t.x()- m.x(); x*=x;
                double y = t.y()- m.y(); y*=y;
                if(x+y < min_dis){
                    min_dis = x+y;
                    int nx=t.x(), ny=t.y();
                    if(t.x() < m.x()){
                        nx = t.x() + SPACE;
                    }
                    else if(t.x() > m.x()){
                        nx = t.x() - SPACE;
                    }
                    if(t.y() < m.y()){
                        ny = t.y() + SPACE;
                    }
                    else if(t.y() > m.y()){
                        ny = t.y() - SPACE;
                    }
                    nt.setX(nx); nt.setY(ny);
                }
            }
        }finally {
            lock.unlock();
        }
        return nt;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }
    void disappear(int type, int id){
        if(type== HULUWA) {
            Iterator<Huluwa> it = huluwas.iterator();
            while (it.hasNext()) {
                Huluwa tc = it.next();
                if (tc.cnt == id) {
                    it.remove();
                    break;
                }
            }
        }
        else if(type == LACKEY){
            Iterator<Lackey> it= lackeys.iterator();
            while(it.hasNext()){
                Lackey m = it.next();
                if(m.cnt == id){
                    it.remove();
                    break;
                }
            }
        }
        else if(type == SNAKE){
            Iterator<Snake> it= snakes.iterator();
            while(it.hasNext()){
                Snake m = it.next();
                if(m.cnt == id){
                    it.remove();
                    break;
                }
            }
        }
        else if(type == SCORPION){
            Iterator<Scorpion> it= scorpions.iterator();
            while(it.hasNext()){
                Scorpion m = it.next();
                if(m.cnt == id){
                    it.remove();
                    break;
                }
            }
        }
        else if(type == GRANDPA){
            Iterator<Grandpa> it= grandpas.iterator();
            while(it.hasNext()){
                Grandpa m = it.next();
                if(m.cnt == id){
                    it.remove();
                    break;
                }
            }
        }
    }
    void nextStep(int type, int id, int nx, int ny){
        if(type == HULUWA){
            Iterator<Huluwa> it = huluwas.iterator();
            while (it.hasNext()) {
                Huluwa tc = it.next();
                if (tc.cnt == id) {
                    tc.setX(nx); tc.setY(ny);
                    break;
                }
            }
        }
        else if(type == LACKEY) {
            Iterator<Lackey> it= lackeys.iterator();
            while(it.hasNext()){
                Lackey m = it.next();
                if(m.cnt == id){
                    m.setX(nx); m.setY(ny);
                    break;
                }
            }
        }
        else if(type == SNAKE) {
            Iterator<Snake> it= snakes.iterator();
            while(it.hasNext()){
                Snake m = it.next();
                if(m.cnt == id){
                    m.setX(nx); m.setY(ny);
                    break;
                }
            }
        }
        else if(type == SCORPION) {
            Iterator<Scorpion> it= scorpions.iterator();
            while(it.hasNext()){
                Scorpion m = it.next();
                if(m.cnt == id){
                    m.setX(nx); m.setY(ny);
                    break;
                }
            }
        }
        else if(type == GRANDPA) {
            Iterator<Grandpa> it= grandpas.iterator();
            while(it.hasNext()){
                Grandpa m = it.next();
                if(m.cnt == id){
                    m.setX(nx); m.setY(ny);
                    break;
                }
            }
        }
    }
    void explainFile(String line){
        lock.lock();
        String []arr;
        arr=line.split(" ");
        int []ans = new int[10];
        for(int i=0;i<arr.length;++i){
            ans[i]=Integer.parseInt(arr[i]);
        }
        int event=ans[0], type=ans[1], id=ans[2];
        if(event== MOVEMENT){
            int nx = ans[5], ny = ans[6];
            nextStep(type, id, nx, ny);
        }
        else if(event == DEATH){
            disappear(type, id);
        }
        lock.unlock();
    }

    //键盘响应类
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (completed) {
                return;
            }
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
               // Thread refereeThread = new Thread(referee);
               // refereeThread.start();
               //  thread.add(refereeThread);

                for(int i=0;i<huluwas.size();++i){
                    Thread t = new Thread((Huluwa)huluwas.get(i));
                    thread.add(t);
                    t.start();
                }
                for(int i=0;i<lackeys.size();++i){
                    Thread t = new Thread((Lackey)lackeys.get(i));
                    thread.add(t);
                    t.start();
                }
                for(int i=0;i<snakes.size();++i){
                    Thread t = new Thread((Snake)snakes.get(i));
                    thread.add(t);
                    t.start();
                }
                for(int i=0;i<scorpions.size();++i){
                    Thread t = new Thread((Scorpion)scorpions.get(i));
                    thread.add(t);
                    t.start();
                }
                for(int i=0;i<grandpas.size();++i){
                    Thread t = new Thread((Grandpa)grandpas.get(i));
                    thread.add(t);
                    t.start();
                }
                if(putsFile.length() ==0) {
                    putsFile = "record.txt";
                }
                try {
                    FileWriter fw = new FileWriter(putsFile, false);
                    printOut = new BufferedWriter(fw);
                }
                catch (Exception te){

                }

            }
            else if(key == KeyEvent.VK_L){
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                jfc.showDialog(new JLabel(), "choose inputFile");
                File file=jfc.getSelectedFile();
                while(file.isDirectory()){
                    System.out.println("Directory:"+file.getAbsolutePath());
                    jfc.showDialog(new JLabel(), "Please rechoose an inputFile!");
                    file=jfc.getSelectedFile();
                }
                conf = jfc.getSelectedFile().getAbsolutePath();
                System.out.println(conf);
                restartLevel();
                Thread t = new Thread(audience);
                t.start();
                thread.add(t);
            }
            else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }

    //一切归于平静
    void worldEnd(){
        Iterator<Thread> it = thread.iterator();
        while(it.hasNext()){
            Thread t = it.next();
            t.interrupt();
        }
        thread.clear();
    }

    public void restartLevel() {
        try{
            printOut.close();
        }
        catch (Exception e){

        }
        tiles.clear();
        worldEnd();

        huluwas.clear();
        lackeys.clear();
        snakes.clear();
        scorpions.clear();
        grandpas.clear();

        initWorld();
        if (completed) {
            completed = false;
        }
    }

    /*
        //对程序中的一些常数进行定义
    public final static int size = 50;

    //蝎子精方阵所在起点
    public final static int enemy_start_x = 15;
    public final static int enemy_start_y = 40;

    //葫芦娃方阵所在起点
    public final static int hulus_start_x = 10;
    public final static int hulus_start_y = 10;

    //爷爷所在位置
    public final static int grandpa_x = 13;
    public final static int grandpa_y = 15;
     */
}