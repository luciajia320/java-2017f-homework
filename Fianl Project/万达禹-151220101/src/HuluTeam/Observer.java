package HuluTeam;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
//import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Creature.*;

public class Observer extends JFrame{
	
	protected BattleField f  = new BattleField(8,14);
	protected Timeturn t ;
	protected String path ="source/1053-";
    protected boolean ifWrite;
    protected ArrayList <Integer> a1 = new ArrayList<Integer>();
    protected ArrayList <Integer> b1 = new ArrayList<Integer>();
    protected int idx;
	
	public Observer() {
		super("葫芦娃对战");	
		JFrameInit();
		CreaturesInit();
		t = new Timeturn(f);
		idx = 0;
		ifWrite = false;
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {	//按下回车重新开始游戏
					t.reset();			//回合调度重置
					f.ifstart = false;	//将游戏状态设为未开始
					f.reloadCreatures();
				}
				else if(e.getKeyCode() == KeyEvent.VK_UP) {		//读取文件
					ifWrite = true;
					try {
						FileReader fr = new FileReader(f.mousepath);
						BufferedReader br = new BufferedReader(fr);
						try {
							String Line= br.readLine();
							try {
							    int a = Integer.parseInt(Line);		//String转Integer
							    //System.out.println(a);
							} catch (NumberFormatException e2) {
							    e2.printStackTrace();
							}
							Line= br.readLine();
							while(Line!=null) {
								String[] parameter = new String[2];
								parameter = Line.split(" ");
								try {
									int at =  Integer.parseInt(parameter[0]); 
								    a1.add(at);
								    int bt = Integer.parseInt(parameter[1]);
								    b1.add(bt);
								   // System.out.println(at+" "+bt);
								    //ClickAttack(a1,b1);
								} catch (NumberFormatException e2) {
								    e2.printStackTrace();
								}
								Line = br.readLine();
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					f.setAttackInfo("已读取"+f.mousepath+"的复盘记录。按下→执行下一步\r\n");
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					ClickAttack(a1.get(idx),b1.get(idx));
					idx++;
				}
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {  
				if(!ifWrite)
					ClickAttack(e.getX(),e.getY());
	    	}
		});
	}
	
	public void ClickAttack(int x_ix, int y_ix) {	//攻击处理函数
		int x=x_ix/200;		//根据点击的位置确定数组位置
		int y=y_ix/200;
		if(f.ifstart&&x<f.colnum&&y<f.rownum) {
				String info;
				f.setMouseInfo(x_ix+" "+y_ix+"\r\n");
				if(t.whose==false)
					info = AttackInfo(f,y,x);
				else info = AiInfo(f);
				if(info!="") {		//攻击产生了效果
					f.updateInfo();
					info += t.NextTurn().getname()+"的回合中：\r\n";
					f.setAttackInfo(info);
				}
		}
	}
	
	public String AttackInfo(BattleField f, int x, int y) {		//葫芦娃默认使用skill攻击
		String info = "";
		info = t.turn.skill(f, x, y);
		return info;
	}
	
	public String AiInfo(BattleField f) {		//敌人AI
		String info = "";
		if(t.turn.iftaunt == true) { 
			info += (t.turn.toString() + "受到了三娃的嘲讽！\r\n");
			int x = f.hulu.hulubrothers.get(2).getPosition().GetPositionX();
			int y = f.hulu.hulubrothers.get(2).getPosition().GetPositionY();	//三娃所在位置
			info = t.turn.attack(f,x,y);
			if(f.hulu.hulubrothers.get(2).currentHP == 0) {		//攻击过后三娃死亡
				for(int i=0;i<f.ene.enemies.size();i++) {
					f.ene.enemies.get(i).iftaunt = false;
					info += ("三娃的嘲讽效果解除了。。。\r\n");
				}
			}
		}
		else {	
			int min = 10000;
			int idxmin = 0;
			for(int i=0;i<f.hulu.hulubrothers.size();i++) {		//寻找血量最低的攻击
				if(f.hulu.hulubrothers.get(i).currentHP>0 && f.hulu.hulubrothers.get(i).currentHP<min) {
					min = f.hulu.hulubrothers.get(i).currentHP;
					idxmin = i;
				}
			}
			int x = f.hulu.hulubrothers.get(idxmin).getPosition().GetPositionX();
			int y = f.hulu.hulubrothers.get(idxmin).getPosition().GetPositionY();	//血量最低的葫芦娃所在位置
			if(idxmin == 5&&t.turn.ifdodge) {	//攻击目标是六娃且六娃闪避了
				info += ("六娃闪避了来自"+t.turn.toString()+"的攻击！\r\n");
				info += ("六娃的闪避效果解除了。。。\r\n\r\n");
				for(int i=0;i<f.ene.enemies.size();i++) {
					f.ene.enemies.get(i).ifdodge = false;
				}
				return info;
			}
			info = t.turn.attack(f,x,y);
			if(f.hulu.hulubrothers.get(5).currentHP == 0) {		//攻击过后六娃死亡
				for(int i=0;i<f.ene.enemies.size();i++) {
					f.ene.enemies.get(i).ifdodge = false;
				}
			}
		}
		return info;
	}
	
	public void CreaturesInit() {	//战场生物初始化
		HuluBrothers a = new HuluBrothers();
		Enemies b = new Enemies();
		b.add("蝎子精",1000,120);
		f.addHuluBrothers(a);
		f.addEnemies(b);
	}
	
	public void JFrameInit() {	//窗口初始化
		setSize(2800,1800);
		setLocation(200, 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel cp = (JPanel)getContentPane();
		getLayeredPane().add(f, new Integer(Integer.MIN_VALUE));
		cp.add(f);
	    setVisible(true);
	}

}
