import com.sun.scenario.effect.Offset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

enum formation_ {
    鹤翼,雁行,衡轭,长蛇,鱼鳞,方圆,偃月,锋矢
}

public class Field extends JPanel {

    private final int OFFSET = 20;
    private final int SPACE = 20;

    private formation_ formation = formation_.长蛇;
    private ArrayList tiles = new ArrayList();
    public ArrayList<Huluwa> huluwas = new ArrayList<Huluwa>();
    public ArrayList<Monster> monsters = new ArrayList<Monster>();
    public Grandpa grandpa;
    public Snake snake;
    public ArrayList<Huluwa> dead_huluwas = new ArrayList<Huluwa>();
    public ArrayList<Monster> dead_monsters = new ArrayList<Monster>();
    public Grandpa dead_grandpa;
    public Snake dead_snake;
    private int huluwa_num = 0;
    private  int monster_num = 0;
    FileWriter fw = null;
    Vector<Thread> threadVector = null;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;
    private boolean running = false;
    private boolean recing = false;
    private JLabel jl;

    private String level =
            "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "...@............&...\n" +
                    "...@............&...\n" +
                    "...@............&...\n" +
                    "...@............&...\n" +
                    "...@............&...\n" +
                    "...@............&...\n" +
                    "...@............&...\n" +
                    "...#............&...\n" +
                    "................$...\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n";

