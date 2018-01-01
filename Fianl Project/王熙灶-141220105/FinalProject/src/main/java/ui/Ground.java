package ui;

import creature.animal.*;
import space.Space;
import util.ImageReader;

import javax.swing.*;
import java.awt.*;

public class Ground extends JPanel {
    private final int size = 80;
    private final int width = 1200;
    private final int height = 720;
    private CalaCrops calaCrops = CalaCrops.getInstance();
    private EssenceCrops essenceCrops = EssenceCrops.getInstance();
    private GrandPa grandPa = GrandPa.getInstance();
    private SnakeEssence snake = SnakeEssence.getInstance();
    public static Space space = new Space(15, 9);

    public Ground() {
        space.creature_position_setter(grandPa, 5, 1);
        space.creature_position_setter(snake, 9, 1);
        setBounds(0, 0, width, height);
        setVisible(true);
        setSize(width, height);
        setLayout(null);
    }

    private void paintGrass(Graphics g) {
        Image image = ImageReader.getImage("grass.png");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 9; j++) {
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
        paintGrass(g);
        paintCalaCrops(g);
        paintEssenceCrops(g);
        paint(g, grandPa);
        paint(g, snake);
    }
}
