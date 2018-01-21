package nju.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Field extends JPanel {

    final int OFFSET = 80;

    private Image background;

    private ArrayList<Player> players= new ArrayList<Player>();
    private int w = 0;
    private int h = 0;
    private boolean completed = false;
    private Record record;
    private boolean fighting = false;
    private Replayer replayer;
    FileChooser fileChooser;
    public Field(FileChooser fileChooser) {
    	this.fileChooser=fileChooser;
        addKeyListener(new TAdapter());
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
    	record=new Record();
    	replayer=new Replayer(this,record);
        URL loc = this.getClass().getClassLoader().getResource("background.png");
        ImageIcon iia = new ImageIcon(loc);
        this.background = iia.getImage();
        w=1200;
        h=640;
   
        for(int i=0;i<7;i++){
        	players.add(new Player("葫芦娃"+(i+1),0,i*OFFSET, this,"huluwa"+(i+1)+".png",0,23-i));
        }
        Player grandpa=new Player("老爷爷",0,7*OFFSET,this,"grandpa.png",0,12);
        players.add(grandpa);
        Player shejing=new Player("蛇精",11*OFFSET,3*OFFSET,this,"shejing.png",1,18);
        shejing.setDirLeft();
        players.add(shejing);
        Player xiezijing=new Player("蝎子精",11*OFFSET,4*OFFSET,this,"xiezijing.png",1,19);
        xiezijing.setDirLeft();
        players.add(xiezijing);
        for(int i=0;i<6;i++){
        	Player temp=null;
        	if(i<3)
        		temp=new Player("喽啰"+(i+1),(14-i)*OFFSET,i*OFFSET,this,"louluo.png",1,15);
        	else
        		temp=new Player("喽啰"+(i+1),(9+i)*OFFSET,(i+2)*OFFSET,this,"louluo.png",1,15);
        	temp.setDirLeft();
        	players.add(temp);
        	
        }
    }
    public Player findByName(String name){
    	
        for (int i = 0; i < players.size(); i++) {
        	Player temp = (Player)players.get(i);
        	if(temp.getName().equals(name))
        		return temp;
        }
        return null;
    }
    public void perform(String str){
    	if(str.indexOf("移动")!=-1){
    	    
			String name=str.substring(0, str.indexOf("从"));

			int start=str.indexOf('(',str.indexOf('('));
			int end=str.indexOf(')',str.indexOf(')'));
			String temp=str.substring(start+1,end);
			String []cord=new String[2];
			cord=temp.split(",");
			int nx=Integer.valueOf(cord[0]),ny=Integer.valueOf(cord[1]);
			Player player=findByName(name);	
			System.out.println("移动中..........");
			System.out.println(name);
			System.out.println(player.x()+" "+player.y()+"->"+nx+" "+ny);
			player.move((nx-player.x())/this.OFFSET, (ny-player.y())/this.OFFSET,true);
			
			repaint();
		
		}
		else if(str.indexOf("战斗")!=-1){
			
			String temp=str.substring(0,str.indexOf("相遇"));
			String []name=temp.split("和");
			System.out.println("战斗中............");
			System.out.println(name[0]);
			System.out.println(name[1]);
			findByName(name[0]).attack();
			findByName(name[1]).attack();
			repaint();
		
		}
		else if(str.indexOf("阵亡")!=-1){
			String []name=str.split(",");
			name[0]=name[0].substring(0, name[0].indexOf("阵亡"));
			name[1]=name[1].substring(0, name[1].indexOf("胜利"));
			System.out.println("战斗结束中............");
			System.out.println(name[0]+"阵亡");
			System.out.println(name[1]+"胜利");
			findByName(name[0]).die();
			findByName(name[1]).normal();
			repaint();
		
		}
    }

    public synchronized boolean available(int x,int y){
        for (int i = 0; i < players.size(); i++) {
       	 	Player temp = (Player)players.get(i);
       	 	if(temp.isAlive()&&temp.x()==x&&temp.y()==y)
       	 		return false;
        }
        return true;
    }
    public synchronized boolean nearby(int x1,int y1,int x2,int y2,DIR dir){
    	boolean exist=false;
    	switch(dir){
    	case UP:if(x1==x2&&y1+this.OFFSET==y2)
    		exist=true;break;
    	case DOWN:if(x1==x2&&y1-this.OFFSET==y2)
    		exist=true;break;
    	case LEFT:if(x1-this.OFFSET==x2&&y1==y2)
    		exist=true;break;
    	case RIGHT:if(x1+this.OFFSET==x2&&y1==y2)
    		exist=true;break;
    	
    	}
    	
    	
    	
    	
    	return exist;
    }
    public synchronized boolean writeRecord(String str){
    	record.record(str);
    	return true;
    }
    public  boolean encounter(Player player){


        for (int i = 0; i < players.size(); i++) {
        	 Player item=players.get(i);
             if (item.isAlive()&&item.getTeam()!=player.getTeam()) {
            	 if(!item.isFighting()&&!player.isFighting()&&nearby(player.x(),player.y(),item.x(),item.y(),player.getDir())){
            		 item.attack();
            		 player.attack();
            		 this.writeRecord(player.getName()+"和"+item.getName()+"相遇,发生战斗\n");
            		 item.sleep(2000);
            		 player.sleep(2000);
            		 repaint();

            		 int win_odd=50;
            		 win_odd+=item.getStrength()-player.getStrength();
            		 
            		 Random rand=new Random();
            		 int temp=rand.nextInt(100);
            		 
            		 if(temp>=0&&temp<=win_odd){
            			 this.writeRecord(player.getName()+"阵亡了,"+item.getName()+"胜利了\n");
            			 player.die();
            			 item.normal();
            			 item.sleep(2000);
            			 //this.search(item);
            		 }
            		 else{
            			 this.writeRecord(item.getName()+"阵亡了,"+player.getName()+"胜利了\n");
            			 item.die();
            			 player.normal();
            			 player.sleep(2000);
            			 //this.search(player);
            		 }
            		 //this.completed=true;
            		 return true;
            	 }
             }
        }
        return false;
    }
    public boolean search(Player player){
    	boolean find=false;
        for (int i = 0; i < players.size(); i++) {
       	 	Player temp = (Player)players.get(i);
       	 	if(temp.isAlive()&&temp.getTeam()!=player.getTeam()){
       	 		if(temp.y()>player.y())
       	 			player.setDirUp();
       	 		else if(temp.y()<player.y())
       	 			player.setDirDown();
       	 		else {
       	 			if(temp.x()<player.x())
       	 				player.setDirLeft();
       	 			else if(temp.x()>player.x())
       	 				player.setDirRight();
       	 		}
       	 	find=true;break;
       	 	}
        }
        if(find==false){
        	this.completed=true;
        	this.fighting=false;
        }
        return find;
    }
    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(this.background, 0 , 0 , this);

        for (int i = 0; i < players.size(); i++) {

            Player item = players.get(i);
            g.drawImage(item.getImage(), item.x(), item.y(), this);
            

            if (completed) {
            	
            	
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {
    	ExecutorService  exec=null;
        @Override
        public void keyPressed(KeyEvent e) {

//            if (completed) {
//                return;
//            }


            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {


                //player.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_L) {
            	if(exec!=null)
            		exec.shutdownNow();
            	if(fighting==false){
            		restartLevel();
            		String filename=fileChooser.show();
	            	//String filename =JOptionPane.showInputDialog("请输入读取的文件名：");
	            	if(filename!=null){
		            	if(record.isOpened())
		            		record.close();
		            	record.open(filename);
		            	new Thread(replayer).start();
		            	}
            	}
            	//System.out.println(filename);
                //player.move(0, SPACE);

            } else if (key == KeyEvent.VK_SPACE) {

                //new Thread(player).start();
                //new Thread(enemy).start();
            	fighting=true;
            	exec=Executors.newCachedThreadPool();
            	for(Player player:players)
            		exec.execute(player);

            } else if (key == KeyEvent.VK_S) {
            	if(completed){
	            	String filename = JOptionPane.showInputDialog("请输入保存的文件名：");
	            	if(filename==null)
	            		filename="huluwa.txt";
	            	record.open(filename);
	            	record.writeRecord();
	            	record.close();
            	}
            } else if (key == KeyEvent.VK_R) {
            	if(exec!=null)
            		exec.shutdownNow();
                restartLevel();
            }

            repaint();
        }
    }


    public void restartLevel() {

        players.clear();
        initWorld();
        if (completed) {
            completed = false;
            fighting=false;
        }
    }
}