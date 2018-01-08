package util;

import archive.ArchiveRecorder;
import archive.TimePoint;
import creature.animal.*;
import space.Space;
import ui.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constant {
    public static GameMode mode = GameMode.GAME;

    public static final int XSIZE = 12;
    public static final int YSIZE = 9;
    public static final Space space = new Space(XSIZE, YSIZE);

    public static HuluMountainFrame frame;
    public static MenuBar menuBar;
    public static final Ground ground = new Ground();

    public static JButton welcome_enter = new JButton("Enter");
    public static JButton welcome_replay = new JButton("Replay");
    public static JButton welcome_help = new JButton("Help");
    public static final Welcome welcome = new Welcome();

    public static JButton control_start;
    public static JButton control_stop;
    public static JButton control_reset;
    public static final ControlPanel control = new ControlPanel();

    public static final StatusBar status = new StatusBar();

    public static final int WIDTH = 1200 + (1200 - 1194);
    public static final int HEIGHT = 720 + (720 - 668);

    public static final ScorpionEssence scorpion = ScorpionEssence.getInstance();
    public static final SnakeEssence snake = SnakeEssence.getInstance();
    public static final Minion minionA = MinionFactory.getInstance().get(0);
    public static final Minion minionB = MinionFactory.getInstance().get(1);
    public static final Minion minionC = MinionFactory.getInstance().get(2);
    public static final Minion minionD = MinionFactory.getInstance().get(3);
    public static final Minion minionE = MinionFactory.getInstance().get(4);
    public static final Minion minionF = MinionFactory.getInstance().get(5);

    public static final GrandPa grandpa = GrandPa.getInstance();
    public static final Calabash calaA = CalabashFactory.getInstance().get(0);
    public static final Calabash calaB = CalabashFactory.getInstance().get(1);
    public static final Calabash calaC = CalabashFactory.getInstance().get(2);
    public static final Calabash calaD = CalabashFactory.getInstance().get(3);
    public static final Calabash calaE = CalabashFactory.getInstance().get(4);
    public static final Calabash calaF = CalabashFactory.getInstance().get(5);
    public static final Calabash calaG = CalabashFactory.getInstance().get(6);

    public static final CalaCrops calaCrops = CalaCrops.getInstance();
    public static final EssenceCrops essenceCrops = EssenceCrops.getInstance();

    public static final List<Animal> animals = new ArrayList<>(Arrays.asList(
            scorpion, snake, minionA, minionB, minionC, minionD, minionE, minionF,
            grandpa, calaA, calaB, calaC, calaD, calaE, calaF, calaG
    ));

    public static final ArchiveRecorder recorder = new ArchiveRecorder();

    public static volatile int recordNo;
    public static volatile boolean recordSaved = false;

    public static List<TimePoint> ReadPoints = new ArrayList<>();
}