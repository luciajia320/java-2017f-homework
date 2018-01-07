package HuluTeam;

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import Creature.*;
import Formation.*;

public class BattleField extends JPanel{
	
	public Position[] field;
	public int rownum;
	public int colnum;
	public Enemies ene;
	public HuluBrothers hulu;
	private ImageIcon background = new ImageIcon("source/cave.jpg");
	private JTextArea area;			//战场信息文本控件
	private JTextArea attackInfo;	//战斗记录文本控件
	private Button button;
	private JScrollPane scroLogArea;
	private JScrollPane scroLogAttackInfo;
	protected boolean ifstart;		//游戏是否开始
	private String infocontent;		//战斗记录
	private String mousecontent;	//鼠标记录
	private String infopath = "source/battle.txt";	//战斗记录保存路径
	protected String mousepath = "source/mouse.txt";	//鼠标记录保存路径
	
	
	public BattleField(int row, int col) {
		field = new Position[row*col];
		int index=0;
		for(int j=0;j<row;j++)
			for(int k=0;k<col;k++) {
				field[index] = new Position(j,k);
				index++;
			}
		rownum = row;
		colnum = col;
		
	}
	
	public void addEnemies(Enemies a) {
		this.ene = a;
	}
	
	public void addHuluBrothers(HuluBrothers b) {
		this.hulu = b;
	}
	
	public void huluformation(int x,int y) {
		for(int i=0;i<hulu.getnumber();i++){
			this.setHolder(hulu.getCreature(i), x+i, y);
		}
	}
	
	public void Choose_A_Formation_For_Enemies(int x, int y, int para) {
		this.CleanTheField();
		this.huluformation(rownum/2-4, 1);
		Random r = new Random();
		int choice;
		if(para == -1)
			choice = r.nextInt(8);
		else choice = para;
		switch(choice) {
		case 0:
			ene.adjusttonum(7);
			Snake_Formation s = new Snake_Formation();
			s.SetFormation(this, ene.enemies, x, y);
			mousecontent += (0+"\r\n");
			break;
		case 1:
			ene.adjusttonum(7);
			Crane_Wing_Formation c = new Crane_Wing_Formation();
			c.SetFormation(this, ene.enemies, x, y);
			mousecontent += (1+"\r\n");
			break;
		case 2:
			ene.adjusttonum(4);
			Flying_Geese_Formation f = new Flying_Geese_Formation();
			f.SetFormation(this, ene.enemies, x, y);
			mousecontent += (2+"\r\n");
			break;
		case 3:
			ene.adjusttonum(6);
			Yoke_Formation yo = new Yoke_Formation();
			yo.SetFormation(this, ene.enemies, x, y);
			mousecontent += (3+"\r\n");
			break;
		case 4:
			ene.adjusttonum(10);
			Scale_Formation sc = new Scale_Formation();
			sc.SetFormation(this, ene.enemies, x, y);
			mousecontent += (4+"\r\n");
			break;
		case 5:
			ene.adjusttonum(8);
			Square_Formation d = new Square_Formation();
			d.SetFormation(this, ene.enemies, x, y);
			mousecontent += (5+"\r\n");
			break;
		case 6:
			ene.adjusttonum(17);
			Moon_Formation m = new Moon_Formation();
			m.SetFormation(this, ene.enemies, x, y);
			mousecontent += (6+"\r\n");
			break;
		case 7:
			ene.adjusttonum(12);
			Arrow_Formation ar = new Arrow_Formation();
			ar.SetFormation(this, ene.enemies, x, y);
			mousecontent += (7+"\r\n");
			break;
			}	
    }
	
	public void setHolder(Creature a, int x, int y) {
		for(int i=0;i<rownum*colnum;i++)
		{
			if(field[i].GetPositionX()==x && field[i].GetPositionY()==y){
				field[i].SetHolder(a);
				a.setPosition(x, y);
			}
		}
	}
	