    public ArrayList<Huluwa> getHuluwas() {
        return huluwas;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public Grandpa getGrandpa() {
        return grandpa;
    }

    public Snake getSnake() {
        return snake;
    }

    public void removeItem(Player player,int k) {
        switch(k) {
            case 1:huluwas.remove(player);player.setImage("huluwa_dead");dead_huluwas.add((Huluwa) player);break;
            case 2:monsters.remove(player);player.setImage("monster_dead");dead_monsters.add((Monster) player);break;
            case 3:grandpa = null;player.setImage("grandpa_dead");dead_grandpa = (Grandpa) player;break;
            case 4:snake = null;player.setImage("snake_dead");dead_snake = (Snake) player;break;
        }
    }

    public Field() {

        addKeyListener(new TAdapter());
        JButton jb = new JButton("鹤翼");
        jb.setBounds(0, 0, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            if(!running && !recing) {
                formation = formation_.鹤翼;
                level =
                        "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "................&...\n" +
                                "..#.............&...\n" +
                                "...@.....@......&...\n" +
                                "....@...@.......&...\n" +
                                ".....@.@........&...\n" +
                                "......@.........&...\n" +
                                "................&...\n" +
                                "................&...\n" +
                                "................$...\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n";
                restartLevel();
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("雁行");
        jb.setBounds(50, 0, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            if(!running && !recing) {
                formation = formation_.雁行;
                level =
                        "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                ".........@......&...\n" +
                                "........@.......&...\n" +
                                ".......@........&...\n" +
                                "......@.........&...\n" +
                                ".....@..........&...\n" +
                                "....@...........&...\n" +
                                "...@............&...\n" +
                                "..#.............&...\n" +
                                "................$...\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n";
                restartLevel();
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("衡轭");
        jb.setBounds(100, 0, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            if(!running && !recing) {
                formation = formation_.衡轭;
                level =
                        "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "...@............&...\n" +
                                "....@...........&...\n" +
                                "...@............&...\n" +
                                "....@...........&...\n" +
                                "...@............&...\n" +
                                "....@...........&...\n" +
                                "...@............&...\n" +
                                "....#...........&...\n" +
                                "................$...\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n";
                restartLevel();
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("长蛇");
        jb.setBounds(150, 0, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            if(!running && !recing) {
                formation = formation_.长蛇;
                level =
                        "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "...@............&...\n" +
                                "...@............&...\n" +
                                "...@............&...\n" +
                                "...@............&...\n" +
                                "...@............&...\n" +
                                "...@............&...\n" +
                                "...@............&...\n" +
                                "...#............&...\n" +
                                "................$...\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n";
                restartLevel();
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("鱼鳞");
        jb.setBounds(200, 0, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            if(!running && !recing) {
                formation = formation_.鱼鳞;
                level =
                        "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "................&...\n" +
                                ".....@..........&...\n" +
                                ".....@@.........&...\n" +
                                "....#@@@........&...\n" +
                                ".....@..........&...\n" +
                                "................&...\n" +
                                "................&...\n" +
                                "................&...\n" +
                                "................$...\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n";
                restartLevel();
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("方圆");
        jb.setBounds(250, 0, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            if(!running && !recing) {
                formation = formation_.方圆;
                level =
                        "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "................&...\n" +
                                "................&...\n" +
                                "....@...........&...\n" +
                                "...@.@..........&...\n" +
                                "..@...@.........&...\n" +
                                "...@.@..........&...\n" +
                                "....#...........&...\n" +
                                "................&...\n" +
                                "................$...\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n";
                restartLevel();
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("偃月");
        jb.setBounds(300, 0, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            if(!running && !recing) {
                formation = formation_.偃月;
                level =
                        "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "................&...\n" +
                                "................&...\n" +
                                "....@...........&...\n" +
                                "..@@............&...\n" +
                                "..#@............&...\n" +
                                "..@@............&...\n" +
                                "....@...........&...\n" +
                                "................&...\n" +
                                "................$...\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n";
                restartLevel();
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("锋矢");
        jb.setBounds(350, 0, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            if(!running && !recing) {
                formation = formation_.锋矢;
                level =
                        "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n" +
                                "................&...\n" +
                                "................&...\n" +
                                "....@...........&...\n" +
                                "...@@@..........&...\n" +
                                "..#.@.@.........&...\n" +
                                "....@...........&...\n" +
                                "................&...\n" +
                                "................&...\n" +
                                "................$...\n" +
                                "....................\n" +
                                "....................\n" +
                                "....................\n";
                restartLevel();
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("开始(SPACE)");
        jb.setBounds(0, 340, 80, 20);
        jb.addActionListener((ActionEvent e) -> {
            if (!running && !recing) {
                restartLevel();
                jl.setText("运行中......");
                try {
                    fw = new FileWriter("HuluwaRecord" + System.currentTimeMillis() + ".txt");
                    fw.write(formation + "\n");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                threadVector = new Vector<Thread>();
                for (Huluwa huluwa : huluwas) {
                    huluwa.setFile(fw);
                    Thread childThread = new Thread(huluwa);
                    threadVector.add(childThread);
                    childThread.start();
                }
                for (Monster monster : monsters) {
                    monster.setFile(fw);
                    Thread childThread = new Thread(monster);
                    threadVector.add(childThread);
                    childThread.start();
                }
                grandpa.setFile(fw);
                Thread childThread = new Thread(grandpa);
                threadVector.add(childThread);
                childThread.start();
                snake.setFile(fw);
                childThread = new Thread(snake);
                threadVector.add(childThread);
                childThread.start();
                running = true;
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("结束/重开(R)");
        jb.setBounds(80, 340, 80, 20);
        jb.addActionListener((ActionEvent e) -> {
            if (running && !recing) {
                jl.setText("运行结束");
                for (Thread thread : threadVector) {
                    thread.interrupt();
                }
                for (Thread thread : threadVector) {
                    try {
                        thread.join();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                try {
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                restartLevel();
                running = false;
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("回放(L)");
        jb.setBounds(160, 340, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            if(!running && !recing) {
                JFileChooser dlg = new JFileChooser(new File("."));
                dlg.setDialogTitle("Open TXT file");
                dlg.setFileFilter(new fileFilter());
                int result = dlg.showOpenDialog(this);  // 打开"打开文件"对话框
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = dlg.getSelectedFile();
                    try {
                        Thread thread = new Thread(new autorec(file));
                        thread.start();
                    } catch (Exception e_f) {
                        e_f.printStackTrace();
                    }
                } else if (result == JFileChooser.CANCEL_OPTION) {

                } else {
                    JDialog jdl = new JDialog();
                    jdl.setTitle("Error");
                    jdl.setSize(200, 120);
                    jdl.setLocationRelativeTo(null);
                    jdl.add(new JLabel("读取错误！"));
                    jdl.setVisible(true);
                }
            }
            repaint();
            requestFocus();
        });
        add(jb);
        jb = new JButton("说明(T)");
        jb.setBounds(210, 340, 50, 20);
        jb.addActionListener((ActionEvent e) -> {
            JFrame jf = new JFrame();
            JPanel jp = new expPanel();
            jf.add(jp);
            jf.setTitle("说明");
            jf.setSize(jp.getWidth(),jp.getHeight());
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
            requestFocus();
        });
        add(jb);
        jl = new JLabel();
        jl.setBounds(370,340,70,20);
        add(jl);
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        int x = OFFSET;
        int y = OFFSET;

        Tile a;
        Huluwa huluwa;
        Monster monster;

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item == '@') {
                a = new Tile(x, y);
                tiles.add(a);
                huluwa = new Huluwa(x, y, this,huluwa_num++);
                huluwas.add(huluwa);
                x += SPACE;
            } else if (item == '&') {
                a = new Tile(x, y);
                tiles.add(a);
                monster = new Monster(x, y, this,monster_num++);
                monsters.add(monster);
                x += SPACE;
            } else if (item == '#') {
                a = new Tile(x, y);
                tiles.add(a);
                grandpa = new Grandpa(x, y, this);
                x += SPACE;
            } else if (item == '$') {
                a = new Tile(x, y);
                tiles.add(a);
                snake = new Snake(x, y, this);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }

            dead_grandpa = null;
            dead_snake = null;
            h = y;
        }

    }

    public void buildWorld(Graphics g) {

        //g.setColor(new Color(250, 240, 170));
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);

        world.addAll(dead_huluwas);
        world.addAll(dead_monsters);
        if(dead_snake != null)
            world.add(dead_snake);
        if(dead_grandpa != null)
            world.add(dead_grandpa);
        world.addAll(monsters);
        world.addAll(huluwas);
        if(snake != null)
            world.add(snake);
        if(grandpa != null)
            world.add(grandpa);



        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage((new ImageIcon(getClass().getResource("background.jpeg"))).getImage(), 0, 0,this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }


            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {


                //grandpa.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {


                //grandpa.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {


                //grandpa.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {


                //grandpa.move(0, SPACE);

            } else if (key == KeyEvent.VK_L) {
                if(!running && !recing) {
                    JFileChooser dlg = new JFileChooser(new File("."));
                    dlg.setDialogTitle("Open TXT file");
                    dlg.setFileFilter(new fileFilter());
                    int result = dlg.showOpenDialog(null);  // 打开"打开文件"对话框
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = dlg.getSelectedFile();
                        try {
                            Thread thread = new Thread(new autorec(file));
                            thread.start();
                        } catch (Exception e_f) {
                            e_f.printStackTrace();
                        }
                    } else if (result == JFileChooser.CANCEL_OPTION) {

                    } else {
                        JDialog jdl = new JDialog();
                        jdl.setTitle("Error");
                        jdl.setSize(200, 120);
                        jdl.setLocationRelativeTo(null);
                        jdl.add(new JLabel("读取错误！"));
                        jdl.setVisible(true);
                    }
                }
            } else if (key == KeyEvent.VK_SPACE) {
                if (!running && !recing) {
                    restartLevel();
                    jl.setText("运行中......");
                    try {
                        fw = new FileWriter("HuluwaRecord" + System.currentTimeMillis() + ".txt");
                        fw.write(formation + "\n");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    threadVector = new Vector<Thread>();
                    for (Huluwa huluwa : huluwas) {
                        huluwa.setFile(fw);
                        Thread childThread = new Thread(huluwa);
                        threadVector.add(childThread);
                        childThread.start();
                    }
                    for (Monster monster : monsters) {
                        monster.setFile(fw);
                        Thread childThread = new Thread(monster);
                        threadVector.add(childThread);
                        childThread.start();
                    }
                    grandpa.setFile(fw);
                    Thread childThread = new Thread(grandpa);
                    threadVector.add(childThread);
                    childThread.start();
                    snake.setFile(fw);
                    childThread = new Thread(snake);
                    threadVector.add(childThread);
                    childThread.start();
                    running = true;
                }

            } else if (key == KeyEvent.VK_R) {
                if (running && !recing) {
                    jl.setText("运行结束");
                    for (Thread thread : threadVector) {
                        thread.interrupt();
                    }
                    for (Thread thread : threadVector) {
                        try {
                            thread.join();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                    try {
                        fw.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    restartLevel();
                    running = false;
                }
            }
            else if(key == KeyEvent.VK_T) {
                JFrame jf = new JFrame();
                JPanel jp = new expPanel();
                jf.setTitle("说明");
                jf.add(jp);
                jf.setSize(jp.getWidth(),jp.getHeight());
                jf.setLocationRelativeTo(null);
                jf.setVisible(true);
            }

            repaint();
        }
    }


    public void restartLevel() {

        huluwa_num = 0;
        monster_num = 0;
        tiles.clear();
        huluwas.clear();
        monsters.clear();
        grandpa = null;
        snake = null;
        dead_monsters.clear();
        dead_huluwas.clear();
        dead_grandpa = null;
        dead_snake = null;
        initWorld();
        if (completed) {
            completed = false;
        }
    }

    class autorec implements Runnable {
        File file;

        public autorec (File f) {
            file = f;
        }

        public void run() {
            running = true;
            recing = true;
            String str = null;
            try {
                jl.setText("回放中......");
                FileInputStream inputStream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                str = br.readLine();
                switch (str) {
                    case "鹤翼":level =
                            "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "................&...\n" +
                                    "..#.............&...\n" +
                                    "...@.....@......&...\n" +
                                    "....@...@.......&...\n" +
                                    ".....@.@........&...\n" +
                                    "......@.........&...\n" +
                                    "................&...\n" +
                                    "................&...\n" +
                                    "................$...\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n";formation = formation_.鹤翼;break;
                    case "雁行":level =
                            "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    ".........@......&...\n" +
                                    "........@.......&...\n" +
                                    ".......@........&...\n" +
                                    "......@.........&...\n" +
                                    ".....@..........&...\n" +
                                    "....@...........&...\n" +
                                    "...@............&...\n" +
                                    "..#.............&...\n" +
                                    "................$...\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n";formation = formation_.雁行;break;
                    case "衡轭":level =
                            "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "...@............&...\n" +
                                    "....@...........&...\n" +
                                    "...@............&...\n" +
                                    "....@...........&...\n" +
                                    "...@............&...\n" +
                                    "....@...........&...\n" +
                                    "...@............&...\n" +
                                    "....#...........&...\n" +
                                    "................$...\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n";formation = formation_.衡轭;break;
                    case "长蛇":level =
                            "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "...@............&...\n" +
                                    "...@............&...\n" +
                                    "...@............&...\n" +
                                    "...@............&...\n" +
                                    "...@............&...\n" +
                                    "...@............&...\n" +
                                    "...@............&...\n" +
                                    "...#............&...\n" +
                                    "................$...\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n";formation = formation_.长蛇;break;
                    case "鱼鳞":level =
                            "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "................&...\n" +
                                    ".....@..........&...\n" +
                                    ".....@@.........&...\n" +
                                    "....#@@@........&...\n" +
                                    ".....@..........&...\n" +
                                    "................&...\n" +
                                    "................&...\n" +
                                    "................&...\n" +
                                    "................$...\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n";formation = formation_.鱼鳞;break;
                    case "方圆":level =
                            "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "................&...\n" +
                                    "................&...\n" +
                                    "....@...........&...\n" +
                                    "...@.@..........&...\n" +
                                    "..@...@.........&...\n" +
                                    "...@.@..........&...\n" +
                                    "....#...........&...\n" +
                                    "................&...\n" +
                                    "................$...\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n";formation = formation_.方圆;break;
                    case "偃月":level =
                            "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "................&...\n" +
                                    "................&...\n" +
                                    "....@...........&...\n" +
                                    "..@@............&...\n" +
                                    "..#@............&...\n" +
                                    "..@@............&...\n" +
                                    "....@...........&...\n" +
                                    "................&...\n" +
                                    "................$...\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n";formation = formation_.偃月;break;
                    case "锋矢":level =
                            "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "................&...\n" +
                                    "................&...\n" +
                                    "....@...........&...\n" +
                                    "...@@@..........&...\n" +
                                    "..#.@.@.........&...\n" +
                                    "....@...........&...\n" +
                                    "................&...\n" +
                                    "................&...\n" +
                                    "................$...\n" +
                                    "....................\n" +
                                    "....................\n" +
                                    "....................\n";formation = formation_.锋矢;break;
                }
                restartLevel();
                repaint();
                while ((str = br.readLine()) != null) {
                    String s[] = str.split(" ");
                    Player dele;
                    switch(s[0]) {
                        case "huluwa":for(Huluwa h : huluwas) {
                            if(h.getNo() == Integer.parseInt(s[1]))
                                h.move(Integer.parseInt(s[2]),Integer.parseInt(s[3]));
                        }
                        break;
                        case "monster":for(Monster m : monsters) {
                            if(m.getNo() == Integer.parseInt(s[1]))
                                m.move(Integer.parseInt(s[2]),Integer.parseInt(s[3]));
                        }
                        break;
                        case "grandpa":grandpa.move(Integer.parseInt(s[2]),Integer.parseInt(s[3]));break;
                        case "snake":snake.move(Integer.parseInt(s[2]),Integer.parseInt(s[3]));break;
                        case "death":switch(s[1]) {
                            case "huluwa":dele = null;
                            for(Huluwa h : huluwas) {
                                if(h.getNo() == Integer.parseInt(s[2]))
                                    dele = h;
                            }
                            removeItem(dele,1);
                            break;
                            case "monster":dele = null;
                            for(Monster m : monsters) {
                                if(m.getNo() == Integer.parseInt(s[2]))
                                    dele = m;
                            }
                            removeItem(dele,2);
                            break;
                            case "grandpa":removeItem(grandpa,3);break;
                            case "snake":removeItem(snake,4);break;
                        }
                    }
                    repaint();
                    System.out.println(str);
                    Thread.sleep(100);
                }
                System.out.println("回放完毕");
                jl.setText("回放完毕");
                inputStream.close();
                br.close();
                //restartLevel();
                repaint();
                recing = false;
                running = false;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}

class expPanel extends JPanel {

    public expPanel() {
        super();
        setSize(300,225);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        URL loc = this.getClass().getClassLoader().getResource("huluwa.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        g.drawImage(image,80,10,this);
        g.drawString("葫芦娃",180,23);
        loc = this.getClass().getClassLoader().getResource("grandpa.png");
        iia = new ImageIcon(loc);
        image = iia.getImage();
        g.drawImage(image,80,40,this);
        g.drawString("老爷爷",180,53);
        loc = this.getClass().getClassLoader().getResource("monster.png");
        iia = new ImageIcon(loc);
        image = iia.getImage();
        g.drawImage(image,80,70,this);
        g.drawString("蝎子精",180,83);
        loc = this.getClass().getClassLoader().getResource("snake.png");
        iia = new ImageIcon(loc);
        image = iia.getImage();
        g.drawImage(image,80,100,this);
        g.drawString("蛇精",180,113);
        g.drawString("上排按钮可以为葫芦娃编排不同的阵型，",20,130);
        g.drawString("下排按钮为开始战斗、",20,150);
        g.drawString("结束战斗/重新开始(会生成战斗过程记录)、",20,170);
        g.drawString("战斗过程回放(通过读取记录文件)",20,190);
    }
}

class fileFilter extends FileFilter {
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".txt");  //设置为选择以.class为后缀的文件
    }

    public String getDescription() {
        return "*.txt";
    }
}