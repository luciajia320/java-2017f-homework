import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;







@SuppressWarnings("unchecked")
public class Field extends JPanel {

    private final int OFFSET = 30;
    private final int SPACE = 20;
    private final int N = 16;
    private Ground ground;
    private boolean fromFile=false;
    private StringBuffer buffer=new StringBuffer();
    private StringBuffer myBuffer=new StringBuffer();
    private Vector<String> loadStrs=new Vector<String>();
    private Vector<String> loadStrs0=new Vector<String>();
    private Vector<String> battleStrs=new Vector<String>();
    private Map<Integer, Integer> deadSquare=new HashMap<Integer, Integer>();
    private Set<String> redSquare=new LinkedHashSet<String>();
    private int vectorIndex=0;
    private int vectorLen=0;
    




	public StringBuffer getMyBuffer() {
		return myBuffer;
	}
	public void setMyBuffer(StringBuffer myBuffer) {
		this.myBuffer = myBuffer;
	}
	public Map<Integer, Integer> getDeadSquare() {
		return deadSquare;
	}
	public void setDeadSquare(Map<Integer, Integer> deadSquare) {
		this.deadSquare = deadSquare;
	}
	public Vector<String> getBattleStrs() {
		return battleStrs;
	}
	public void setBattleStrs(Vector<String> battleStrs) {
		this.battleStrs = battleStrs;
	}
	public int getVectorIndex() {
		return vectorIndex;
	}
	public void addVectorIndex() {
		vectorIndex++;
	}

	public void setVectorIndex(int vectorIndex) {
		this.vectorIndex = vectorIndex;
	}

	public int getVectorLen() {
		return vectorLen;
	}

	public void setVectorLen(int vectorLen) {
		this.vectorLen = vectorLen;
	}

	public Vector<String> getLoadStrs() {
		return loadStrs;
	}

	public void setLoadStrs(Vector<String> loadStrs) {
		this.loadStrs = loadStrs;
	}

	
	@FuncAnno(func="fromfile is to indicate whether read data from file")
	public boolean isFromFile() {
		return fromFile;
	}

