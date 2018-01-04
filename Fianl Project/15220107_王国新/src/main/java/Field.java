import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel implements ActionListener {
	//各个生物的初始坐标
	private final int huluwaX = 550;
	private final int huluwaY = 450;
	private final int scorpionX = 875;
	private final int scorpionY = 450;
	private final int monsterX = 1100;
	private final int monsterY = 420;
	private final int boardWidth = 1500;
	private final int boardHeight = 750;

	private decorateImg decorate = new decorateImg();
	private ArrayList<Creature> brothers = new ArrayList<Creature>();
	private ArrayList<Creature> enemies = new ArrayList<Creature>();
	private ArrayList<Creature> boss = new ArrayList<Creature>();
	private Monster monster = new Monster(this, monsterX, monsterY);
	private ReplaySystem replaysystem = new ReplaySystem(brothers, enemies, monster);
	private FileDialog fileDialog;
	private Timer timer;

	
	private int timeCount = 1;
	private boolean isBegin = false;
	private boolean monsterChapter = false;
	private boolean isWin = false;
	private boolean isLose = false;
	private boolean isReplay = false;

	public Field() {
		timer = new Timer(100, this);
		addKeyListener(new TAdapter());
		setFocusable(true);
		initWorld();
	}
	
	public void addFileDialog(FileDialog fileDialog) {
		this.fileDialog = fileDialog;
	}

	public int getBoardWidth() {
		return this.boardWidth;
	}

	public int getBoardHeight() {
		return this.boardHeight;
	}

	public void initWorld() {
		//葫芦娃
		for (int i = 0; i < 7; i++) {
			Huluwa huluwa = new Huluwa(SENIORITY.values()[i], this);
			brothers.add(huluwa);
		}
		new HeyiFormation(brothers, huluwaX, huluwaY, Formation.left);

		//蝎子精和小喽啰
		enemies.add(new Scorpion(this));
		for (int i = 1; i < 7; i++) {
			enemies.add(new Frog(i, this));
		}
		new HeyiFormation(enemies, scorpionX, scorpionY, Formation.right);
		boss.add(monster);

		for (int i = 0; i < brothers.size(); i++) {
			brothers.get(i).initState();
			brothers.get(i).setEnemy(enemies);
		}

		System.out.println();
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).initState();
			enemies.get(i).setEnemy(brothers);
		}
		System.out.println();
		monster.initState();
		monster.setEnemy(brothers);
	}

	public void initMonsterChapter() {
		for (int i = 0; i < brothers.size(); i++) {
			brothers.get(i).setEnemy(boss);
			brothers.get(i).beginAttack();
		}
		monster.beginAttack();
		monsterChapter = true;
	}

	public void buildWorld(Graphics g) {
		g.setColor(new Color(250, 240, 170));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// -----------------背景---------------------
		g.drawImage(decorate.getBkgImage(), decorate.x(), decorate.y(), this);

		// -----------------生物---------------------
		for (int i = 0; i < brothers.size(); i++) {
			Creature item = brothers.get(i);
			g.drawImage(item.getImage(), item.x() + 5, item.y(), this);
		}

		for (int i = 0; i < enemies.size(); i++) {
			Creature item = enemies.get(i);
			g.drawImage(item.getImage(), item.x(), item.y(), this);
		}

		g.drawImage(monster.getImage(), monster.x(), monster.y(), this);

		// -----------------攻击波---------------------
		EnergyBlast item;
		for (int i = 0; i < brothers.size(); i++) {
			item = brothers.get(i).getEnergyBlast();
			if (item.getEBvisible()) {
				g.drawImage(item.getImage(), item.x() + 36, item.y() + 30, this);
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			item = enemies.get(i).getEnergyBlast();
			if (item.getEBvisible()) {
				g.drawImage(item.getImage(), item.x(), item.y() + 10, this);
			}
		}
		item = monster.getEnergyBlast();
		if (item.getEBvisible()) {
			g.drawImage(item.getImage(), item.x(), item.y() + 50, this);
		}

		if (this.isWin)
			g.drawImage(decorate.getWinImage(), decorate.x(), decorate.y(), this);
		if (this.isLose)
			g.drawImage(decorate.getLoseImage(), decorate.x(), decorate.y(), this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		buildWorld(g);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (this.isReplay) {
				//读取记录
				replaysystem.readRecord();
			} else {
				if (this.isAllEnemiesDead() && monsterChapter == false) {
					this.initMonsterChapter();
					new Thread(monster).start();
				}
				//存放记录
				replaysystem.record();
			}

			if (this.isAllHuluwaDead()) {
				this.isLose = true;
				this.gameOver();
			} else if (monster.isDead()) {
				this.isWin = true;
				this.gameOver();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		repaint();
	}

	//开始攻击
	public void beginAttack() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).initState();
			enemies.get(i).beginAttack();
			new Thread(enemies.get(i)).start();
		}

		for (int i = 0; i < brothers.size(); i++) {
			brothers.get(i).initState();
			brothers.get(i).beginAttack();
			new Thread(brothers.get(i)).start();
		}
		monster.initState();
		isBegin = true;
		isWin = false;
		isLose = false;
		timer.start();
	}

	// 开始回放
	public void beginReplay() throws IOException {
		this.fileDialog.setVisible(true);
		if (fileDialog.getDirectory() == null || fileDialog.getFile() == null) {
			return;
		}
		String filename = this.fileDialog.getDirectory() + this.fileDialog.getFile();
		replaysystem.replay(filename);

	   	this.isReplay = true;
		this.isWin = false;
		this.isLose = false;
		timer.start();
	}

	public void gameOver() {
		timer.stop();
		isBegin = false;
		isReplay = false;
		this.replaysystem.saveRecord();
	}

	public boolean isAllHuluwaDead() {
		for (int i = 0; i < brothers.size(); i++) {
			if (brothers.get(i).isDead() == false)
				return false;
		}
		return true;
	}

	public boolean isAllEnemiesDead() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isDead() == false)
				return false;
		}
		return true;
	}

	class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_L) {
				if (isBegin == true)
					return;
				try {
					beginReplay();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			} else if (key == KeyEvent.VK_SPACE) {
				if (isReplay == true)
					return;
				if(isBegin == false && timeCount>0) {
					timeCount--;
					beginAttack();
				}
			}
			repaint();
		}
	}

}
