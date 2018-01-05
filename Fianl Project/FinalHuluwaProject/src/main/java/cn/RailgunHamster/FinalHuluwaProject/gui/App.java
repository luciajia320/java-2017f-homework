package cn.RailgunHamster.FinalHuluwaProject.gui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App {
    private static App app = new App();
    private Frame frame;
    private ActionListener timerAction = e -> {
        if (frame == null) return;
        frame.refresh();
    };
    private Timer timer = new Timer(1000 / Materials.frames, timerAction);

    /**
     * GUI入口
     */
    public static void start() {
        SwingUtilities.invokeLater(() -> {
            App.app.frame = new Frame("葫芦娃最终企划 (151220114 王宇鑫)");
            App.app.addKeyListener();
        });
    }

    static App getApp() {
        return app;
    }

    static void startTimer() {
        App.app.timer.start();
    }

    static void stopTimer() {
        App.app.timer.stop();
    }


    /**
     * 此方法必须在窗体产生后才能返回非空值
     * @return Frame class
     */
    Frame getFrame() {
        return frame;
    }

    static void game() {
        if (App.app.frame == null) return;
        App.app.frame.switchTo("Ground");
    }

    static void close() {
        if (App.app.frame == null) return;
        App.app.frame.dispose();
    }

    static MapControllerProtocol getMap() {
        if (App.app.frame == null) return null;
        return App.app.frame.getGround().getMap();
    }

    /**
     * 程序所有键盘事件的监听器根
     */
    private void addKeyListener() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        frame.gameStart();
                        break;
                    case KeyEvent.VK_L:
                        replay();
                        break;
                    case KeyEvent.VK_R:
                        restart();
                        break;
                }
            }
        });
    }

    private void replay() {
        if (frame == null) return;
        frame.replay();
    }

    private void restart() {
        if (frame == null) return;
        frame.restart();
    }
}
