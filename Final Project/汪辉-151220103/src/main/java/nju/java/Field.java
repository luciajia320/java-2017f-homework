package main.java.nju.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class Field extends JPanel implements Runnable{

    private final int OFFSET = 0;
    private final int SPACE = 90;
	static private int count=0;

    private ArrayList<Position> tiles = new ArrayList<Position>();
    private ArrayList<HuluBro> playList=new ArrayList<HuluBro>();
    private ArrayList<BadBoy> badboy=new ArrayList<BadBoy>();
    private ArrayList<Bullet> bullet=new ArrayList<Bullet>();
    private HuluBro player;
    private int CurX=0;
    private int CurY=0;
    private int CurNum=0;
    private String filename;
    private boolean is_review=false;

    private int w = 0;
    private int h = 0;
    private String Result;
    private boolean is_start=false;
    private boolean completed = false;
    private String[] Str= {
    		".................9..\n"+
			".................9..\n"+
			".................9..\n"+
			".................9..\n"+
			".................9..\n"+
			".................8..\n"+
			".................9..\n"+
			".................9..\n"+
			".................9..\n"+
			".................9..\n"+
			".................9..\n",
					"............9.....9.\n"+
					".............9...9..\n"+
					"..............9.9...\n"+
					"...............8....\n"+
					"....................\n"+
					"....................\n"+
					"............9.....9.\n"+
					".............9...9..\n"+
					"..............9.9...\n"+
					"...............8....\n"+
					"....................\n",
							"....................\n"+
							"..................9.\n"+
							".................9..\n"+
							"................9...\n"+
							"...............9....\n"+
							"..............9.....\n"+
							".............8......\n"+
							"............9.......\n"+
							"...........9........\n"+
							"..........9.........\n"+
							".........9..........\n",
									".................9..\n"+
									"..................9.\n"+
									".................9..\n"+
									"..................9.\n"+
									".................9..\n"+
									"..................9.\n"+
									".................98.\n"+
									"..................9.\n"+
									".................9..\n"+
									"..................9.\n"+
									".................9..\n",
											"....................\n"+
											"....................\n"+
											"....................\n"+
											".................9..\n"+
											"................99..\n"+
											"...............999..\n"+
											".............999998.\n"+
											"...............999..\n"+
											"................99..\n"+
											".................9..\n"+
											"....................\n",
													"....................\n"+
													"....................\n"+
													"...............9....\n"+
													"..............9.9...\n"+
													".............9...9..\n"+
													"............9..8..9.\n"+
													".............9...9..\n"+
													"..............9.9...\n"+
													"...............9....\n"+
													"....................\n"+
													"....................\n",
															"....................\n"+
															"...............99...\n"+
															"..............999...\n"+
															".............999....\n"+
															"...........9999.....\n"+
															"...........99998....\n"+
															"...........9999.....\n"+
															".............999....\n"+
															"..............999...\n"+
															"................99..\n"+
															"....................\n",
																	"....................\n"+
																	"....................\n"+
																	"................9...\n"+
																	"...............9....\n"+
																	"..............9.....\n"+
																	"............9998999.\n"+
																	"..............9.....\n"+
																	"...............9....\n"+
																	"................9...\n"+
																	"....................\n"+
																	"....................\n"};
    private String level =Str[0];

    public Field() {
    	Random rand = new Random();
    	level=Str[rand.nextInt(8)];
        addKeyListener(new TAdapter());
        addMouseListener(new MouthAdapter());
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

        Position a;


        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '.') {
                a = new Position(x, y);
                tiles.add(a);
                x += SPACE;
            }else if (item == '*') {
                a = new Position(x, y);
                tiles.add(a);
                Bullet b=new Bullet(0,x,y, this);
                bullet.add(b);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            } else if (item == '0') {
            	a = new Position(x, y);
                HuluBro b=new HuluBro(0,10,1,x,y, this);
                a.SetPosition(b);
                tiles.add(a);
            	playList.add(b);
                x += SPACE;
            }else if (item == '1') {
            	a = new Position(x, y);
            	HuluBro b=new HuluBro(1,10,1,x,y, this);
                a.SetPosition(b);
                tiles.add(a);
             	playList.add(b);
                x += SPACE;
            } else if (item == '2') {
            	a = new Position(x, y);
            	HuluBro b=new HuluBro(2,10,1,x,y, this);
                a.SetPosition(b);
                tiles.add(a);
             	playList.add(b);
                x += SPACE;
            }else if (item == '3') {
            	a = new Position(x, y);
            	HuluBro b=new HuluBro(3,10,1,x,y, this);
                a.SetPosition(b);
                tiles.add(a);
             	playList.add(b);
                x += SPACE;
            } else if (item == '4') {
            	a = new Position(x, y);
            	HuluBro b=new HuluBro(4,10,1,x,y, this);
                a.SetPosition(b);
                tiles.add(a);
             	playList.add(b);
                x += SPACE;
            } else if (item == '5') {
            	a = new Position(x, y);
            	HuluBro b=new HuluBro(5,10,1,x,y, this);
                a.SetPosition(b);
                tiles.add(a);
             	playList.add(b);
                x += SPACE;
            } else if (item == '6') {
            	a = new Position(x, y);
            	 HuluBro b=new HuluBro(6,10,1,x,y, this);
                 a.SetPosition(b);
                 tiles.add(a);
             	 playList.add(b);
                x += SPACE;
            } else if (item == '7') {
            	a = new Position(x, y);
            	HuluBro b=new HuluBro(7,10,1,x,y, this);
                a.SetPosition(b);
                tiles.add(a);
             	playList.add(b);
                x += SPACE;
            }else if (item == '8') {
            	a = new Position(x, y);
            	BadBoy b=new BadBoy(8,10,1,x,y,this);
            	a.SetPosition(b);
                badboy.add(b);
                tiles.add(a);
                x += SPACE;
            } else if (item == '9') {
            	a = new Position(x, y);
            	BadBoy b=new BadBoy(9,10,1,x,y,this);
            	a.SetPosition(b);
                badboy.add(b);
                tiles.add(a);
                x += SPACE;
            }

            h = y;
        }
        Random rand=new Random();
        int i=rand.nextInt(8);
        player=new HuluBro(i,10,1,0+ OFFSET,0+OFFSET, this);
        this.Getfromxy(0+OFFSET, 0+OFFSET).SetPosition(player);
        playList.add(player);
        Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");//可以方便地修改日期格式 

		String time = dateFormat.format( now ); 
		filename="E:\\Javaworkstation\\HuluBro2\\src\\Logfile\\"+time+".txt";
        
    }
    public File ChooseFile() {
    	 // 创建文件选择器
		  JFileChooser fileChooser = new JFileChooser();
		  // 设置当前目录
		  fileChooser.setCurrentDirectory(new File(".//src//Logfile"));
		  fileChooser.setAcceptAllFileFilterUsed(false);
		  final String[][] fileENames = { { ".java", "JAVA源程序 文件(*.java)" },{".txt","文本文件"}};
		  
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
		   
		   fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
		 
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
		  File file=fileChooser.getSelectedFile(); 
		  return file;
    }
    public void Review(){
    	is_review=true;
    	tiles.clear();
    	for(int i=0;i<11;i++) {
    		for(int j=0;j<20;j++) {
    			tiles.add(new Position(j*SPACE,i*SPACE));
    		}
    	}
    	new Thread(this).start();
    }
    public boolean is_completed() {
    	return completed;
    }
    public void ResetWorld(String le) {
		// TODO Auto-generated method stub
    	 int x = OFFSET;
         int y = OFFSET;

         Position a;
        // tiles.clear();
         badboy.clear();
         playList.clear();
         bullet.clear();

         for (int i = 0; i < le.length(); i++) {

             char item = le.charAt(i);

             if (item == '\n') {
                 y += SPACE;
                 if (this.w < x) {
                     this.w = x;
                 }

                 x = OFFSET;
             } else if (item == '.') {
//                 a = new Position(x, y);
//                tiles.add(a);
                 x += SPACE;
             } else if (item == ' ') {
                 x += SPACE;
             } else if (item == '0') {
             	a = new Position(x, y);
                 player=new HuluBro(0,10,1,x,y, this);
                // a.SetPosition(b);
                // tiles.add(a);
             	playList.add(player);
                 x += SPACE;
             }else if (item == '*') {
              	a = new Position(x, y);
                bullet.add(new Bullet(0,x,y,this));
               // a.SetPosition(b);
              //  tiles.add(a);
            	//playList.add(b);
              //  playList.add(player);
                x += SPACE;
            }
             else if (item == '@') {
               	a = new Position(x, y);
                 bullet.add(new Bullet(5,x,y,this));
                // a.SetPosition(b);
               //  tiles.add(a);
             	//playList.add(b);
                // playList.add(player);
                 x += SPACE;
             }
             else if (item == '$') {
               	a = new Position(x, y);
                 bullet.add(new Bullet(4,x,y,this));
                // a.SetPosition(b);
               //  tiles.add(a);
             	//playList.add(b);
                 //playList.add(player);
                 x += SPACE;
             }else if (item == '1') {
             	a = new Position(x, y);
             	player=new HuluBro(1,10,1,x,y, this);
                 //a.SetPosition(b);
                 //tiles.add(a);
              	//playList.add(b);
             	playList.add(player);
                 x += SPACE;
             } else if (item == '2') {
             	a = new Position(x, y);
             	player=new HuluBro(2,10,1,x,y, this);
                 //a.SetPosition(b);
                // tiles.add(a);
              	//playList.add(b);
             	playList.add(player);
                 x += SPACE;
             }else if (item == '3') {
             	a = new Position(x, y);
             	player=new HuluBro(3,10,1,x,y, this);
              //   a.SetPosition(b);
                // tiles.add(a);
              //	playList.add(b);
             	playList.add(player);
                 x += SPACE;
             } else if (item == '4') {
             	a = new Position(x, y);
             	player=new HuluBro(4,10,1,x,y, this);
                 //a.SetPosition(b);
               //  tiles.add(a);
              	//playList.add(b);
             	playList.add(player);
                 x += SPACE;
             } else if (item == '5') {
             	a = new Position(x, y);
             	player=new HuluBro(5,10,1,x,y, this);
                 //a.SetPosition(b);
              //   tiles.add(a);
              	//playList.add(b);
             	playList.add(player);
                 x += SPACE;
             } else if (item == '6') {
             	a = new Position(x, y);
             	player=new HuluBro(6,10,1,x,y, this);
                  //a.SetPosition(b);
                //  tiles.add(a);
              	 //playList.add(b);
             	playList.add(player);
                 x += SPACE;
             } else if (item == '7') {
             	a = new Position(x, y);
             	player=new HuluBro(7,10,1,x,y, this);
                 //a.SetPosition(b);
                // tiles.add(a);
              	//playList.add(b);
             	playList.add(player);
                 x += SPACE;
             }else if (item == '8') {
             	a = new Position(x, y);
             	BadBoy b=new BadBoy(8,10,1,x,y,this);
             	a.SetPosition(b);
                 badboy.add(b);
                 //tiles.add(a);
                 x += SPACE;
             } else if (item == '9') {
             	a = new Position(x, y);
             	BadBoy b=new BadBoy(9,10,1,x,y,this);
             	a.SetPosition(b);
                 badboy.add(b);
                // tiles.add(a);
                 x += SPACE;
             }

             h = y;
         }
         repaint();
	}

	public void writeToFile() {
		String level="....................\n"+
				"....................\n"+
				"....................\n"+
				"....................\n"+
				"....................\n"+
				"....................\n"+
				"....................\n"+
				"....................\n"+
				"....................\n"+
				"....................\n"+
				"....................\n";
			StringBuffer leveltemp = new StringBuffer(level);
			int x=0;
			int y=0;
			x=player.x()/SPACE;
			y=player.y()/SPACE;
			if(y!=0) x+=1;
			leveltemp.setCharAt(y*21+x, '0');
			for(HuluBro b:playList) {
				x=b.x()/SPACE;
	    		y=b.y()/SPACE;
	    		int temp=b.getRank();
	    		if(y!=0) x+=1;
	    		leveltemp.setCharAt(y*21+x,(char) ('0'+temp));
			}
	    	for(Bullet b:bullet) {
	    		
	    		 x=b.x()/SPACE;
	    		 y=b.y()/SPACE;
	    		 int temp=b.getRank();
	    //		StringBuffer leveltemp = new StringBuffer(level);
	 			if(y!=0) x+=1;
//	 			if(temp==4)
//	 				leveltemp.setCharAt(y*21+x, '$');
//	 			else if(temp==5)
//	 				leveltemp.setCharAt(y*21+x, '@');
//	 			else 
	 			leveltemp.setCharAt(y*21+x, '*');
	    	}
	    	for(BadBoy b:badboy) {
	    		x=b.x()/SPACE;
	    		 y=b.y()/SPACE;
	 			if(y!=0) x+=1;
	    		 //System.out.println(x+" "+y);
	    //		StringBuffer leveltemp = new StringBuffer(level);
	    		 if(b.GetRank()==9)
	    			 leveltemp.setCharAt(y*21+x, '9');
	    		 else leveltemp.setCharAt(y*21+x, '8');
	    	}
	             
	        File outputFile = new File(filename);
			try {
				FileWriter out = new FileWriter(outputFile,true);
				out.write(leveltemp.toString());
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
    public void AddBullet(Bullet bu) {
    	bullet.add(bu);
    	if(is_start) {
        		new Thread(bu).start();
    	}
    }
    public void DeletePlayer() {
    	int i=0;
    	while(i<playList.size()) {
    		if(!playList.get(i).IfIs_alive()) {
    			playList.remove(i);
    		}
    		else i++;
    	}
    	if(playList.isEmpty()) {
    		completed=true;
    		Result="The Bad Boys Win!";
    	}
    }
    public Position Getfromxy(int x,int y) {
    	for(Position p:tiles) {
    		if(p.x()==x&&p.y()==y)
    			return p;
    	}
    	return null;
    }
    public void DeleteBadBoy() {
    	int i=0;
    	while(i<badboy.size()) {
    		if(!badboy.get(i).IfIs_alive()) {
    			badboy.remove(i);
    		}
    		else i++;
    	}
    	if(badboy.isEmpty()) {
    		completed=true;
    		Result="The HuluBro Win!";
    	}
    }
    public void DeleteBullet() {
    	int i=0;
    	while(i<bullet.size()) {
    		if(!bullet.get(i).IfIs_alive()) {
    			bullet.remove(i);
    		}
    		else i++;
    	}
    }
    public boolean is_HasThing(int x,int y) {
    	return this.Getfromxy(x, y).is_hasThing();
    }
    public void checkWorld() {
    	for(HuluBro a:playList) {
    		for(BadBoy b:badboy) {
    			if(a.y()==b.y()&&a.x()+SPACE==b.x()) {
    				a.Hurt(b.getHurt());
    				b.Hurt(a.getHurt());
    			}
    		}
    	}
    	for(Bullet a:bullet) {
    		for(BadBoy b:badboy) {
    			if(a.y()==b.y()&&a.x()+SPACE==b.x()) {
    				a.die();
    				b.Hurt(a.getHurt());
    			}
    		}
    	}
    	for(BadBoy b:badboy) {
    		if(b.y()==player.y()&&b.x()-SPACE==player.x()){
    			player.Hurt(b.getHurt());
    			b.Hurt(player.getHurt());
    			if(!player.IfIs_alive()) {
    				completed=true;
    				Result="The BadBoy win!";
    			}
    			}
    			
    	}
    }
    public void buildWorld(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
       
        world.addAll(tiles);

        world.add(player);
        world.addAll(playList);
        world.addAll(badboy);
        world.addAll(bullet);


        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed&&!is_review) {
                g.setColor(new Color(0, 0, 0));
                g.setFont(getFont());
                g.drawString("Completed , "+Result, 25, 20);
//                for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet())
//                {
//                	Thread thread = (Thread) stackTrace.getKey();
//                	StackTraceElement[] stack = (StackTraceElement[]) stackTrace.getValue();
//                	if (thread.equals(Thread.currentThread())) {
//                		continue;
//                	}
//                	thread.interrupt();
//               }
                playList.clear();
                badboy.clear();
                bullet.clear();
                //completed=false;
               // count++;
            }

        }
    }

	@Override
    public void paint(Graphics g) {
		super.paint(g);
        if(!is_review) {
        	writeToFile();
        	checkWorld();
       // DeletePlayer();
        	DeleteBullet();
        	DeleteBadBoy();
        }
        buildWorld(g);
    }
    
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

