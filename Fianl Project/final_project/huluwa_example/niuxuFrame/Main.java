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
        //JFrame��add�����̳��������游��Container������������
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //���ô��ڴ�С
        setSize(field.getBoardWidth()*80,
                field.getBoardHeight()*80);
        setLocationRelativeTo(null);
        setTitle("HULU-FATE");
    }


    public static void main(String[] args) {
        Main ground = new Main();
        ground.setVisible(true); //ʹ�ؼ��ɼ�
    }
}