package cn.RailgunHamster.FinalHuluwaProject.gui;

import javax.swing.*;

class Frame extends JFrame {
    private StartMenu startMenu = new StartMenu();
    private Ground ground = new Ground();

    Frame(String title) {
        super(title);
        this.setLayout(null);
        this.setSize(Materials.defaultSize);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.switchTo("Start");
        this.setVisible(true);
    }

    /**
     * @param name of the panel
     */
    void switchTo(String name) {
        switch (name) {
            case "Start":
                this.setContentPane(startMenu);
                break;
            case "Ground":
                this.ground.animating();
                this.ground.restart();
                this.setContentPane(ground);
                break;
        }
    }

    Ground getGround() {
        return ground;
    }

    void gameStart() {
        if (!this.gameReady()) return;

        if (ground.isGaming()) return;

        if (ground.isReplaying()) return;

        this.ground.gameStart();
    }

    private boolean gameReady() {
        return this.getContentPane() == this.ground && ground.isReady();
    }

    void replay() {
        ground.replay();
    }

    /**
     * 刷新屏幕，检测事件
     */
    void refresh() {
        if (this.getContentPane() != this.ground) return;
        this.ground.refresh();
    }

    void restart() {
        if (this.getContentPane() != this.ground) return;

        if (ground.isGaming()) return;

        if (ground.isReplaying()) return;

        this.ground.restart();
    }

    /**
     * 会终止所有线程
     */
    @Override
    public void dispose() {
        super.dispose();
        ground.closeNow();
        App.stopTimer();
    }
}