	public void CleanTheField() {		//清空战场上的所有生物
		for(int i=0;i<rownum*colnum;i++) {
			field[i].DeleteHolder();
		}
	}
	
	public void CleanTheEnemies() {
		for(int i=0;i<rownum*colnum;i++)
			if(field[i].reportHolder()&&(field[i].GetHolder().getname().equals("蝎子精")||field[i].GetHolder().getname().equals("杂兵")))
				field[i].DeleteHolder();
	}
	
	public void setFieldVisible() {
		for(int i=0;i<rownum;i++) {
			for(int j=0;j<colnum;j++) {
				if(j>4&&j<colnum-1&&i<rownum-1) {
					field[i*colnum+j].setVisible(false); //敌人部分不可见
				}
			}
		}
	}
	
	public String getHuluBroInfo() {
		String huluInfo = "我方成员信息：\r\n";
		for(int i=0;i<hulu.hulubrothers.size();i++) {
			if(hulu.getCreature(i).currentHP!=0)		//葫芦娃存活
				huluInfo += (hulu.getCreature(i).toString()+"\r\n");
		}
		huluInfo += "\r\n";
		return huluInfo;
	}
	
	public String getEnemiesInfo() {
		String eneInfo = "敌方成员信息：\r\n";
		for(int i=0;i<ene.enemies.size();i++) {
			int x = ene.getCreature(i).getPosition().GetPositionX();
			int y = ene.getCreature(i).getPosition().GetPositionY();	//该敌人所在位置
			if(field[x*colnum+y].getVisible()&&ene.enemies.get(i).currentHP!=0)		//区域可视且敌人存活
				eneInfo += (ene.getCreature(i).toString()+"\r\n");
		}
		return eneInfo;
	}
	
