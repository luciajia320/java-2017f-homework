package nju.java;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Yuyang Wei on 2018/1/5.
 */
public class Review extends JPanel{
    private final int OFFSET=30;
    private final int SPACE=50;
    private Field field;
    private char load[][]=new char[13][13];
    File file=new java.io.File("HuLuWaRecord.txt");
    char loadArray[]=new char[200];
    BufferedReader fis=new BufferedReader(new FileReader(file));
    String line=null;

    private ArrayList Grass=new ArrayList();
    private ArrayList Cloud=new ArrayList();
    private ArrayList TombStone=new ArrayList();

    private Grandfather grandfather;
    private Snake snake;
    private HU1 hu1;
    private HU2 hu2;
    private HU3 hu3;
    private HU4 hu4;
    private HU5 hu5;
    private HU6 hu6;
    private HU7 hu7;
    private Xiezi xiezi;
    private Toad toad58;
    private Toad toad78;
    private Toad toad47;
    private Toad toad87;
    private Toad toad56;
    private Toad toad76;
    private Toad toad65;

    //TODO
    private int w=0;
    private int h=0;

    public Review(Field field) throws FileNotFoundException {
        this.field=field;
        /*this.Grass= Grass;
        this.Cloud=Cloud;
        this.TombStone=TombStone;

        this.grandfather=grandfather;
        this.snake=snake;
        this.hu1=hu1;
        this.hu2=hu2;
        this.hu3=hu3;
        this.hu4=hu4;
        this.hu5=hu5;
        this.hu6=hu6;
        this.hu7=hu7;
        this.xiezi=xiezi;
        this.toad58=toad58;
        this.toad78=toad78;
        this.toad47=toad47;
        this.toad87=toad87;
        this.toad56=toad56;
        this.toad76=toad76;
        this.toad65=toad65;*/
    }

    /*public final void reviewWorld(){

    }*/

