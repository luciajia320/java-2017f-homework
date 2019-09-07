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
		super("��«�޶�ս");	
		JFrameInit();
		CreaturesInit();
		t = new Timeturn(f);
		idx = 0;
		ifWrite = false;
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {	//���»س����¿�ʼ��Ϸ
					t.reset();			//�غϵ�������
					f.ifstart = false;	//����Ϸ״̬��Ϊδ��ʼ
					ifWrite = false;
					f.reloadCreatures();
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {		//��ȡ�ļ�
					ifWrite = true;
					try {
						FileReader fr = new FileReader(f.mousepath);
						BufferedReader br = new BufferedReader(fr);
						try {
							String Line= br.readLine();
							try {
							    int a = Integer.parseInt(Line);		//StringתInteger
							    //System.out.println(a);
							    f.CleanTheEnemies();
							    f.Choose_A_Formation_For_Enemies(3, 7, a);
							    f.reloadCreatures();
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
					f.setAttackInfo("�Ѷ�ȡ"+f.mousepath+"�ĸ��̼�¼�����¡�ִ����һ��\r\n");
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
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
	
	public void ClickAttack(int x_ix, int y_ix) {	//����������
		int x=x_ix/200;		//���ݵ����λ��ȷ������λ��
		int y=y_ix/200;
		if(f.ifstart&&x<f.colnum&&y<f.rownum) {
				String info;
				f.setMouseInfo(x_ix+" "+y_ix+"\r\n");
				if(t.whose==false)
					info = AttackInfo(f,y,x);
				else info = AiInfo(f);
				if(info!="") {		//����������Ч��
					f.updateInfo();
					info += t.NextTurn().getname()+"�Ļغ��У�\r\n";
					f.setAttackInfo(info);
				}
		}
	}
	
	public String AttackInfo(BattleField f, int x, int y) {		//��«��Ĭ��ʹ��skill����
		String info = "";
		info = t.turn.skill(f, x, y);
		return info;
	}
	
	public String AiInfo(BattleField f) {		//����AI
		String info = "";
		if(t.turn.iftaunt == true) { 
			info += (t.turn.toString() + "�ܵ������޵ĳ���\r\n");
			int x = f.hulu.hulubrothers.get(2).getPosition().GetPositionX();
			int y = f.hulu.hulubrothers.get(2).getPosition().GetPositionY();	//��������λ��
			info = t.turn.attack(f,x,y);
			if(f.hulu.hulubrothers.get(2).currentHP == 0) {		//����������������
				for(int i=0;i<f.ene.enemies.size();i++) {
					f.ene.enemies.get(i).iftaunt = false;
					info += ("���޵ĳ���Ч������ˡ�����\r\n");
				}
			}
		}
		else {	
			int min = 10000;
			int idxmin = 0;
			for(int i=0;i<f.hulu.hulubrothers.size();i++) {		//Ѱ��Ѫ����͵Ĺ���
				if(f.hulu.hulubrothers.get(i).currentHP>0 && f.hulu.hulubrothers.get(i).currentHP<min) {
					min = f.hulu.hulubrothers.get(i).currentHP;
					idxmin = i;
				}
			}
			int x = f.hulu.hulubrothers.get(idxmin).getPosition().GetPositionX();
			int y = f.hulu.hulubrothers.get(idxmin).getPosition().GetPositionY();	//Ѫ����͵ĺ�«������λ��
			if(idxmin == 5&&t.turn.ifdodge) {	//����Ŀ��������������������
				info += ("��������������"+t.turn.toString()+"�Ĺ�����\r\n");
				info += ("���޵�����Ч������ˡ�����\r\n\r\n");
				for(int i=0;i<f.ene.enemies.size();i++) {
					f.ene.enemies.get(i).ifdodge = false;
				}
				return info;
			}
			info = t.turn.attack(f,x,y);
			if(f.hulu.hulubrothers.get(5).currentHP == 0) {		//����������������
				for(int i=0;i<f.ene.enemies.size();i++) {
					f.ene.enemies.get(i).ifdodge = false;
				}
			}
		}
		return info;
	}
	
	public void CreaturesInit() {	//ս�������ʼ��
		HuluBrothers a = new HuluBrothers();
		Enemies b = new Enemies();
		b.add("Ы�Ӿ�",1000,120);
		f.addHuluBrothers(a);
		f.addEnemies(b);
	}
	
	public void JFrameInit() {	//���ڳ�ʼ��
		setSize(2800,1800);
		setLocation(200, 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel cp = (JPanel)getContentPane();
		getLayeredPane().add(f, new Integer(Integer.MIN_VALUE));
		cp.add(f);
	    setVisible(true);
	}

}
