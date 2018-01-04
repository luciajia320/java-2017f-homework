import Characters.Cheerer;
import Characters.Huluwa;
import Characters.Leader;
import Characters.Louluo;
import Layout.Queue;
import Layout.QuickSorter;
import Layout.Troop;
import Position.Position;
import Types.COLOR;
import Types.FormationName;
import Types.SENIORITY;
import Types.TianGan;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Field extends JPanel{
    private int fieldWidth, fieldHeight, positionRowCount, positionColCount, troopNum;
    private Position[][] positions;

    private List<Troop> troops = new ArrayList<>();

    private Image backgroundImage;

    public Field(int fieldWidth, int fieldHeight, int positionRowCount, int positionColCount, int troopNum) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.positionRowCount = positionRowCount;
        this.positionColCount = positionColCount;
        this.troopNum = troopNum;
        positions = new Position[positionRowCount][positionColCount];

        URL loc = this.getClass().getClassLoader().getResource("Image/background.jpg");
        backgroundImage = new ImageIcon(loc).getImage();
    }
    public Field(int fieldWidth, int troopNum){
        this.fieldWidth = fieldWidth;
        this.troopNum = troopNum;

        positions = new Position[fieldWidth][fieldWidth];

        for(int i = 0; i < fieldWidth; i++){
            for(int j = 0; j < fieldWidth; j++)
                positions[i][j] = new Position(i, j);
        }
    }

    public Position[][] getPositions(){
        return this.positions;
    }
    public void addTroop(Troop newTroop){
        newTroop.applyTo(this.positions);
        troops.add(newTroop);
    }


    public int getBoardWidth() {
        return this.fieldWidth;
    }

    public int getBoardHeight() {
        return this.fieldHeight;
    }

    private void buildWorld(Graphics g) {
        // background
        g.drawImage(backgroundImage, 0, 0, this.getBoardWidth(), this.getBoardHeight(), this);

        for(Troop troop: troops) {
            troop.paintInGraphics(g);
        }

        /*
        URL loc = this.getClass().getClassLoader().getResource("Image/xiaojingang.png");
        Image image = new ImageIcon(loc).getImage();
        g.drawImage(image, 0, 0, 71, 96, 0, 0, 71, 96, this);
        */
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    public void rawShow(){ //  依次显示每个position
        System.out.println();
        for(int i = 0; i < fieldWidth; i++){
            for(int j = 0; j < fieldWidth; j++){
                if(positions[i][j].getHolder() == null){
                    System.out.print("_________ ");
                }else{
                    System.out.print(positions[i][j].getHolder().toString());
                }
            }
            System.out.println();
        }
        System.out.println();

        act();
    }

    public void act(){
        for(int i = 0 ; i < troops.size() ; i++) {
            troops.get(i).act();
        }
    }

    public static void main(String[] args) {
        /* 初始化所有生物 */
        //葫芦娃
        Huluwa[] HuluBrothers = new Huluwa[7];
        for (int i = 0; i < HuluBrothers.length; i++) {
            HuluBrothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
        }
        // 爷爷
        Cheerer YeYe = new Cheerer("爷爷");
        // 蛇精
        Cheerer SheJing = new Cheerer("蛇精");
        // 小喽啰
        Louluo[] lackeys = new Louluo[10];
        for (int i = 0; i < lackeys.length; i++) {
            lackeys[i] = new Louluo(TianGan.values()[i]);
        }
        // 蝎子精
        Leader XieZiJing = new Leader("蝎子精");


        /* 初始化各方势力 */
        Troop powerOfHuluwa = new Troop(5, "葫芦娃", 0, -1);
        Troop powerOfYaojing = new Troop(5, "妖精", 0, 4);

        /* 所有人物加入势力 */
        powerOfHuluwa.addCreatures(HuluBrothers);
        powerOfHuluwa.addOneCreature(YeYe);

        powerOfYaojing.addCreatures(lackeys);
        powerOfYaojing.addOneCreature(SheJing);
        powerOfYaojing.addOneCreature(XieZiJing);

        /* 初始化场地，11*11方阵，可容纳2方势力 */
        Field field = new Field(11, 2);

        /* 各方势力登场 */
        field.addTroop(powerOfHuluwa);
        field.addTroop(powerOfYaojing);



        /* 各方势力布阵 */
        powerOfHuluwa.setFormation(FormationName.长蛇);
        powerOfYaojing.setFormation(FormationName.锋矢);

        /* 使用queue管理待排序的成员 */
        Queue queue = new Queue(HuluBrothers);

        queue.shuffle();    //  打乱
        field.rawShow();

        new QuickSorter().sort(queue); //  冒泡排序
        field.rawShow();

        /* 妖精变换阵型 */
        powerOfYaojing.setFormation(FormationName.偃月);

        field.rawShow();

    }


}
