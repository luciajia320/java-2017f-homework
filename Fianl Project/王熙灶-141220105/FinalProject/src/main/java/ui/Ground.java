package ui;

import static util.Constant.space;
import static util.Constant.XSIZE;
import static util.Constant.YSIZE;

import creature.animal.*;
import util.GroundState;
import util.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

public class Ground extends JPanel {
    private final int size = 80;
//    private final int width = 1200;
//    private final int height = 720;
    private List<Animal> allAnimals = new LinkedList<>();
    private CalaCrops calaCrops = CalaCrops.getInstance();
    private EssenceCrops essenceCrops = EssenceCrops.getInstance();
    private GrandPa grandPa = GrandPa.getInstance();
    private SnakeEssence snake = SnakeEssence.getInstance();

    private GroundState state;

    public Ground() {
        space.bind(grandPa, 3, 1);
        space.bind(snake, 7, 1);
//        setBounds(0, 0, width, height);
//        setSize(width, height);
        setLayout(null);
        setVisible(true);

        init();
    }

    public void init() {
        calaCrops.addEnemy(essenceCrops);
        calaCrops.addEnemy(snake);
        essenceCrops.addEnemy(calaCrops);
        essenceCrops.addEnemy(grandPa);

        allAnimals.addAll(essenceCrops.getMinions());
        allAnimals.add(essenceCrops.getScorpionEssence());
        allAnimals.add(snake);
        allAnimals.addAll(calaCrops.getCalabashes());
        allAnimals.add(grandPa);

        state = GroundState.READY;
    }

    public void run() {
//        ExecutorService exec = Executors.newFixedThreadPool(16);
        for(Animal a: allAnimals) {
            new Thread(a).start();
        }
    }

    public void setState(GroundState state) {
        this.state = state;
    }

    public GroundState getState() {
        return state;
    }

    private void paintBackGround(Graphics g) {
        Image image = ImageReader.getImage("grass.png");
        for (int i = 0; i < XSIZE; i++) {
            for (int j = 0; j < YSIZE; j++) {
                g.drawImage(image, i * size, j * size, null);
            }
        }
    }

    private void paintCalaCrops(Graphics g) {
        for(Calabash c: calaCrops) {
            paint(g, c);
        }
    }

    private void paintEssenceCrops(Graphics g) {
        for(Animal a: essenceCrops) {
            paint(g, a);
        }
    }

    @Deprecated
    public void paintGrandPa(Graphics g) {
        g.drawImage(grandPa.getImage(), grandPa.getPosition().getX() * size, grandPa.getPosition().getY() * size, null);
    }

    @Deprecated
    public void paintSnake(Graphics g) {
        g.drawImage(snake.getImage(), snake.getPosition().getX() * size, snake.getPosition().getY() * size, null);
    }

    private void paint(Graphics g, Animal a) {
        g.drawImage(a.getImage(), a.getPosition().getX() * size, a.getPosition().getY() * size, null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintBackGround(g);
        paintCalaCrops(g);
        paintEssenceCrops(g);
        paint(g, grandPa);
        paint(g, snake);
    }
}