    public void start() {
                try {
                    while((line=fis.readLine())!=null){
                        loadArray = line.toCharArray();

                        for (int i = 0; i < 13; i++) {
                            for (int j = 0; j < 13; j++) {
                                load[i][j] = loadArray[13 * i + j];
                            }
                        }

                        System.out.println("+++++++++++++");
                        for (int i = 0; i < 13; i++) {
                            for (int j = 0; j < 13; j++) {
                                System.out.print(load[i][j]);
                            }
                            System.out.println();
                        }
                        System.out.println("+++++++++++++");
                        int x = OFFSET;
                        int y = OFFSET;

                        int ToadFlag[] = new int[7];
                        for (int i = 0; i < 7; i++) {
                            ToadFlag[i] = 0;
                        }
                        Grass init_Grass;
                        Cloud init_Cloud;
                        TombStone init_TombStone;

                        for (int i = 0; i < 13; i++) {
                            for (int j = 0; j < 13; j++) {
                                if (i == 0 && j != 12) {
                                    init_Cloud = new Cloud(x, y);
                                    Cloud.add(init_Cloud);
                                    x += SPACE;
                                }
                                if (j == 12) {
                                    y += SPACE;
                                    if (this.w < x) {
                                        this.w = x;
                                    }
                                    x = OFFSET;
                                } else if (i != 0 && load[i][j] == '.') {
                                    init_Grass = new Grass(x, y);
                                    Grass.add(init_Grass);
                                    x += SPACE;
                                } else if (load[i][j] == 'D') {
                                    init_TombStone = new TombStone(x, y);
                                    TombStone.add(init_TombStone);
                                    x += SPACE;
                                } else if (load[i][j] == 'G') {
                                    grandfather = new Grandfather(x, y, field);
                                    x += SPACE;
                                } else if (load[i][j] == 'S') {
                                    snake = new Snake(x, y, field);
                                    x += SPACE;
                                } else if (load[i][j] == '1') {
                                    init_Grass = new Grass(x, y);
                                    Grass.add(init_Grass);
                                    hu1 = new HU1(x, y, field, load);
                                    x += SPACE;
                                } else if (load[i][j] == '2') {
                                    init_Grass = new Grass(x, y);
                                    Grass.add(init_Grass);
                                    hu2 = new HU2(x, y, field, load);
                                    x += SPACE;
                                } else if (load[i][j] == '3') {
                                    init_Grass = new Grass(x, y);
                                    Grass.add(init_Grass);
                                    hu3 = new HU3(x, y, field, load);
                                    x += SPACE;
                                } else if (load[i][j] == '4') {
                                    init_Grass = new Grass(x, y);
                                    Grass.add(init_Grass);
                                    hu4 = new HU4(x, y, field, load);
                                    x += SPACE;
                                } else if (load[i][j] == '5') {
                                    init_Grass = new Grass(x, y);
                                    Grass.add(init_Grass);
                                    hu5 = new HU5(x, y, field, load);
                                    x += SPACE;
                                } else if (load[i][j] == '6') {
                                    init_Grass = new Grass(x, y);
                                    Grass.add(init_Grass);
                                    hu6 = new HU6(x, y, field, load);
                                    x += SPACE;
                                } else if (load[i][j] == '7') {
                                    init_Grass = new Grass(x, y);
                                    Grass.add(init_Grass);
                                    hu7 = new HU7(x, y, field, load);
                                    x += SPACE;
                                } else if (load[i][j] == 'X') {
                                    init_Grass = new Grass(x, y);
                                    Grass.add(init_Grass);
                                    xiezi = new Xiezi(x, y, field, load);
                                    x += SPACE;
                                } else if (load[i][j] == 'T') {
                                    int flag = 0;
                                    for (int find = 0; find < 7; find++) {
                                        if (ToadFlag[find] == 0) {
                                            flag = find;
                                            break;
                                        }
                                    }
                                    if (flag == 0) {
                                        init_Grass = new Grass(x, y);
                                        Grass.add(init_Grass);
                                        toad58 = new Toad(x, y, field, load);
                                        x += SPACE;
                                        ToadFlag[0] = 1;
                                    } else if (flag == 1) {
                                        init_Grass = new Grass(x, y);
                                        Grass.add(init_Grass);
                                        toad78 = new Toad(x, y, field, load);
                                        x += SPACE;
                                        ToadFlag[1] = 1;
                                    } else if (flag == 2) {
                                        init_Grass = new Grass(x, y);
                                        Grass.add(init_Grass);
                                        toad47 = new Toad(x, y, field, load);
                                        x += SPACE;
                                        ToadFlag[2] = 1;
                                    } else if (flag == 3) {
                                        init_Grass = new Grass(x, y);
                                        Grass.add(init_Grass);
                                        toad87 = new Toad(x, y, field, load);
                                        x += SPACE;
                                        ToadFlag[3] = 1;
                                    } else if (flag == 4) {
                                        init_Grass = new Grass(x, y);
                                        Grass.add(init_Grass);
                                        toad56 = new Toad(x, y, field, load);
                                        x += SPACE;
                                        ToadFlag[4] = 1;
                                    } else if (flag == 5) {
                                        init_Grass = new Grass(x, y);
                                        Grass.add(init_Grass);
                                        toad76 = new Toad(x, y, field, load);
                                        x += SPACE;
                                        ToadFlag[5] = 1;
                                    } else if (flag == 6) {
                                        init_Grass = new Grass(x, y);
                                        Grass.add(init_Grass);
                                        toad65 = new Toad(x, y, field, load);
                                        x += SPACE;
                                        ToadFlag[6] = 1;
                                    }
                                }
                                h = y;
                            }
                        }//transfer char to pic

                        grandfather = new Grandfather(grandfather.x(), grandfather.y(), field);
                        //System.out.println(hu1.x()+" "+hu1.y());
                        snake = new Snake(snake.x(), snake.y(), field);
                        hu1 = new HU1(hu1.x(), hu1.y(), field, load);
                        hu2 = new HU2(hu2.x(), hu2.y(), field, load);
                        hu3 = new HU3(hu3.x(), hu3.y(), field, load);
                        hu4 = new HU4(hu4.x(), hu4.y(), field, load);
                        hu5 = new HU5(hu5.x(), hu5.y(), field, load);
                        hu6 = new HU6(hu6.x(), hu6.y(), field, load);
                        hu7 = new HU7(hu7.x(), hu7.y(), field, load);
                        xiezi = new Xiezi(xiezi.x(), xiezi.y(), field, load);
                        toad58 = new Toad(toad58.x(), toad58.y(), field, load);
                        toad78 = new Toad(toad78.x(), toad78.y(), field, load);
                        toad47 = new Toad(toad47.x(), toad47.y(), field, load);
                        toad87 = new Toad(toad87.x(), toad87.y(), field, load);
                        toad56 = new Toad(toad56.x(), toad56.y(), field, load);
                        toad76 = new Toad(toad76.x(), toad76.y(), field, load);
                        toad65 = new Toad(toad65.x(), toad65.y(), field, load);
                        //TODO

                        //Grass.clear();
                        //System.out.println("Repaint");
                        this.field.repaint();
                        //updateUI();

                        Thread.sleep(1000);
                    }
                    //fis.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                //catch
    }
}