//            if (completed) {
//                return;
//            }


            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {


               player.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {


               player.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {


               player.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {


               player.move(0, SPACE);

            } 
            else if(key==KeyEvent.VK_O) {
            	if(completed) {
            		initWorld();
            		new Thread(player.field).start();
                	is_start=true;
                	new Thread(player).start();
                	for(Bullet b:bullet) {
                		new Thread(b).start();
                	}
                	for(BadBoy b:badboy) {
                		new Thread(b).start();
                	}
                	completed=false;
            	}
            	else return;
            }else if (key == KeyEvent.VK_S) {
            	if(!is_start) {
            		//new Thread(player.field).start();
                	is_start=true;
                	new Thread(player).start();
                	for(Bullet b:bullet) {
                		new Thread(b).start();
                	}
                	for(BadBoy b:badboy) {
                		new Thread(b).start();
                	}
                	for(HuluBro b:playList) {
                		new Thread(b).start();
                	}
            	}else return;

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
                completed=false;
               // count++;
            }
            else if(key==KeyEvent.VK_V) {
            	if(!completed&&is_start) return ;
					Review();
				
            }
            else if(key==KeyEvent.VK_0&&!is_start) {
            	if(is_HasThing(CurX,CurY)) return;
            	HuluBro a=new HuluBro(0,10,10,CurX,CurY,player.GetField());
            	player.field.Getfromxy(CurX, CurY).SetPosition(a);
            	playList.add(a);
            }else if(key==KeyEvent.VK_1&&!is_start) {
            	if(is_HasThing(CurX,CurY)) return;
            	HuluBro a=new HuluBro(1,10,10,CurX,CurY,player.GetField());
            	player.field.Getfromxy(CurX, CurY).SetPosition(a);
            	playList.add(a);
            }else if(key==KeyEvent.VK_2&&!is_start) {
            	if(is_HasThing(CurX,CurY)) return;
            	HuluBro a=new HuluBro(2,10,10,CurX,CurY,player.GetField());
            	player.field.Getfromxy(CurX, CurY).SetPosition(a);
            	playList.add(a);
            }else if(key==KeyEvent.VK_3&&!is_start) {
            	if(is_HasThing(CurX,CurY)) return;
            	HuluBro a=new HuluBro(3,10,10,CurX,CurY,player.GetField());
            	player.field.Getfromxy(CurX, CurY).SetPosition(a);
            	playList.add(a);
            }else if(key==KeyEvent.VK_4&&!is_start) {
            	if(is_HasThing(CurX,CurY)) return;
            	HuluBro a=new HuluBro(4,10,10,CurX,CurY,player.GetField());
            	player.field.Getfromxy(CurX, CurY).SetPosition(a);
            	playList.add(a);
            }else if(key==KeyEvent.VK_5&&!is_start) {
            	if(is_HasThing(CurX,CurY)) return;
            	HuluBro a=new HuluBro(5,10,10,CurX,CurY,player.GetField());
            	player.field.Getfromxy(CurX, CurY).SetPosition(a);
            	playList.add(a);
            }else if(key==KeyEvent.VK_6&&!is_start) {
            	if(is_HasThing(CurX,CurY)) return;
            	HuluBro a=new HuluBro(6,10,10,CurX,CurY,player.GetField());
            	player.field.Getfromxy(CurX, CurY).SetPosition(a);
            	playList.add(a);
            }else if(key==KeyEvent.VK_7&&!is_start) {
            	if(is_HasThing(CurX,CurY)) return;
            	HuluBro a=new HuluBro(7,10,10,CurX,CurY,player.GetField());
            	player.field.Getfromxy(CurX, CurY).SetPosition(a);
            	playList.add(a);
            }
            else if(key==KeyEvent.VK_A) {
            	if(CurNum>0) 
            		CurNum--;
            	else CurNum=playList.size()-1;
            		HuluBro temp=playList.get(CurNum);
            		playList.set(CurNum, player);
            		player=temp;
            }else if(key==KeyEvent.VK_D) {
            	if(CurNum<playList.size()-1) 
            		CurNum++;
            	else CurNum=0;
            		HuluBro temp=playList.get(CurNum);
            		playList.set(CurNum, player);
            		player=temp;
            	
            }
            repaint();
        }
    }

    class MouthAdapter implements MouseListener {

    	@Override
    	public void mouseClicked(MouseEvent e) {
    		// TODO Auto-generated method stub
    		CurX=e.getX()-e.getX()%SPACE;
    		CurY=e.getY()-e.getY()%SPACE;
    		//player.moveTo(CurX, CurY);
    		repaint();
    	}

    	@Override
    	public void mousePressed(MouseEvent e) {
    		// TODO Auto-generated method stub

    	}

    	@Override
    	public void mouseReleased(MouseEvent e) {
    		// TODO Auto-generated method stub

    	}

    	@Override
    	public void mouseEntered(MouseEvent e) {
    		// TODO Auto-generated method stub

    	}

    	@Override
    	public void mouseExited(MouseEvent e) {
    		// TODO Auto-generated method stub

    	}

    }
    public void restartLevel() {
//        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet())
//        {
//        	Thread thread = (Thread) stackTrace.getKey();
//        	//StackTraceElement[] stack = (StackTraceElement[]) stackTrace.getValue();
////        	if (thread.equals(Thread.currentThread())) {
////        		continue;
////        	}
//        	thread.interrupt();
//       }
        tiles.clear();
        playList.clear();
        badboy.clear();
        bullet.clear();
        initWorld();
        repaint();
        if (completed) {
            completed = false;
        }
    }
    public void Setcompleted() {
    	this.completed=true;
    	this.Result="The BadBoy win!";
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.interrupted()) {
			File file=ChooseFile();
	    	BufferedReader input;
			try {
				input = new BufferedReader(new FileReader(file));
				int line = 0;
		        String f=null;
		        try {
					while ((f=input.readLine())!=null) {
						line=0;
						f=f+"\n";
					    while(line < 10){
					        f += input.readLine()+"\n";
					        line++;
					    }
					   // level=f;
					    ResetWorld(f);
					    this.repaint();
		                try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                //this.badboy.clear();
		                //this.bullet.clear();
		               // this.playList.clear();
						}
					Thread.interrupted();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
		
	}
}