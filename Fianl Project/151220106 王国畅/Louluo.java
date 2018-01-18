import java.awt.Image;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

public class Louluo implements Creature{
	private FenceSitter audience;
	private Position pos;
	private final int side;
	private int dir;
	private Field field;
	private boolean dead;
	protected int luck;
	protected Image image;
	
	public Louluo(FenceSitter audience,Field field){
		this.audience = audience;
		this.pos = null;
		this.side = 1;
		this.field = field;
		this.dead = false;
		this.luck = new java.util.Random().nextInt(8);
		this.dir = 4;
        URL loc = this.getClass().getClassLoader().getResource("Louluo.png");
        ImageIcon iia = new ImageIcon(loc);
        this.image = iia.getImage();
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread()+" initialize");
		while(dead == false&&!field.gameIsOver()){
			synchronized(field){
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
		return "喽";
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
		return this.pos;
	}

	@Override
	public void move() {
		Position next;
		if(this.dir==1){
			int srcx = pos.getX(),srcy = pos.getY();
			next = field.getFieldPos(srcx-1, srcy);
			int r = setFieldPos(next);
			if(r == 2)
				return;
			else
				dir=2;
		}
		if(this.dir==2){
			int srcx = pos.getX(),srcy = pos.getY();
			next = field.getFieldPos(srcx, srcy+1);
			int r = setFieldPos(next);
			if(r == 2)
				return;
			else
				dir=3;
		}
		if(this.dir==3){
			int srcx = pos.getX(),srcy = pos.getY();
			next = field.getFieldPos(srcx+1, srcy);
			int r = setFieldPos(next);
			if(r == 2)
				return;
			else
				dir=4;
		}
		if(this.dir==4){
			int srcx = pos.getX(),srcy = pos.getY();
			next = field.getFieldPos(srcx, srcy-1);
			int r = setFieldPos(next);
			if(r == 2)
				return;
			else
				dir=1;
		}
	}

	@Override
	public int getSide() {
		return this.side;
	}

	@Override
	public boolean dead() {
		return this.dead;
	}

	@Override
	public void getKilled() {
		this.dead = true;
	}

	@Override
	public int getLuck() {
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
		return 8;
	}

}
