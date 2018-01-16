package nju.java;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class CreatureTest {
    @Test
    public void moveControlled() throws Exception {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Field field = new Field();

                JFrame frame = new JFrame();
                frame.setVisible(true);
                frame.add(field);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(field.getBoardWidth() + 30,
                        field.getBoardHeight() + 2 * 30);
                frame.setLocationRelativeTo(null);

                Human h = field.getHumans().get(0);
                System.out.println("Initial: " + h.x() + ", "+ h.y());
                int steps[][] = {
                        {100, 100},
                        {-50, -500},
                        {2000, 200}
                };

                try{
                    //逐步到位测试
                    for ( int[] step: steps){
                        int step_x = step[0];
                        int step_y = step[1];
                        h.moveControlled(step_x, step_y);
                        System.out.println("X Gradually Move " + step_x + ", Y Gradually Move "+ step_y + ": " + h.x() + ", "+ h.y());
                        field.repaint();
                    }

                    field.repaint();
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t1.join();

    }

    @Test
    public void findTarget() throws Exception {
        Field field = new Field();

        for ( Human h: field.getHumans()){

            Creature target = h.FindTarget();

            // Print Human Info and Target Info for test
            String h_info;
            String target_info = target.getClass().getSimpleName() + "(" +  target.x() +"," + target.y() + ")";
            if ( h instanceof Huluwa){
                h_info = ((Huluwa) h).getRank().toString() + "(" +  h.x() +"," + h.y() + ")";
            }
            else{
                h_info = h.getClass().getSimpleName()  + "(" +  h.x() +"," + h.y() + ")";
            }
            System.out.println( h_info + " -- " + target_info);

        }
    }


    @Test
    public void move() throws Exception {

        Field field = new Field();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.add(field);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(field.getBoardWidth() + 30,
                field.getBoardHeight() + 2 * 30);
        frame.setLocationRelativeTo(null);

        Human h = field.getHumans().get(0);
        System.out.println("Initial: " + h.x() + ", "+ h.y());
        int steps[][] = {
                {100, 100},
                {-50, -500},
                {2000, 200}
        };

        //一步到位测试
        for ( int[] step: steps){
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            int step_x = step[0];
            int step_y = step[1];
            h.move(step_x, step_y);
            System.out.println("X Move " + step_x + ", Y Move "+ step_y + ": " + h.x() + ", "+ h.y());
            field.repaint();
        }

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }


    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void battle() throws Exception {

        Field field = new Field();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.add(field);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(field.getBoardWidth() + 30,
                field.getBoardHeight() + 2 * 30);
        frame.setLocationRelativeTo(null);

        Creature attacker = field.getMonsters().get(3);

        for (int i = 0; i < 3; i++){
            Creature target = attacker.FindTarget();
            attacker.moveTo(target);

            boolean result = attacker.Battle(target);
            assertEquals(result, attacker.getAttack() >= target.getAttack());

            System.out.println("attacker-" + attacker.getClass().getSimpleName() + ": "+attacker.getAttack() +
                    "     target-" + target.getClass().getSimpleName()+ ": "+ target.getAttack());
            if ( result ){
                System.out.println("Target Down!");
            }
            else{
                System.out.println("Attacker Down!");
                attacker = target;
            }
        }
        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }

}