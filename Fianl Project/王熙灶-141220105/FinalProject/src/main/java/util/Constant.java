package util;

import space.Space;
import ui.ControlPanel;
import ui.Ground;
import ui.HuluMountainFrame;
import ui.StatusBar;

import javax.swing.*;

public class Constant {
    public static final int XSIZE = 12;
    public static final int YSIZE = 9;
    public static final Space space = new Space(XSIZE, YSIZE);
    public static HuluMountainFrame frame;
    public static final Ground ground = new Ground();

    public static JButton control_start;
    public static JButton control_stop;
    public static JButton control_reset;
    public static final ControlPanel control = new ControlPanel();
    public static final StatusBar status = new StatusBar();

    public static final int WIDTH = 1200 + (1200 - 1194);
    public static final int HEIGHT = 720 + (720 - 668);
}