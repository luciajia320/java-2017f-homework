package ui;

import archive.ArchiveRecorder;
import archive.CreatureArchived;
import archive.TimePoint;
import creature.animal.*;
import exception.FormationException;
import formation.FengShi;
import formation.HeYi;
import util.GameMode;
import util.GroundState;
import util.ImageReader;
import util.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static util.Constant.*;

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
        setLayout(null);
        setVisible(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if(ground.getState() == GroundState.READY) {
                        ground.run();
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_L && (ground.getState() == GroundState.READY || ground.getState() == GroundState.OVER)) {
                    menuBar.openitem.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        init();
    }

    public void init() {
        allAnimals.addAll(essenceCrops.getAnimals());
        allAnimals.addAll(calaCrops.getAnimals());

        calaCrops.setEnemyList(essenceCrops.getAnimals());
        essenceCrops.setEnemyList(calaCrops.getAnimals());

        state = GroundState.READY;
    }

    public void run() {
        Animal.runnable = true;
        for(Animal a: allAnimals) {
            a.setCurrentState(State.FIGHT);
            new Thread(a).start();
        }
    }

    public void stop() {
        Animal.runnable = false;
        for (Animal a: allAnimals) {
            a.interrupt();
        }
    }

    public void reset() {
        recordSaved = false;
        recorder.clear();
        space.unbindAll();

        for (Animal a: allAnimals) {
            a.setCurrentState(State.WAIT);
            a.resetCheerTime();
        }

        try {
            calaCrops.setFormation(new HeYi(3, 4));
            essenceCrops.setFormation(new FengShi(7, 4));
            space.bind(snake, 7, 1);
            space.bind(grandPa, 3, 1);
        } catch (FormationException e) {
            e.printStackTrace();
        }
        repaint();
        status.repaint();
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

    @Deprecated
    public void paintGrandPa(Graphics g) {
        g.drawImage(grandPa.getImage(), grandPa.getPosition().getX() * size, grandPa.getPosition().getY() * size, null);
    }

    @Deprecated
    public void paintSnake(Graphics g) {
        g.drawImage(snake.getImage(), snake.getPosition().getX() * size, snake.getPosition().getY() * size, null);
    }

    private void paint(Graphics g, Animal a) {
//        System.out.println(a + " is null: " + (a==null));
//        System.out.println(a + " getimage is null: " + (a.getImage()==null));
//        System.out.println(a + " getPosition is null: " + (a.getPosition()==null));
//        System.out.println(a + " runnable: " + Animal.runnable);
        g.drawImage(a.getImage(), a.getPosition().getX() * size, a.getPosition().getY() * size, null);
    }

    @Deprecated
    private void paintCalaCrops(Graphics g) {
        for(Animal c: calaCrops) {
            paint(g, c);
        }
    }

    @Deprecated
    private void paintEssenceCrops(Graphics g) {
        for(Animal a: essenceCrops) {
            paint(g, a);
        }
    }

    private void paint(Graphics g, Crops c) {
        for(Animal a: c) {
            paint(g, a);
        }
    }

    @Override
    public synchronized void paint(Graphics g) {
        super.paint(g);
        paintBackGround(g);
        if(mode == GameMode.GAME) {
            paint(g, calaCrops);
            paint(g, essenceCrops);
//            g.setFont(new Font("华文行楷", Font.PLAIN, 100));
//            g.setColor(new Color(109, 12, 12));
//            g.drawString("葫芦队胜利", 230, 400);

//            int res = JOptionPane.showConfirmDialog(null, "Do U want to save this archive?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//            if(res == JOptionPane.YES_OPTION) {
//                menuBar.saveitem.doClick();
//            }
            if(state == GroundState.OVER) {
                g.setFont(new Font("华文行楷", Font.PLAIN, 100));
                g.setColor(new Color(109, 12, 12));
                if(calaCrops.Win()) {
                    g.drawString("葫芦队胜利", 230, 400);
                }
                else {
                    g.drawString("妖精队胜利", 230, 400);
                }
                if(!recordSaved) {
                    recorder.save();
                    System.out.println("recordNo: " + recordNo);
                    recordSaved = true;
                }
//                int res = JOptionPane.showConfirmDialog(null, "Do U want to save this archive?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                if(res == JOptionPane.YES_OPTION) {
//                    menuBar.saveitem.doClick();
//                }
            }
        }
        else {
            paint(g, calaCrops);
            paint(g, essenceCrops);

//            int res = JOptionPane.showConfirmDialog(null, "Do U want to save this archive?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//            if(res == JOptionPane.YES_OPTION) {
//                menuBar.saveitem.doClick();
//            }
            if(state == GroundState.OVER) {
                g.setFont(new Font("华文行楷", Font.PLAIN, 100));
                g.setColor(new Color(109, 12, 12));
                if(calaCrops.Win()) {
                    g.drawString("葫芦队胜利", 230, 400);
                }
                else {
                    g.drawString("妖精队胜利", 230, 400);
                }
            }
        }
    }
}
