//package huluwa_final;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;


import javax.swing.JPanel;

public class Field extends JPanel {

    private final int OFFSET = 30;
    private final int SPACE = 20;
    
    private final int Hulu_num = 8;
    private int count1 = Hulu_num;
    private int count2 = Hulu_num;
    
    private boolean re_play;
    private boolean start;
    
    //private Replay my_replay= new Replay(this,1);
    
    //ExecutorService exec;

    private ArrayList tiles = new ArrayList();

    private Player[] plays;
    private Player[] louluos;
    private Thread[] Thulu;
    private Thread[] Tlouluo;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level = new Map(1).c_map();

    public Field() {
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
    
    public boolean in_field(int x,int y) {
    	if(x > 10 && y > 10 && y <= this.getBoardHeight() - 20 && x <= this.getBoardWidth() - 20 )
    		return true;
    	else return false;
    }

    public void fighting() {
    	for(int i = 0;i < Hulu_num;i++) {
    		for(int j = 0;j < Hulu_num;j++) {
    			if(plays[i].live() && louluos[j].live() 
    					&& plays[i].x() == louluos[j].x() && plays[i].y() == louluos[j].y()) {
    				int type = plays[i].fightwith(louluos[j]);
    				System.out.println(i + " vs " + j + ":");
    				if(type < 10) {
    					//plays[i].death();
    					count1--;
    					System.out.println("->"+j+"\n");
    				}
    				else {
    					//louluos[j].death();
    					count2--;
    					System.out.println("->"+i+"\n");
    				}
    			}
    		}
    	}
    }
    
    //@SuppressWarnings("deprecation")
	public boolean gameover() {
    		if(count1 == 0 || count2 == 0) {
    			this.completed = true;
    			//exec.shutdown();
    			return true;
    		}
    		else return false;
    }
    
	public boolean hold(int x,int y,int type) {
		if(type < 10)
			for(int i = 0;i < Hulu_num;i++) {
				if(plays[i].x() == x && plays[i].y() == y && plays[i].live())
					return true;
			}
		else
			for(int i = 0;i < Hulu_num;i++) {
				if(louluos[i].x() == x && louluos[i].y() == y && louluos[i].live())
					return true;
			}
		return false;
	}
	
    public Player near(int x,int y,int type) {
    	int tempx = 900;
    	int tempy = 900;
    	Player temp = new Player(tempx,tempy,this,type);
    	if(type < 10) {
    		for(int i = 0;i < Hulu_num;i++) {
    			if(louluos[i].live() && 
    					(Math.abs(louluos[i].x() - x) + Math.abs(louluos[i].y() - y)) < tempx + tempy) {
    				tempx = Math.abs(louluos[i].x() - x);
    				tempy = Math.abs(louluos[i].y() - y);
    				temp = louluos[i];
    			}
    		}
    	}
    	else {
    		for(int i = 0;i < Hulu_num;i++) {
    			if(plays[i].live() && 
    					(Math.abs(plays[i].x() - x) + Math.abs(plays[i].y() - y)) < tempx + tempy) {
    				tempx = Math.abs(plays[i].x() - x);
    				tempy = Math.abs(plays[i].y() - y);
    				temp = plays[i];
    			}
    		}
    	}
    	return temp;
    }
    
    public final void initWorld() {
    	
        plays = new Player[Hulu_num];
        louluos = new Player[Hulu_num];
        Thulu = new Thread[Hulu_num];
        Tlouluo = new Thread[Hulu_num];
        
        re_play = false;
        start = false;
        
        int x = OFFSET;
        int y = OFFSET;

        Tile a;
        
        int counter1 = 0;
        int counter2 = 0;
        
        try {
        	FileOutputStream out = new FileOutputStream("test.txt");
        	
        	out.write(level.getBytes());
        	out.write("\r\n".getBytes());
        	out.close();
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }


        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == 'n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item >= '1' && item <= '7') {
            	plays[counter1++] = new Player(x,y,this,item-'0');
            	Thulu[counter1-1] = new Thread(plays[counter1-1]);
            	//System.out.println(counter1);
            	a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if( item == 'g') {
            	plays[counter1++] = new Player(x,y,this,8);
            	Thulu[counter1-1] = new Thread(plays[counter1-1]);
            	a = new Tile(x, y);
            	//System.out.println(counter1);
                tiles.add(a);
                x += SPACE;
            } else if( item <= 'f' && item >= 'a') {
            	louluos[counter2++] = new Player(x,y,this,item - 'a' + 11);
            	Tlouluo[counter2-1] = new Thread(louluos[counter2-1]);
            	a = new Tile(x, y);
            	//System.out.println(counter2);
                tiles.add(a);
                x += SPACE;
            }else if( item == 's') {
            	louluos[counter2++] = new Player(x,y,this,17);
            	Tlouluo[counter2-1] = new Thread(louluos[counter2-1]);
            	a = new Tile(x, y);
            	//System.out.println(counter2);
                tiles.add(a);
                x += SPACE;
            }else if( item == 'x') {
            	louluos[counter2++] = new Player(x,y,this,18);
            	Tlouluo[counter2-1] = new Thread(louluos[counter2-1]);
            	a = new Tile(x, y);
            	//System.out.println(counter2);
                tiles.add(a);
                x += SPACE;
            }else if (item == ' ') {
                x += SPACE;
            }
            
            h = y;
        }
        System.out.println(w + "," + h);
    }

