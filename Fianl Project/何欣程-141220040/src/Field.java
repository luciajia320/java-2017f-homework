
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer; 
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

enum Role{GROUND,HUMAN,SNAKE,MONSTER,SCORPION,NOTCREATURE}
public class Field extends JPanel{
	
	private boolean if_started =false;
	private final int OFFSET = 60;
	private final int SPACE = 60;
	private ArrayList tiles = new ArrayList();
	private ArrayList goodman=new ArrayList();
	private ArrayList badman=new ArrayList();
	private boolean goodman_live=true,badman_live=true;
	private String win_log="",lose_log="",all_log="";
	private int w = 0;
    private int h = 0;
    private String level =
            		".....................\n" +
                    ".....................\n" +
                    ".....................\n" +
                    ".....................\n" +
                    ".....................\n" +
                    ".....................\n" +
                    ".....................\n" +
                    ".....................\n";
	
	public Field(){
		addKeyListener(new BankAdapter());
		setFocusable(true);
		initWorld();
		initPlayer();
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
        for (int i = 0; i < level.length(); i++) {
            char item = level.charAt(i);
            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }
                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y,Role.NOTCREATURE);
                tiles.add(a);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }
            h = y;
        }
    }
    
    public final void initPlayer(){
    	Player b1=new Player(OFFSET+2*SPACE,OFFSET+2*SPACE,this,1,Role.GROUND);
    	goodman.add(b1);
    	Player b2=new Player(OFFSET+3*SPACE,OFFSET+SPACE,this,2,Role.GROUND);
    	goodman.add(b2);
    	Player b3=new Player(OFFSET+3*SPACE,OFFSET+3*SPACE,this,3,Role.GROUND);
    	goodman.add(b3);
    	Player b4=new Player(OFFSET+4*SPACE,OFFSET,this,4,Role.GROUND);
    	goodman.add(b4);
    	Player b5=new Player(OFFSET+4*SPACE,OFFSET+4*SPACE,this,5,Role.GROUND);
    	goodman.add(b5);
    	Player b6=new Player(OFFSET+5*SPACE,OFFSET+SPACE,this,6,Role.GROUND);
    	goodman.add(b6);
    	Player b7=new Player(OFFSET+5*SPACE,OFFSET+3*SPACE,this,7,Role.GROUND);
    	goodman.add(b7);  	
    	Player human=new Player(OFFSET+6*SPACE,OFFSET+2*SPACE,this,8,Role.HUMAN);
    	goodman.add(human);
    	Player snake=new Player(OFFSET+15*SPACE,OFFSET+2*SPACE,this,9,Role.SNAKE);
    	badman.add(snake);
    	Player m0=new Player(OFFSET+16*SPACE,OFFSET+SPACE,this,10,Role.SCORPION);
    	badman.add(m0);
    	Player m1=new Player(OFFSET+17*SPACE,OFFSET,this,11,Role.MONSTER);
    	badman.add(m1);
    	Player m2=new Player(OFFSET+16*SPACE,OFFSET+3*SPACE,this,11,Role.MONSTER);
    	badman.add(m2);
    	Player m3=new Player(OFFSET+17*SPACE,OFFSET+4*SPACE,this,11,Role.MONSTER);
    	badman.add(m3);
    }
    
    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);
        world.addAll(goodman);
        world.addAll(badman);
        
        for (int i = 0; i < world.size(); i++) {
            Thing2D item = (Thing2D) world.get(i);
            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }
    

    ActionListener taskAction=new ActionListener( ){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			start();	
		}
    };
    
  
    
    public void start(){
    	
    	if(!check_live()){
    		if(goodman_live==false)
    			System.out.println("Good humans are all dead!");
    		else
    			System.out.println("Bad humans are all dead!");
    		System.out.println();
    		System.out.println("War logs are as follows:");
    		System.out.println(all_log);
    		
    		saveRecordInFile(win_log,"./log/win_log.txt");
    		saveRecordInFile(lose_log,"./log/lose_log.txt");
    		saveRecordInFile(all_log,"./log/all_log.txt");
    		System.exit(0);}

    	for(int i=0;i<goodman.size();i++){
    		Player temp=(Player) goodman.get(i);
    		if(temp.is_live()){
    			temp.setX(temp.x()+SPACE);
    			if(temp.x()>OFFSET*20)
    				temp.setX(OFFSET);
    			check_enemy(temp, badman,i);
    		}
    	}
    	for(int j=0;j<badman.size();j++){
    		Player temp2=(Player) badman.get(j);
    		if(temp2.is_live()){
    			temp2.setX(temp2.x()-SPACE);
    			if(temp2.x()<OFFSET)
    				temp2.setX(OFFSET*20);
    			check_enemy(temp2, goodman,j);
    		}
    	}
    	
    	repaint();
    }
    
	public void check_enemy(Player x,ArrayList<Player> y,int id){
		for(int i=0;i<y.size();i++){
			Player temp=(Player)y.get(i);
			if(check_len(x.x(),temp.x(),x.y(),temp.y())&&temp.is_live()){
				if(x.war(temp))
				{
					System.out.println(x.role().toString()+" "+id+" at "+x.x()+","+x.y()+":win!");
					win_log+=x.role().toString()+" "+id+" "+x.x()+" "+x.y()+"\n";
					all_log+=x.role().toString()+" "+id+" "+x.x()+" "+x.y()+" win"+"\n";
					System.out.println(temp.role().toString()+" "+i+" at "+temp.x()+","+temp.y()+":lose!");
					lose_log+=temp.role().toString()+" "+i+" "+temp.x()+" "+temp.y()+"\n";
					all_log+=temp.role().toString()+" "+i+" "+temp.x()+" "+temp.y()+" lose"+"\n";
					temp.dead();
				}
				else{
					System.out.println(x.role().toString()+" "+id+" at "+x.x()+","+x.y()+":lose!");
					lose_log+=x.role().toString()+" "+id+" "+x.x()+" "+x.y()+"\n";
					all_log+=x.role().toString()+" "+id+" "+x.x()+" "+x.y()+" lose"+"\n";
					System.out.println(temp.role().toString()+" "+i+" at "+temp.x()+","+temp.y()+":win!");
					win_log+=temp.role().toString()+" "+i+" "+temp.x()+" "+temp.y()+"\n";
					all_log+=temp.role().toString()+" "+i+" "+temp.x()+" "+temp.y()+" win"+"\n";
					x.dead();
				}
			}
			
		}
		
	}
	
	public boolean check_len(int x1,int x2,int y1,int y2){
		return (Math.abs(x1-x2)<=OFFSET&&Math.abs(y1-y2)<=OFFSET);
	}
    
    public boolean check_live(){
    	goodman_live=false;
    	badman_live=false;
    	for(int i=0;i<goodman.size();i++){
    		Player temp=(Player) goodman.get(i);
    		goodman_live=goodman_live||temp.is_live();
    	}
    	for(int j=0;j<badman.size();j++){
    		Player temp2=(Player) badman.get(j);
    		badman_live=badman_live||temp2.is_live();
    	}
    	return goodman_live&&badman_live;
    }

    public void saveRecordInFile(String str,String path) {
        File record = new File(path);//记录结果文件
        try {
            if (!record.exists()) {

                File dir = new File(record.getParent());
                dir.mkdirs();
                record.createNewFile();
            }
            FileWriter writer = null;
            try {
                // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                writer = new FileWriter(record, false);
                writer.write(str);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Can not save logs!");
        }
    }
	
	

    public void restartLevel() {

        tiles.clear();
        goodman.clear();
        badman.clear();
        win_log=lose_log="";
        initWorld();
        initPlayer();
        if (if_started) {
            if_started = false;
        }
        repaint();
    }



    class BankAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (if_started) {
				return;
			}
			int key = e.getKeyCode();
			if(key==KeyEvent.VK_SPACE)
			{
				System.out.println("Game Start!");
				if_started=false;
				new Timer(1000,taskAction).start();
			}else if (key == KeyEvent.VK_R) {
				System.out.println("Game Restart!");
                restartLevel();
            }else if (key == KeyEvent.VK_Q){
            	System.out.println("Game Finished!");
            	System.exit(0);
            }else if (key == KeyEvent.VK_L){
            	restartLevel();
            	read_log();
            }
		}
	}
    
    public void read_log(){
    	JFileChooser fd = new JFileChooser();  
    	//fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
    	fd.showOpenDialog(null);  
    	File file = fd.getSelectedFile();  
    	BufferedReader reader=null;  
        String temp=null;  
        int line=1;  
        try{  
                reader=new BufferedReader(new FileReader(file));  
                while((temp=reader.readLine())!=null){  
                   // System.out.println("第"+line+"行:"+temp);

                    String string=getNewString(temp);//调用分割方法
                    System.out.println(string);
                    line++;  
                }  
        }  
        catch(Exception e){  
            e.printStackTrace();  
        } 
        finally{  
            if(reader!=null){  
                try{  
                    reader.close();  
                }  
                catch(Exception e){  
                    e.printStackTrace();  
                }  
            }  
        }  
    	if(file != null){System.out.println("Open file succeed!");} 
    }
    
    public String getNewString(String fileName){
        String str1="";
        String str2="";
        String str3="";
        String str4="";
        String str5="";
        String []arrayStr=fileName.split("\\s+");
        str1="\n\t\t"+arrayStr[0];
        str2="\t"+arrayStr[1];
        str3="\t"+arrayStr[2];
        str4="\t"+arrayStr[3];
        str5="\t"+arrayStr[4];
      /*  ActionListener taskAction2=new ActionListener( ){

    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			reload();	
    		}
        };*/
        reload(arrayStr[0],arrayStr[1],arrayStr[2],arrayStr[3],arrayStr[4]);
        return str1+str2+str3+str4+str5;
    }
    
    public void reload(String s1,String s2,String s3,String s4,String s5){
    	
    	if(is_bad(s1)){

    		Player tp=(Player)badman.get(Integer.parseInt(s2));
    		tp.setX(Integer.parseInt(s3));
    		tp.setY(Integer.parseInt(s4));
    		if(s5.equals("lose")){

    			tp.dead();}
    	}
    	if(is_good(s1)){
    		Player tp2=(Player)goodman.get(Integer.parseInt(s2));
    		tp2.setX(Integer.parseInt(s3));
    		tp2.setY(Integer.parseInt(s4));
    		if(s5.equals("lose"))
    			tp2.dead();
    		
    	}
    	repaint();
    }
    
    public boolean is_bad(String s1){
    	//System.out.println(222+Role.SNAKE.toString());
    	//System.out.println(s1.equals(Role.MONSTER.toString())||s1.equals(Role.SCORPION.toString())||s1.equals(Role.SNAKE.toString()));
    	return s1.equals(Role.MONSTER.toString())||s1.equals(Role.SCORPION.toString())||s1.equals(Role.SNAKE.toString());
    }
    
    public boolean is_good(String s){
    	return s.equals(Role.GROUND.toString())||s.equals(Role.HUMAN.toString());
    }
}