	public void paintComponent(Graphics g) {
		this.setLayout(null);
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		((Graphics2D)g).setStroke(new BasicStroke(8.0f));
		g.setColor(Color.white);
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.95f); //最后一个参数代表不透明度 即 当 最后一个参数为 0.1f 时 画笔的透明度就为90%
		((Graphics2D)g).setComposite(ac);
		initComponent();	
		loadCreatures(g);
		this.updateInfo();
	}
	
	public void initComponent() {		//组件初始化
		//button = new Button("restart");
		area = new JTextArea();
		attackInfo = new JTextArea();
		Font f=new Font("微软雅黑",Font.BOLD,20);
		//button.setFont(f);
		area.setFont(f);
		attackInfo.setFont(f);
		//this.add(button);
		scroLogAttackInfo = new JScrollPane();
		scroLogArea = new JScrollPane();
		scroLogAttackInfo.setViewportView(attackInfo);
		scroLogArea.setViewportView(area);
		this.add(scroLogAttackInfo);
		this.add(scroLogArea);
		scroLogArea.setBounds(2300, 1000, 500, 800);
		//attackInfo.setBounds(400,1500,1600,200);
		scroLogAttackInfo.setBounds(400,1400,1600,300);
		//button.setBounds(2500, 100, 200, 200);
		//button.addActionListener((e)->{this.reloadCreatures();});
		if(!ifstart) {
			infocontent = "葫芦娃遇到了敌人！游戏开始：\r\n\r\n";		//游戏开始前默认战斗记录
			mousecontent = "";
		}
		attackInfo.setText(this.infocontent);
	}
	
	
	public void updateInfo() {		//更新战场信息
		area.setText("战场信息：\r\n\r\n");
		area.append(this.getHuluBroInfo()+this.getEnemiesInfo());
	}
	
	public void setAttackInfo(String s) {	//更新战斗记录并加载到TextArea
		this.infocontent +=s ;
		attackInfo.setText(infocontent);
	}
	
	public void setMouseInfo(String s) {	//更新鼠标记录
		this.mousecontent +=s;
	}
	
	public void reloadCreatures() {
		if(!ifstart) {	//如果游戏未开始，清除战场
			this.CleanTheField();
		}
		this.repaint();
	}
	
	public void get_start() {	//游戏正式开始设置，恢复血量，设置阵型与战场可视信息。
		for(HuluBoy a : hulu.hulubrothers)
			a.currentHP = a.HP;
		for(Creature b : ene.enemies)
			b.currentHP = b.HP;
		this.Choose_A_Formation_For_Enemies(3, 7, -1);
		this.setFieldVisible();
		ifstart = true;			//游戏开始
		infocontent += this.hulu.getCreature(0).getname()+"的回合中：\r\n";
		this.attackInfo.setText(infocontent);
	}
	
	public void loadCreatures(Graphics g) {		//加载生物进场
		if(!ifstart)
			get_start();
		for(int i=0;i<rownum;i++) {
			g.drawLine(0, 200*i, 2600, 200*i);
		}
		for(int i=0;i<colnum;i++) {
			g.drawLine(200*i, 0, 200*i, 1400);
		}
		this.getHuluBroInfo();
		this.getEnemiesInfo();
		if(getEnemiesInfo()== "敌方成员信息：\r\n"&&this.ene.getCreature(0).currentHP==0&&ifstart) {	//代表敌人全部阵亡且在游戏中
			infocontent += ("战斗胜利！战斗记录已保存在"+infopath+"中\r\n");
			infocontent += ("鼠标记录已保存在"+mousepath+"中\r\n");
			try {
				FileWriter ou = new FileWriter(infopath);
				ou.write(this.infocontent);			//保存所有的战斗记录
				ou.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			try {
				FileWriter ou = new FileWriter(mousepath);
				ou.write(this.mousecontent);			//保存所有的鼠标记录
				ou.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			attackInfo.setText(infocontent);
			ifstart = false;	//游戏结束
		}	
		for(int i=0;i<rownum;i++) {
			for(int j=0;j<colnum;j++) {
				if(field[i*colnum+j].getVisible()==false)	//如果不可视，加载迷雾。
					loadCloud(g,i,j);
				else if(field[i*colnum+j].reportHolder()&&field[i*colnum+j].GetHolder().currentHP!=0) {//生物存在且存活根据种类加载图片
					if(field[i*colnum+j].GetHolder().getname()=="蝎子精") 
						loadBoss(g,i,j);
					else if(field[i*colnum+j].GetHolder().getname()=="杂兵")
						loadEnemy(g,i,j);
					else loadHuluWa(g,i,j);
				}
			}
		}
	}
	
	public void loadHuluWa(Graphics g, int i, int j) {	
		ImageIcon Huluwa = new ImageIcon();
		switch(i) {
		case 0:
			Huluwa = new ImageIcon("source/bigwa.png");
			break;
		case 1:
			Huluwa = new ImageIcon("source/erwa.png");
			break;
		case 2:
			Huluwa = new ImageIcon("source/sanwa.png");
			break;
		case 3:
			Huluwa = new ImageIcon("source/siwa.png");
			break;
		case 4:
			Huluwa = new ImageIcon("source/wuwa.png");
			break;
		case 5:
			Huluwa = new ImageIcon("source/liuwa.png");
			break;
		case 6:
			Huluwa = new ImageIcon("source/qiwa.png");
			break;	
		}
		g.drawImage(Huluwa.getImage(), 200*j, 200*i, 200, 200, this);
	}
	
	public void loadEnemy(Graphics g, int i, int j) {
		ImageIcon Enemy = new ImageIcon("source/enemy.png");
		g.drawImage(Enemy.getImage(), 200*j, 200*i, 200, 200, this);
	}
	
	public void loadBoss(Graphics g, int i, int j) {
		ImageIcon Huluwa = new ImageIcon("source/boss.png");
		g.drawImage(Huluwa.getImage(), 200*j, 200*i, 200, 200, this);
	}
	
	public void loadCloud(Graphics g, int i, int j) {
		ImageIcon Huluwa = new ImageIcon("source/cloud.png");
		g.drawImage(Huluwa.getImage(), 200*j, 200*i, 200, 200, this);
	}

}