	public void restart() {
    	int x = OFFSET;
        int y = OFFSET;

        Tile a;
        
        int counter1 = 0;
        int counter2 = 0;


        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == 'n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item >= '1' && item <= '7') {
            	plays[counter1++] = new Player(x,y,this,item-'0');
            	//System.out.println(counter1+1);
            	//Thulu[counter1-1] = new Thread(plays[counter1-1]);
            	a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if( item == 'g') {
            	plays[counter1++] = new Player(x,y,this,8);
            	//Tlouluo[counter2-1] = new Thread(louluos[counter2-1]);
            	a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if( item <= 'f' && item >= 'a') {
            	louluos[counter2++] = new Player(x,y,this,item-'a' + 11);
            	//Tlouluo[counter2-1] = new Thread(louluos[counter2-1]);
            	a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            }else if( item == 's') {
            	louluos[counter2++] = new Player(x,y,this,17);
            	//Tlouluo[counter2-1] = new Thread(louluos[counter2-1]);
            	a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            }else if( item == 'x') {
            	louluos[counter2++] = new Player(x,y,this,18);
            	//Tlouluo[counter2-1] = new Thread(louluos[counter2-1]);
            	a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }
            
            h = y;
        }
    }
    
    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);

        if(!re_play)
        	fighting();
        
        
        
        for(int i = 0;i < Hulu_num;i++) {
        	if(plays[i].live())
        		world.add(plays[i]);
        	if(louluos[i].live())
        		world.add(louluos[i]);
        }
        
        //world.remove(0);


        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                if(count1 > 0)
                	g.drawString("Completed,	Huluwa WIN", 25, 20);
                else
                	g.drawString("Completed,	YaoGuai WIN", 25, 20);
            }

        }
        
        gameover();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    public void print() {
    	for(int i = 0;i < Hulu_num;i++) {
    		System.out.println(i + ": (" + plays[i].x() + "," + plays[i].y() + ")");
    	}
    }
    
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

        	int key = e.getKeyCode();
        	
            if (completed) {
            	if(key == KeyEvent.VK_L) {
            		//new Thread(my_replay).start();
            		RePlay my_replay = new RePlay();
            		re_play = true;
            		new Thread(my_replay).start();
            		//replay();
            	}
                return;
            }

            if(!start) {
            	if (key == KeyEvent.VK_LEFT) {
            	
            		plays[0].move(-SPACE, 0);print();

            	} else if (key == KeyEvent.VK_RIGHT) {

            		plays[0].move(SPACE, 0);print();
                //player2.move(SPACE, 0);

            	} else if (key == KeyEvent.VK_UP) {


            		plays[0].move(0, -SPACE);print();
                //player2.move(0, -SPACE);

            	} else if (key == KeyEvent.VK_DOWN) {


            		plays[0].move(0, SPACE);print();
                //player2.move(0, SPACE);

            	} else if (key == KeyEvent.VK_SPACE) {

                //new Thread(player1).start();
                //new Thread(player2).start();
            	//ExecutorService exec = Executors.newCachedThreadPool();
            	/*for(int i = 0;i < Hulu_num;i++) {
            		exec.execute(plays[i]);
            		exec.execute(louluos[i]);
            	}*/
            		for(int i = 0;i < Hulu_num;i++) {
            		Thulu[i].start();
            		Tlouluo[i].start();
            		}
            		start = true;

            	} else if (key == KeyEvent.VK_R) {
            		restartLevel();
            	}else if(key == KeyEvent.VK_L) {
            		//new Thread(my_replay).start();
            		RePlay my_replay = new RePlay();
            		re_play = true;
            		new Thread(my_replay).start();
            		//replay();
            	}
            }

            repaint();
        }
    }


    //@SuppressWarnings("deprecation")
	public void restartLevel() {

    	/*for(int i = 0;i < Hulu_num;i++) {
    		Thulu[i].wait();
    		Tlouluo[i].destroy();
    	}*/
        tiles.clear();
        //initWorld();
        restart();
        if (completed) {
            completed = false;
        }
    }
	
	class RePlay implements Runnable{
		String level = "0";
		String[] order;
		int count;
		
		public RePlay() {
			//Frame f = new Frame("FileDialog Test");
			FileDialog d1;
			String filename = null;
			while(filename == null) {
				d1 = new FileDialog(new Frame(),"ѡ���¼",FileDialog.LOAD);
				d1.setVisible(true);
				filename = d1.getFile();
			}
			
			
			
			
			try {
				FileInputStream in = new FileInputStream(filename);
				byte bs[] = new byte[in.available()];
				in.read(bs);
				level = new String(bs);
				//System.out.println(level);
				in.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			//String[] test = level.split("\n");
			order = level.split("\n");
			//System.out.println(test.length);
			this.level = order[0].toString();
			restartLevel();
			
			count = 1;
		}

		public void run() {
			while(!Thread.interrupted()) {
				if(count < order.length) {
					System.out.println(order[count].toString());
					String[] temp = order[count].toString().split(",");
					if(temp[0].toString().charAt(0) == 'm') {
						int place = Integer.parseInt(temp[1].toString());
						System.out.println(Integer.parseInt(temp[1].toString()));
						if(place < 10) {
							if(temp[3].toString().charAt(0) != '-') {
								if(temp[2].toString().charAt(0) == 'x')
									plays[place-1].move(20, 0);
								else plays[place-1].move(0, 20);
							//System.out.println(Integer.parseInt(temp[3].toString().substring(0, 1)));
							}
							else {
								if(temp[2].toString().charAt(0) == 'x')
									plays[place-1].move(-20, 0);
								else plays[place-1].move(0, -20);
							//System.out.println(Integer.parseInt(temp[3].toString().substring(1, 2)));
							}
						}
						else {
							if(temp[3].toString().charAt(0) != '-') {
								if(temp[2].toString().charAt(0) == 'x')
									louluos[place - 11].move(20, 0);
								else louluos[0].move(0, 20);
							//System.out.println(Integer.parseInt(temp[3].toString().substring(0, 1)));
							}
							else {
								if(temp[2].toString().charAt(0) == 'x')
									louluos[place - 11].move(-20, 0);
								else louluos[place - 11].move(0, -20);
							//System.out.println(Integer.parseInt(temp[3].toString().substring(1, 2)));
							}
						}
					}
					String[] temp1 = order[count+1].toString().split(",");
					if(temp1[0].toString().charAt(0) == 'k') {
						count++;
						int place = Integer.parseInt(temp1[1].toString());
						if(place < 10) plays[place-1].death();
						else louluos[place-11].death();
						//System.out.println(Integer.parseInt(temp1[1].toString()));
						/*int leng = temp1[1].length();
						if(leng == 2) {
							System.out.println("flag1," + temp1[1].toString() + ","+temp1[1].toString().substring(0, 0));
							//plays[Integer.parseInt(temp[1].toString().substring(0, 0))-1].death();
						}
						else if(leng == 3) System.out.println("flag2," + temp1[1].toString() + ","+temp1[1].toString().substring(0, 1));
						else if(leng == 4) System.out.println("flag3");
						//louluos[Integer.parseInt(temp[1].toString().substring(0, 1))-11].death();
						/*int place = Integer.parseInt(temp1[1].toString());
						if(place < 10) plays[place-1].death();
						else louluos[place-11].death();*/
					}
					if( count < order.length -1) {
						String[] temp2 = order[count+1].toString().split(",");
						if(temp2[0].toString().charAt(0) == 'k') {
							count++;
							int place = Integer.parseInt(temp2[1].toString());
							if(place < 10) plays[place-1].death();
							else louluos[place-11].death();
							//System.out.println(Integer.parseInt(temp2[1].toString()));
						}
						/*if(temp2[0].toString().charAt(0) == 'k') {
							i++;
							int leng = temp2[1].length();
							if(leng == 1) {
								System.out.println("flag1," + temp2[1].toString() + ","+temp2[1].toString().substring(0, 0));
								//plays[Integer.parseInt(temp[1].toString().substring(0, 0))-1].death();
							}
							else if(leng == 2) System.out.println("flag2," + temp2[1].toString() + ","+temp2[1].toString().substring(0, 1));
							else if(leng == 3) System.out.println("flag3");
							//louluos[Integer.parseInt(temp[1].toString().substring(0, 1))-11].death();
							/*int place = Integer.parseInt(temp1[1].toString());
							if(place < 10) plays[place-1].death();
							else louluos[place-11].death();*/
						//}
					}
					count++;
		            try {

		                Thread.sleep(100);
		                //repaint();

		            } catch (Exception e) {

		            }
				}
			}
			
		}
		
	}
	
	/*public void replay() {
		String level = "0";
		try {
			FileInputStream in = new FileInputStream("test.txt");
			byte bs[] = new byte[in.available()];
			in.read(bs);
			level = new String(bs);
			//System.out.println(level);
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//this.level = level;
		String[] test = level.split("\n");
		//System.out.println(test.length);
		this.level = test[0].toString();
		restartLevel();
		for(int i = 1,len = test.length;i < len;i++) {
			System.out.println(test[i].toString());
			String[] temp = test[i].toString().split(",");
			if(temp[0].toString().charAt(0) == 'm') {
				int place = Integer.parseInt(temp[1].toString());
				System.out.println(Integer.parseInt(temp[1].toString()));
				if(place < 10) {
					if(temp[3].toString().charAt(0) != '-') {
						if(temp[2].toString().charAt(0) == 'x')
							plays[place-1].move(20, 0);
						else plays[place-1].move(0, 20);
					//System.out.println(Integer.parseInt(temp[3].toString().substring(0, 1)));
					}
					else {
						if(temp[2].toString().charAt(0) == 'x')
							plays[place-1].move(-20, 0);
						else plays[place-1].move(0, -20);
					//System.out.println(Integer.parseInt(temp[3].toString().substring(1, 2)));
					}
				}
				else {
					if(temp[3].toString().charAt(0) != '-') {
						if(temp[2].toString().charAt(0) == 'x')
							louluos[place - 11].move(20, 0);
						else louluos[0].move(0, 20);
					//System.out.println(Integer.parseInt(temp[3].toString().substring(0, 1)));
					}
					else {
						if(temp[2].toString().charAt(0) == 'x')
							louluos[place - 11].move(-20, 0);
						else louluos[place - 11].move(0, -20);
					//System.out.println(Integer.parseInt(temp[3].toString().substring(1, 2)));
					}
				}
			}
			String[] temp1 = test[i+1].toString().split(",");
			if(temp1[0].toString().charAt(0) == 'k') {
				i++;
				int place = Integer.parseInt(temp1[1].toString());
				if(place < 10) plays[place-1].death();
				else louluos[place-11].death();
			}
			if(i < len-1) {
				String[] temp2 = test[i+1].toString().split(",");
				if(temp2[0].toString().charAt(0) == 'k') {
					i++;
					int place = Integer.parseInt(temp2[1].toString());
					if(place < 10) plays[place-1].death();
					else louluos[place-11].death();
					//System.out.println(Integer.parseInt(temp2[1].toString()));
				}
			}
			try{
				Thread.sleep(1000);
				}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}*/
}
