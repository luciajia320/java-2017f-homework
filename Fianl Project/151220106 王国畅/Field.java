import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Field extends JPanel{
	protected final int M,N;
    protected final int OFFSET = 30;
    protected final int SPACE = 20;
    private boolean Hwin=false,Mwin=false;
    private int w = 0;
    private int h = 0;
	private Position[][] pos;
	private boolean gameover;
    private ArrayList<grass> background = new ArrayList<grass>();
    private Image body;
    private Huluwa[] brothers;
    private Louluo[] monsters;
    private FenceSitter audience;
    private ExecutorService exec;
    protected String dir = System.getProperty("user.dir");
    protected FileWriter fw;
    private boolean reviewable = true;
	
	public Field(ExecutorService exec){
		this.exec = exec;
		M = 15;
		N = 15;
		gameover = false;
        URL loc = this.getClass().getClassLoader().getResource("body.png");
        ImageIcon iia = new ImageIcon(loc);
        body = iia.getImage();
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        initWorld();
	}
	
	
    public final void initWorld() {
    	
        grass a;
        
		pos = new Position[M][N];
		
		for(int i=0;i<M;i++){
			
			for(int j=0;j<N;j++){
				
				pos[i][j] = new Position(i,j);
				
				a = new grass(i,j);
				
				background.add(a);
			}
			
		}
		
		w = N*OFFSET+OFFSET;
		h = M*OFFSET+OFFSET;
    }
	
    public void printGrass(Graphics g){
        for(grass i:background){
        	g.drawImage(i.getImage(), i.y()*OFFSET+OFFSET, i.x()*OFFSET+OFFSET, this);
        }
    }
    
    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        printGrass(g);
        for(int i = 0;i < M;i ++){
        	for(int j = 0;j < N;j ++){
				Creature temp = pos[i][j].getHolder();
				if(temp!=null){
					if(!temp.dead()){
						g.drawImage(temp.getImage(), j*OFFSET+OFFSET, i*OFFSET+OFFSET, this);
						try {
							int num = temp.getNum();
							fw.write(num);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						g.drawImage(this.body, j*OFFSET+OFFSET, i*OFFSET+OFFSET, this);
						try {
							int num = 0;
							fw.write(num);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else{
					try {
						int num = 11;
						fw.write(num);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
        	}
        }
    }
    
    @Override
    public void paint(Graphics g) {
    	synchronized(fw){
            super.paint(g);
            buildWorld(g);
    	}
    }
    
	public void printStatus(){
			System.out.println(">>>"+Thread.currentThread());
			System.out.println("==============================");
			for(int i=0;i<15;i++){
				for(int j=0;j<15;j++){
					Creature temp = pos[i][j].getHolder();
					if(temp!=null){
						if(!temp.dead())
							System.out.print(temp.getName());
						else
							System.out.print("ËÀ");
					}
					else{
						System.out.print("^^");
					}
				}
				System.out.println();
			}
			System.out.println("==============================");
	}
	
	public Position getFieldPos(int x,int y){
		if(x<M&&x>=0&&y<N&&y>=0)
			return pos[x][y];
		else
			return null;
	}
	
	public void gameOver(){
		this.gameover=true;
		this.reviewable=true;
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean gameIsOver(){
		return this.gameover;
	}

	public int getBoardWidth() {
		// TODO Auto-generated method stub
		return this.w;
	}

	public int getBoardHeight() {
		// TODO Auto-generated method stub
		return this.h;
	}
	
	public void gameStart(){
		this.reviewable=false;
        try {
			fw = new FileWriter(dir+"/GameLog");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.gameover=false;
		this.brothers = new Huluwa[7];
		this.monsters = new Louluo[7];
		this.audience = new FenceSitter();
		
		for(int i=0;i<7;i++){
			brothers[i] = new Huluwa(COLOR.values()[i],RANK.values()[i],audience,this);
		}
		for(int i=0;i<7;i++){
			if(i==0)
				monsters[i] = new Xiezijing(audience,this);
			else if(i==1)
				monsters[i] = new Shejing(audience,this);
			else
				monsters[i] = new Louluo(audience,this);
		}
		audience.Record(brothers, monsters, null, this);
		new HengE().setFormation(brothers, this);
		new ChangShe().setFormation(monsters, this);
		this.repaint();
		for(int i=0;i<7;i++){
			exec.execute(brothers[i]);
		}
		for(int i=0;i<7;i++){
			exec.execute(monsters[i]);
		}
	}
	
	public Position[][] getField(){
		return pos;
	}
	
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

 /*           if (completed) {
                return;
            }*/


            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
            	gameStart();
/*            	synchronized(getField()){
    				try {
    					TimeUnit.MILLISECONDS.sleep(5000);
    				} catch (InterruptedException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				Thread.yield();
            	}*/
            }  else if (key == KeyEvent.VK_S) {

                gameOver();
            }  else if (key == KeyEvent.VK_L){
            	
            	if(!reviewable)
            		return;
            	else{
                	ReviewDialog revd = new ReviewDialog();
            		return;
            	}
            		
            }
            repaint();

        }
    }
    
    public void setWinner(int side){
    	if(side==0)
    		Hwin = true;
    	else
    		Mwin = true;
    }
    
    public String getWinner(){
    	if(Hwin = true)
    		return "Huluwas";
    	else
    		return "Monsters";
    }
}