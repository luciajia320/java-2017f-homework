import java.awt.Image;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

enum COLOR {

    赤, 橙, 黄, 绿, 青, 蓝, 紫

}

enum RANK {

    一, 二, 三, 四, 五, 六, 七

}

public class Huluwa implements Creature{
	private FenceSitter audience;
	private Position pos;
	private COLOR color;
	private RANK rank;
	private final int side;
	private int dir;
	private Field field;
	private boolean dead;
	private final int luck;
	private Image image;
	private int idx;
	
	public Huluwa(COLOR color, RANK rank,FenceSitter audience,Field field){
		this.audience = audience;
		this.pos = null;
		this.color = color;
		this.rank = rank;
		this.side = 0;
		this.dir = 4;
		this.field = field;
		this.dead = false;
		this.luck = new java.util.Random().nextInt(8);
		this.idx = rank.ordinal()+1;
        URL loc = this.getClass().getClassLoader().getResource("huluwa"+this.idx+".png");
        ImageIcon iia = new ImageIcon(loc);
        this.image = iia.getImage();
	}
	
	public COLOR getColor(){
		return this.color;
	}
	
	public RANK getRank(){
		return this.rank;
	}
	
	@Override
	public void run() {
/*		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println(Thread.currentThread()+" initialize");
		while(dead == false&&!field.gameIsOver()){
			synchronized(field.getField()){
				if(pos == null){
					System.out.println("again");
					Thread.yield();
					continue;
				}
				move();
				System.out.println(Thread.currentThread()+" moved");
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				field.printStatus();
				field.repaint();
				Thread.yield();
			}
		}
		synchronized(field.getField()){
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.pos!=null)
				this.pos.setHolder(null);
			Thread.yield();
		}
//		System.out.println("A Huluwa has been killed!");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.rank.toString();
	}

	@Override
	public int setFieldPos(Position pos) {
		// TODO Auto-generated method stub
		//不可同行返回0，发生战斗返回1，成功设置返回2
		if(pos == null)
			return 0;
		else{
			int r = pos.setHolder(this);
			if(r == 2){
				if(this.pos!=null)
					this.pos.setHolder(null);
				this.pos = pos;
			}
			return r;
		}
	}

	@Override
	public Position getFieldPos() {
		// TODO Auto-generated method stub
		return this.pos;
	}

	@Override
	public void move() {
		Position next = null;
		if(this.dir==1){
			int srcx = pos.getX(),srcy = pos.getY();
			next = field.getFieldPos(srcx-1, srcy);
			int r = setFieldPos(next);
			if(r == 0)
				dir=2;
			else if(r == 1){
				Creature monster = next.getHolder();
				if(monster.dead())
					dir=2;
				else{
					audience.ViewBattle(this, monster);
				}
			}
			else
				return;
		}
		if(this.dir==2){
			int srcx = pos.getX(),srcy = pos.getY();
			next = field.getFieldPos(srcx, srcy-1);
			int r = setFieldPos(next);
			if(r == 0)
				dir=3;
			else if(r == 1){
				Creature monster = next.getHolder();
				if(monster.dead())
					dir=3;
				else{
					audience.ViewBattle(this, monster);
				}
			}
			else
				return;
		}
		if(this.dir==3){
			int srcx = pos.getX(),srcy = pos.getY();
			next = field.getFieldPos(srcx+1, srcy);
			int r = setFieldPos(next);
			if(r == 0)
				dir=4;
			else if(r == 1){
				Creature monster = next.getHolder();
				if(monster.dead())
					dir=4;
				else{
					audience.ViewBattle(this, monster);
				}
			}
			else
				return;
		}
		if(this.dir==4){
			int srcx = pos.getX(),srcy = pos.getY();
			next = field.getFieldPos(srcx, srcy+1);
			int r = setFieldPos(next);
			if(r == 0)
				dir=1;
			else if(r == 1){
				Creature monster = next.getHolder();
				if(monster.dead())
					dir=1;
				else{
					audience.ViewBattle(this, monster);
				}
			}
			else
				return;
		}
	}

	@Override
	public int getSide() {
		// TODO Auto-generated method stub
		return this.side;
	}

	@Override
	public boolean dead() {
		// TODO Auto-generated method stub
		return this.dead;
	}

	@Override
	public void getKilled() {
		// TODO Auto-generated method stub
		this.dead = true;
	}

	@Override
	public int getLuck() {
		// TODO Auto-generated method stub
		return this.luck;		
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}

	@Override
	public int getNum() {
		// TODO Auto-generated method stub
		return this.idx;
	}
}