	public void setFromFile(boolean fromFile) {
		this.fromFile = fromFile;
	}

	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}


	private ArrayList tiles = new ArrayList();
    private ArrayList hulus = new ArrayList();
    private ArrayList louluos = new ArrayList();
    
    //private Player player;
    private Shejing shejing;
    private Player player;
    private Review review;
    private Xiezijing xiezijing;
    private Xiaolouluo[] xiaolouluos;
    private Grandpa grandpa;
    Mouseclicked mouseclicked=new Mouseclicked();
    private HuLuWa[] huLuWas;
    

    private int w = 350;
    private int h = 350;
    private int completed = 0;

    private String level =
    		 //      1234567890123456 
            		"................\n" + //1
            		"................\n" + //2
            		"................\n" + //3
            		"................\n" + //4
            		"................\n" + //5
            		"................\n" + //6
            		"................\n" + //7
            		"................\n" + //8
            		"................\n" + //9
            		"................\n" + //10
            		"................\n" + //11
            		"................\n" + //12
            		"................\n" + //13
            		"................\n" + //14
            		"................\n" + //15
            		"................\n" ; //16
    
    
    public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}


	private Position[][] positions;
    
	public Position[][] getPositions() {
	    return positions;
	}

    private void changsheInitLevel() {
    	 char[] s=level.toCharArray();
    	 s[2+4*17]='1';//红娃
    	 s[2+5*17]='2';//橙
    	 s[2+6*17]='3';//黄
    	 s[2+7*17]='4';//绿
    	 s[2+8*17]='5';//青
    	 s[2+9*17]='6';//蓝
    	 s[2+10*17]='7';//紫
    	 s[2+11*17]='8';//爷爷
    	 s[2+4*17+11]='c';//喽啰
    	 s[2+4*17+11+16]='c';
    	 s[2+4*17+11+16+16]='c';
    	 s[2+4*17+11+16+16+16]='b';//蝎子精
    	// s[2+4*17+11+16+16+16+4+17*7]='b';//蝎子精
    	 s[2+4*17+11+16+16+16+17]='a';//蛇精
    	 s[2+4*17+11+16+16+16+18]='c';
    	 s[2+4*17+11+16+16+16+18+18]='c';
    	 s[2+4*17+11+16+16+16+18+18+18]='c';
    	
    	
    	// System.out.println(s);
    	 level=String.valueOf(s);
    }
    class Mouseclicked extends MouseAdapter		//判断鼠标左击并通知棋盘和电脑，可以用来调试
	{
		public void mouseClicked(MouseEvent e)
		{
			int mouseX=e.getX();
			int mouseY=e.getY();
			System.out.println("x="+mouseX+" y="+mouseY);
		}
	}
    
    public Field(Ground ground) {
    	this.ground=ground;
    	addMouseListener(new Mouseclicked());
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.positions = new Position[16][16];
	    for (int i = 0; i < 16; i++) {
	        for(int j=0;j<16;j++){
	            this.positions[i][j] = new Position(i,j);
	        }
	    }
        huLuWas=new HuLuWa[7];
        xiaolouluos=new Xiaolouluo[6];
        player=new Player(0, 0, this);
        review=new Review(0, 0, this);
        changsheInitLevel();
        initWorld();
        
    }

    public void setCreature(Thing2D creature,int x,int y) {
	    creature.setPosition(this.positions[x][y]);
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
        int huluIndex=0,xiaolouluoIndex=0;
        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
           
                y += SPACE;
                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item == 'a') {
            	a = new Tile(x, y);
                tiles.add(a);
                shejing = new Shejing(x, y, this);
                x += SPACE;
            }else if (item == 'b') {
            	a = new Tile(x, y);
                tiles.add(a);
                xiezijing = new Xiezijing(x, y, this);
                x += SPACE;
            }else if (item == 'c') {
            	a = new Tile(x, y);
                tiles.add(a);
                xiaolouluos[xiaolouluoIndex]= new Xiaolouluo(x, y, this,xiaolouluoIndex+1);
                louluos.add(xiaolouluos[xiaolouluoIndex]);
                xiaolouluoIndex++;
                x += SPACE;
            }
            
            else if (item >= '1' && item <='7') {
            	 a = new Tile(x, y);
                 tiles.add(a);
                 huLuWas[huluIndex]= new HuLuWa(x, y, this,item-'0');
                 hulus.add(huLuWas[huluIndex]);
                 huluIndex++;
                
                 x += SPACE;
            }  else if (item == '8' ) {
           	 a = new Tile(x, y);
             tiles.add(a);
             grandpa=new Grandpa(x, y, this);
             x += SPACE;
        } 
            
            else if (item == ' ') {
                x += SPACE;
            }
            else {
			   x += SPACE;
			}

        }
        
        setCreature(huLuWas[0], 2, 4);
      
        setCreature(huLuWas[1], 2, 5);
        setCreature(huLuWas[2], 2, 6);
        setCreature(huLuWas[3], 2, 7);
        setCreature(huLuWas[4], 2, 8);
        setCreature(huLuWas[5], 2, 9);
        setCreature(huLuWas[6], 2, 10);
        setCreature(grandpa, 2, 11);
        setCreature(xiaolouluos[0], 13, 4);
        setCreature(xiaolouluos[1], 12, 5);
        setCreature(xiaolouluos[2], 11, 6);
        setCreature(xiezijing, 10, 7);
     
        setCreature(shejing, 10, 8);
        setCreature(xiaolouluos[3], 11, 8);
        setCreature(xiaolouluos[4], 12, 9);
        setCreature(xiaolouluos[5], 13, 10);
       // System.out.println("W is "+w+" H is "+h);
        System.out.println("初始化时候的holder分布(1代表此处有占)");
        for(int i=0;i<16;i++) {
        	for(int j=0;j<16;j++) {
        		if(positions[j][i].getHolder()!=null)
        			System.out.print("1 ");
        		 
        		else
        			System.out.print("0 ");
        	}
        	
        	System.out.println();
        }
        

    }

    public Shejing getShejing() {
		return shejing;
	}

	public Xiezijing getXiezijing() {
		return xiezijing;
	}

	public Xiaolouluo[] getXiaolouluos() {
		return xiaolouluos;
	}

	public Grandpa getGrandpa() {
		return grandpa;
	}

	public HuLuWa[] getHuLuWas() {
		return huLuWas;
	}

	public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);

      
        world.add(shejing);
        world.addAll(hulus);
        world.addAll(louluos);
        world.add(xiezijing);
        world.add(grandpa);
        
        
        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Tile ) {
                
                g.drawImage(item.getImage(), item.x() , item.y() , this);
            } 
            else 
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
              
            
           if(completed>0&&fromFile==false) {
           	try {
       		
       		 BufferedWriter bw = new BufferedWriter(new FileWriter("outPut2.txt"));
       		 bw.write(myBuffer.toString());
       		 bw.close();
	                    
				
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
           }
            
            if (completed==1) {
            	
                g.setColor((Color.RED));
                g.drawString("HuLus Win", 170, 20);
             
                
            }
            else if (completed==2) {
              
            	 g.setColor((Color.RED));
                g.drawString("Monsters Win", 170, 20);
             
            }
            

        }
    }

	private void makeAllDead() {
		
		getGrandpa().setAlive(false);
		getShejing().setAlive(false);
		getXiezijing().setAlive(false);
		for(int i=0;i<7;i++) {
			getHuLuWas()[i].setAlive(false);
		}
		for(int i=0;i<6;i++) {
			getXiaolouluos()[i].setAlive(false);
		}
	}
	private int judgeWin() {
		
		
		
		if(getGrandpa().isAlive()==false&&getHuLuWas()[0].isAlive()==false&&
				getHuLuWas()[1].isAlive()==false&&getHuLuWas()[2].isAlive()==false&&
				getHuLuWas()[3].isAlive()==false&&getHuLuWas()[4].isAlive()==false&&
				getHuLuWas()[5].isAlive()==false&&getHuLuWas()[6].isAlive()==false)
			return 2;//Monsters win
		if(getShejing().isAlive()==false&&getXiezijing().isAlive()==false&&
				getXiaolouluos()[0].isAlive()==false&&getXiaolouluos()[1].isAlive()==false&&
				getXiaolouluos()[2].isAlive()==false&&getXiaolouluos()[3].isAlive()==false&&
				getXiaolouluos()[4].isAlive()==false&&getXiaolouluos()[5].isAlive()==false)
			return 1;//Hulus win
		return 0;
		
	}
    @Override
    public void paint(Graphics g) {
    	
    	completed=judgeWin();
        super.paint(g);
        buildWorld(g);
        paintChessMap(g); 
        
    }

    private void paintChessMap(Graphics g){
    	g.setColor(Color.BLACK);
    	for(int j=0;j<=N;j++){							//画线
        	g.drawLine(30,30+j*20,350,30+j*20);
        	g.drawLine(30+j*20,30,30+j*20,350);
        }
    	
    	g.setColor(Color.YELLOW);
    	
    	for(int i=0;i<7;i++) {
    		
    		if(huLuWas[i].isAlive()==false) {
    			redSquare.add(( Integer.toString(huLuWas[i].x())+" "+Integer.toString(huLuWas[i].y())));
    		}
    	}
    	
    	for(int i=0;i<6;i++) {
    		
    		if(xiaolouluos[i].isAlive()==false) {
    			redSquare.add(( Integer.toString(xiaolouluos[i].x())+" "+Integer.toString(xiaolouluos[i].y())));
    		}
    	}
    	
    	if(shejing.isAlive()==false) {
    		redSquare.add(( Integer.toString(shejing.x())+" "+Integer.toString(shejing.y())));
    	}
    	
    	if(grandpa.isAlive()==false) {
    		redSquare.add(( Integer.toString(grandpa.x())+" "+Integer.toString(grandpa.y())));
    	}
    	
    	if(xiezijing.isAlive()==false) {
    		redSquare.add(( Integer.toString(xiezijing.x())+" "+Integer.toString(xiezijing.y())));
    	}
    	
    	
    	for (String s:redSquare) {  
    	  String tmp[]=s.split(" ");
    	  int x=Integer.parseInt(tmp[0]);
    	  int y=Integer.parseInt(tmp[1]);
    	  g.drawLine(x, y,x+20 , y); 
    	  g.drawLine(x, y,x , y+20); 
    	  g.drawLine(x, y+20,x+20 , y+20); 
    	  g.drawLine(x+20, y,x+20 , y+20); 
    	  
    	}  
    	
    }
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed!=0) {
                return;
            }


            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {


                //player.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {


                //player.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {


                //player.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {


                //player.move(0, SPACE);

            } else if (key == KeyEvent.VK_SPACE) {
            	
                new Thread(player).start();
                new Thread(shejing).start();
                new Thread(xiezijing).start();
                new Thread(grandpa).start();
                for(int i=0;i<7;i++) {
                	new Thread(huLuWas[i]).start();
                }
                for(int i=0;i<6;i++) {
                	new Thread(xiaolouluos[i]).start();}
            //	System.out.println("space");
                
                
                

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            }
            else if (key == KeyEvent.VK_L) {
                fromFile=true;
                
                
                try {
                	 JFileChooser fileChooser = new JFileChooser();

                	  // 设置当前目录
                	  fileChooser.setCurrentDirectory(new File("."));
                	  fileChooser.setAcceptAllFileFilterUsed(false);

                	  final String[][] fileENames = {  { ".txt", "文本文件(*.txt)" }
                			 
                	           };
                	  
                	  // 显示所有文件
                	  fileChooser.addChoosableFileFilter(new FileFilter() {

                	   public boolean accept(File file) {

                	    return true;
                	   }

                	   public String getDescription() {

                	    return "所有文件(*.*)";
                	   }
                	  });
                	  
                	  // 循环添加需要显示的文件
                	  for (final String[] fileEName : fileENames) {
                	   
                	   fileChooser.setFileFilter(new FileFilter() {
                	 
                	    public boolean accept(File file) { 
                	 
                	     if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
                	 
                	      return true;
                	     }
                	 
                	     return false;
                	    }
                	 
                	    public String getDescription() {
                	 
                	     return fileEName[1];
                	    }
                	 
                	   });
                	  }
                	  
                	  fileChooser.showDialog(null, null);
                	  File f = fileChooser.getSelectedFile();
                	  System.out.println(f.getPath());
                	  
                   FileReader fr = new FileReader(f.getPath());
       	           BufferedReader br = new BufferedReader(fr);
                    
       	             String str;
       	              while ((str = br.readLine()) != null) {                
       	            	
       	            	  loadStrs.add(str);
       	            	  
                  }
       	              
       	              vectorLen=loadStrs.size();
       	              
      	             
      	             System.out.println("读取了"+vectorLen+"行");
      	          
       	             fr.close();
       	             br.close();
       	             new Thread(review).start();
       	             
       	        } catch (IOException ex) {
       	             ex.printStackTrace();
       	         }           
                
            }

            repaint();
        }
    }


    public void restartLevel() {

        tiles.clear();
        hulus.clear();
        louluos.clear();
        initWorld();
        if (completed!=0) {
            completed = 0;
        }
    }
}