package UI;

import Hulu.Anno.AuthorAnno;
import UI.BattleFrame;

import javax.swing.*;

@AuthorAnno(name = "牟星宇", studentNum = 151070053, department = "信管")
public class BattleMenu extends JMenuBar {

    private BattleFrame bf;
    private JMenu game=null, pause=null, help=null;
    private JMenuItem start=null, save=null, rsave=null, exit=null,restart=null;
    private JMenuItem p=null;
    private JMenuItem game_instr=null, author_instr=null;

    public BattleMenu(BattleFrame bf){
        this.bf = bf;

        game = new JMenu("游戏");
        pause = new JMenu("游戏控制");
        help = new JMenu("帮助");

        start = new JMenuItem("开始游戏");
        restart = new JMenuItem("重新开始");
        save = new JMenuItem("保存记录");
        rsave = new JMenuItem("读取记录");
        exit = new JMenuItem("退出");

        p = new JMenuItem("暂停/继续");

        game_instr = new JMenuItem("游戏说明");
        author_instr = new JMenuItem("作者说明");

        game.add(start); game.add(restart); game.add(save); game.add(rsave); game.add(exit);
        pause.add(p);
        help.add(game_instr); help.add(author_instr);

        start.addActionListener(e->{bf.start();});
        restart.addActionListener(e->bf.restart());
        save.addActionListener(e->{bf.save();});
        rsave.addActionListener(e->{bf.readSave();});
        exit.addActionListener(e->bf.exit());
        p.addActionListener(e->bf.pause());
        game_instr.addActionListener(e->{bf.gameInstruct();});
        author_instr.addActionListener((e->bf.authorInstruct()));

        add(game); add(pause); add(help);
    }


}
