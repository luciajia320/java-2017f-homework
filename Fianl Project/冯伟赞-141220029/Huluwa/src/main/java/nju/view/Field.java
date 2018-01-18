package nju.view;

import nju.constant.AppConfig;
import nju.constant.GameParams;
import nju.model.*;
import nju.model.actor.*;
import nju.util.DateUtil;
import nju.util.Log;
import nju.util.Recorder;
import nju.util.Replayer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Field extends JPanel {


    private static final int SPACE = GameParams.SPACE;
    private static final int OFFSET = GameParams.OFFSET;
    private static final String TAG = "Field";
    private static final String PLAT_RESOURCE = "data/plat.json";
    private static final int DEFAULT_PLAT_HEIGHT = 9;
    private static final int DEFAULT_PLAT_WIDTH = 12;
    private static final int[][] DEFAULT_PLAT_DATA = new int[][]{
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}};

    enum State {
        READY, RUNNING, STOP, REPLAY
    }

    private int w = 0;
    private int h = 0;
    private State state = State.STOP;
    private ArrayList<Actor> actors = new ArrayList<>();
    private ExecutorService service = Executors.newCachedThreadPool();

    private Plat plat;
    private Recorder recorder;


    public Field() {
        setFocusable(true);
        initPlat();
        initWorld();
        addKeyListener(new TAdapter(this));
        state = State.READY;
        try {
            String date = DateUtil.formatDate(Calendar.getInstance().getTime(), "MM-dd-HH-mm");
            Log.d(TAG, date);
            recorder = new Recorder(date + "-record.json");
        } catch (IOException e) {
            Log.e(TAG, "cannot create record file.");
        }
    }


    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Plat getPlat() {
        return plat;
    }

    public Recorder getRecorder() {
        return recorder;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public Actor findActorById(int id) {
//        Log.d(TAG, "findActorById - param: " + id);
        for (Actor actor : actors) {
//            Log.d(TAG, "findActorById - " + actor.getId());
            if (actor.getId() == id) {
                return actor;
            }
        }
        return null;

    }


    public boolean isCompleted() {
        return state == State.STOP;
    }

    public void initPlat() {
        Log.i(TAG, "Load map successfully");
        plat = new Plat();
        plat.setHeight(DEFAULT_PLAT_HEIGHT);
        plat.setWidth(DEFAULT_PLAT_WIDTH);
        plat.setData(DEFAULT_PLAT_DATA);
    }

    public final void initWorld() {

        int x = 0;
        int y = 0;
        ActorFactory actorFactory = new ActorFactory();
        for (int i = 0; i < plat.getHeight(); i++) {
            x = 0;
            for (int j = 0; j < plat.getWidth(); j++) {
                int itemId = plat.getDataValue(i, j);
                Actor actor = actorFactory.createActor(itemId, x, y, this);
                if (actor != null) {
                    actors.add(actor);
                }
                x++;
            }
            y++;
        }

        w = Math.max(x, plat.getWidth()) * SPACE;
        h = Math.max(y, plat.getHeight()) * SPACE;
        Log.d(TAG, "width: " + w + " height: " + h);
    }

    public void buildWorld(Graphics g) {
        Background background = new Background(OFFSET, OFFSET);
        g.drawImage(background.getImage(),
                background.getLocation().getX(),
                background.getLocation().getY(),
                w, h, this);

        if (state == State.STOP) {
            g.setColor(new Color(0, 0, 0));
            g.drawString("STOP", 25, 20);
            return;
        }

        for (Actor item : actors) {
            if (!item.isAlive()) {
                g.drawImage(item.getImage(),
                        OFFSET + item.getLocation().getX() * SPACE,
                        OFFSET + item.getLocation().getY() * SPACE,
                        SPACE - 1, SPACE - 1, this);
            }
        }

        int a = 0, b = 0;
        for (Actor item : actors) {
            if (item.isAlive()) {
                g.drawImage(item.getImage(),
                        OFFSET + item.getLocation().getX() * SPACE,
                        OFFSET + item.getLocation().getY() * SPACE,
                        SPACE - 1, SPACE - 1, this);
                if (item.getTeamId() == 1) a++;
                else b++;
            }
        }
        if (a == 0) {
            endGame("妖怪胜利");
        } else if (b == 0) {
            endGame("葫芦娃胜利");
        }
    }

    public void endGame(String msg) {
        if (state == State.RUNNING) {
            state = State.STOP;
            try {
                recorder.recordAllBeans();
                recorder.close();
            } catch (IOException e) {
                Log.e(TAG, "record error");
            }
            Log.i(TAG, "game end - " + msg);
            JOptionPane.showMessageDialog(null, msg,
                    AppConfig.TITLE, JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void restartLevel() {
        actors.clear();
        try {
            recorder.open(DateUtil.formatDate(Calendar.getInstance().getTime()) + "-record.json");
        } catch (IOException e) {
            Log.e(TAG, "cannot create record file.");
        }
        initWorld();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        private Field field;

        public TAdapter(Field field) {
            this.field = field;
        }

        @Override
        public void keyPressed(KeyEvent e) {


            int key = e.getKeyCode();

//            Log.d(TAG, "press " + key);
            switch (key) {
                case KeyEvent.VK_SPACE:
                    Log.d(TAG, "game state - " + state.toString());
                    if (state == State.READY) {
                        for (Actor item : actors) {
                            service.execute(item);
                        }
                        state = State.RUNNING;
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    for (Actor item : actors) {
                        item.setAlive(false);
                    }
                    state = State.STOP;
                    break;
                case KeyEvent.VK_R:
                    state = State.STOP;
                    for (Actor item : actors) {
                        item.setAlive(false);
                    }
                    restartLevel();
                    state = State.READY;
                    break;
                case KeyEvent.VK_L:
                    JFileChooser jfc = new JFileChooser();
                    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    jfc.setCurrentDirectory(new File("."));

                    jfc.setFileFilter(new FileFilter() {
                        @Override
                        public boolean accept(File f) {
                            return f.getName().endsWith(".json");
                        }

                        @Override
                        public String getDescription() {
                            return ".json";
                        }
                    });
                    jfc.showDialog(null, null);
                    File selectedFile = jfc.getSelectedFile();

                    try {
                        if (selectedFile == null) {
                            Log.i(TAG, "no selected file");
                        } else {
                            Log.i(TAG, "selected file: " + selectedFile.getName());
                            state = State.REPLAY;
                            Replayer replayer = new Replayer(field);
                            replayer.load(selectedFile);
                            replayer.replay();
                        }
                    } catch (FileNotFoundException | NullPointerException e1) {
                        e1.printStackTrace();
                        Log.i(TAG, "Can not replay");
                    }


                    break;
            }

            repaint();
        }
    }

}
