package niuxuFrame;

import nju.java.*;
import javax.swing.JFrame;


public final class Main extends JFrame {

    private final int OFFSET = 30;


    public Main() {
        InitUI();
    }

    public void InitUI() {
        Field field = new Field();
        //JFrame的add函数继承自他的祖父类Container，用于添加组件
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口大小
        setSize(field.getBoardWidth()*80,
                field.getBoardHeight()*80);
        setLocationRelativeTo(null);
        setTitle("HULU-FATE");
    }


    public static void main(String[] args) {
        Main ground = new Main();
        ground.setVisible(true); //使控件可见
    }
}