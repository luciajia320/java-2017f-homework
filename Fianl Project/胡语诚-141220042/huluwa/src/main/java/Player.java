import java.awt.Image;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    private Field field;
    private FileWriter fileWriter;
    private String resource;
    private String kind;
    private int no;

    public void setImage(String resource) {
        URL loc = this.getClass().getClassLoader().getResource(resource + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public Player(int x, int y, Field field, int no, String resource) {
        super(x, y);

        this.no = no;
        this.field = field;
        this.kind = resource;
        this.resource = resource + " " + no;
        no++;

        URL loc = this.getClass().getClassLoader().getResource(resource + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public int getNo() {
        return no;
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        if(nx < 20) nx = 20;
        else if(nx > 400) nx = 400;
        int ny = this.y() + y;
        if(ny < 20) ny = 20;
        else if(ny > 320) ny = 320;
        this.setX(nx);
        this.setY(ny);
    }

    public void setFile(FileWriter fw) {
        fileWriter = fw;
    }

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();
            int x = rand.nextInt(40) - 20;
            if(x() < 220) x += 5;
            else x-= 5;
            int y = rand.nextInt(40) - 20;
            if(y() < 200) y += 5;
            else y-= 5;
            this.move(x, y);
            try {

                fileWriter.write(resource + " " + x + " " + y + " " + System.currentTimeMillis() + "\n");
                this.field.repaint();

                Thread.sleep(rand.nextInt(1000) + 1000);
                boolean dead = false;

                if(kind == "huluwa") {
                    for(Monster m : field.getMonsters()) {
                        if(!dead && (Math.abs(x()-m.x()) + Math.abs(y()-m.y())) < 20) {
                            if(rand.nextInt(100) > 70) {
                                field.removeItem(this,1);
                                field.repaint();
                                fileWriter.write("death " + kind + " " + no + " " + System.currentTimeMillis() + "\n");
                                Thread.currentThread().interrupt();
                                dead = true;
                            }
                        }
                    }
                    if(!dead && field.getSnake()!= null && ((Math.abs(x()-field.getSnake().x()) + Math.abs(y()-field.getSnake().y())) < 20)) {
                        if(rand.nextInt(100) > 30) {
                            field.removeItem(this,1);
                            field.repaint();
                            fileWriter.write("death " + kind + " " + no + " " + System.currentTimeMillis() + "\n");
                            Thread.currentThread().interrupt();
                            dead = true;
                        }
                    }
                }
                else if(kind == "grandpa") {
                    for(Monster m : field.getMonsters()) {
                        if(!dead && (Math.abs(x()-m.x()) + Math.abs(y()-m.y())) < 20) {
                            if(rand.nextInt(100) > 40) {
                                field.removeItem(this,3);
                                field.repaint();
                                fileWriter.write("death " + kind + " " + no + " " + System.currentTimeMillis() + "\n");
                                Thread.currentThread().interrupt();
                                dead = true;
                            }
                        }
                    }
                    if(!dead && field.getSnake()!= null && ((Math.abs(x()-field.getSnake().x()) + Math.abs(y()-field.getSnake().y())) < 20)) {
                        if(rand.nextInt(100) > 20) {
                            field.removeItem(this,3);
                            field.repaint();
                            fileWriter.write("death " + kind + " " + no + " " + System.currentTimeMillis() + "\n");
                            Thread.currentThread().interrupt();
                            dead = true;
                        }
                    }
                }
                else if(kind == "monster") {
                    for(Huluwa h : field.getHuluwas()) {
                        if(!dead && (Math.abs(x()-h.x()) + Math.abs(y()-h.y())) < 20) {
                            if(rand.nextInt(100) > 30) {
                                field.removeItem(this,2);
                                field.repaint();
                                fileWriter.write("death " + kind + " " + no + " " + System.currentTimeMillis() + "\n");
                                Thread.currentThread().interrupt();
                                dead = true;
                            }
                        }
                    }
                    if(!dead && field.getGrandpa()!= null && ((Math.abs(x()-field.getGrandpa().x()) + Math.abs(y()-field.getGrandpa().y())) < 20)) {
                        if(rand.nextInt(100) > 60) {
                            field.removeItem(this,2);
                            field.repaint();
                            fileWriter.write("death " + kind + " " + no + " " + System.currentTimeMillis() + "\n");
                            Thread.currentThread().interrupt();
                            dead = true;
                        }
                    }
                }
                else if(kind == "snake") {
                    for(Huluwa h : field.getHuluwas()) {
                        if(!dead && (Math.abs(x()-h.x()) + Math.abs(y()-h.y())) < 20) {
                            if(rand.nextInt(100) > 70) {
                                field.removeItem(this,4);
                                field.repaint();
                                fileWriter.write("death " + kind + " " + no + " " + System.currentTimeMillis() + "\n");
                                Thread.currentThread().interrupt();
                                dead = true;
                            }
                        }
                    }
                    if(!dead && field.getGrandpa()!= null && ((Math.abs(x()-field.getGrandpa().x()) + Math.abs(y()-field.getGrandpa().y())) < 20)) {
                        if(rand.nextInt(100) > 80) {
                            field.removeItem(this,4);
                            field.repaint();
                            fileWriter.write("death " + kind + " " + no + " " + System.currentTimeMillis() + "\n");
                            Thread.currentThread().interrupt();
                            dead = true;
                        }
                    }
                }

            } catch (InterruptedException e) {
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}