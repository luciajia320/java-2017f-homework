package cn.RailgunHamster.FinalHuluwaProject.gui;

import cn.RailgunHamster.FinalHuluwaProject.module.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Ground extends JPanel {
    private static int row = Materials.row;
    private static int col = Materials.col;
    private Materials materials = new Materials();

    private Dimension unitSize;
    private Dimension space;
    private Dimension unitSpace;
    Dimension getUnitSize() {
        return unitSize;
    }
    Dimension getSpace() {
        return space;
    }
    Dimension getUnitSpace() {
        return unitSpace;
    }

    Ground() {
        this.setLayout(null);
        this.setSize(Materials.defaultSize);
        this.space = new Dimension(Materials.defaultSize.width / 12, Materials.defaultSize.height / 8);
        int width = (Materials.defaultSize.width - this.space.width * 2) / col;
        int height = (Materials.defaultSize.height - this.space.height * 2) / row;
        this.unitSize = new Dimension(width * 2 / 3, height * 2 / 3);
        this.unitSpace = new Dimension(width - this.unitSize.width, height - this.unitSize.height);
    }

    private MapControllerProxy mapProxy = new MapControllerProxy(new MapController());
    private MapControllerProtocol map = mapProxy.create();

    public MapControllerProtocol getMap() {
        return map;
    }

    private boolean ready = false;
    private boolean gaming = false;
    private boolean replaying = false;

    boolean isReady() {
        return ready;
    }

    boolean isGaming() {
        return gaming;
    }

    boolean isReplaying() {
        return replaying;
    }


    /**
     * 清空一局游戏
     */
    private void close() {
        this.closeNow();
        map.clear();
        this.gaming = false;
        this.replaying = false;
    }

    /**
     * 重置单位
     */
    void restart() {
        this.close();
        for (int i = 0;i < row;i++) {
            for (int j = 0;j < col;j++) {
                map.addVisible(new Tile(new Position(i, j)));
                map.addUnit(Unit.getNull(i, j));
            }
        }
        List<Manager> managers = new ArrayList<>();
        managers.add(new GrandFather(new Position(0, 0), "衡轭"));
        managers.add(new Shejing(new Position(0, col - 1), "长蛇"));
        map.addUnits(managers);
        managers.forEach(manager -> {
            manager.addChildren();
            manager.formation();
        });
    }

    void gameStart() {
        this.ready = false;
        this.map.setEnd();
        this.gaming = true;
        map.forEach((position, unit) -> {
            if (unit.thread != null) unit.thread.start();
        });
    }

    void replay() {
        this.ready = false;
        this.replaying = true;
        Thread replayThread = new Thread(() -> {
            mapProxy.replay();
            replaying = false;
            ready = true;
        });
        replayThread.start();
    }

    void animationEnd() {
        this.background = this.animation.lastBackground;
        this.animation = null;
        this.ready = true;
        App.startTimer();
    }

    void refresh() {
        this.repaint();
        if (this.gaming && map.isEnd()) {
            this.gaming = false;
            mapProxy.record();
            this.ready = true;
        }
    }

    /**
     * 终止所有单位的线程
     */
    void closeNow() {
        map.forEach((position, unit) -> {
            if (unit.thread != null) unit.thread.interrupt();
        });
    }

    /**
     * 过场动画实现
     */
    private class Animation {
        private Image originalBackground = materials.getImage("background");
        private List<Image> backgrounds = new ArrayList<>();
        private Image lastBackground;
        private ExecutorService animationPool = Executors.newCachedThreadPool();

        Animation(Ground ground) {
            this.ground = ground;
            this.generate();
        }

        void animating() {
            animationPool.execute(this::__animating);
            animationPool.shutdown();
        }

        private int count = 0;

        void generate() {
            Rectangle rectangle = new Rectangle(Materials.defaultSize);
            double amplifier = 100.0 / 99.0;
            for (int i = 0;i < 75;i++) {
                this.backgrounds.add(materials.getScaledImage(this.originalBackground, rectangle));
                this.amplify(rectangle, amplifier);
            }
        }

        private Ground ground;

        private synchronized void __animating() {
            while (true) {
                try {
                    this.lastBackground = this.backgrounds.get(count);
                    this.ground.repaint();
                    if (++count >= this.backgrounds.size()) break;
                    Thread.sleep(33);
                } catch (InterruptedException ie) {
                    System.out.println("Unhandled InterruptedException");
                }
            }
            this.ground.animationEnd();
        }

        private void amplify(Rectangle rectangle, double amplifier) {
            double width = rectangle.width * amplifier;
            double height = rectangle.height * amplifier;
            double x = Materials.defaultSize.width - width;
            double y = Materials.defaultSize.height - height;
            rectangle.setRect(x, y, width, height);
        }
    }

    private Animation animation = new Animation(this);
    private Image background;

    void animating() {
        if (this.animation == null) return;
        this.animation.animating();
    }

    Image background() {
        if (this.animation != null) return this.animation.lastBackground;
        else return background;
    }

    boolean isAnimating() {
        return this.animation != null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(this.background(), 0, 0, null);

        if (!this.isAnimating()) {
            map.draw(g);
        }
    }
}