import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReplaySystem {

	private ArrayList<Creature> brothers;
	private ArrayList<Creature> enemies;
	private Monster snake;

	private BufferedReader br;
	private ArrayList<String> allRecord = new ArrayList<String>();

	public ReplaySystem(ArrayList<Creature> brothers, ArrayList<Creature> enemies, Monster snake) {
		this.brothers = brothers;
		this.enemies = enemies;
		this.snake = snake;
	}

	public void replay(String filename) throws IOException {
		br = new BufferedReader(new FileReader(filename));
	}

	public void readRecord() throws IOException {
		String oneRecord = br.readLine();
		String[] s = null;
		if (oneRecord == null)
			return;
		s = oneRecord.split(" ");
		if (s.length != 15 * 4) {
			System.out.println("record wrong");
			return;
		}
		int index = 0;
		int x, y, state;
		boolean visible;
		for (int i = 0; i < brothers.size(); i++) {
			x = Integer.parseInt(s[index++]);
			y = Integer.parseInt(s[index++]);
			state = Integer.parseInt(s[index++]);
			visible = Boolean.parseBoolean(s[index++]);
			brothers.get(i).getEnergyBlast().setX(x);
			brothers.get(i).getEnergyBlast().setY(y);
			brothers.get(i).setState(state);
			brothers.get(i).getEnergyBlast().setEBvisible(visible);
		}
		for (int i = 0; i < enemies.size(); i++) {
			x = Integer.parseInt(s[index++]);
			y = Integer.parseInt(s[index++]);
			state = Integer.parseInt(s[index++]);
			visible = Boolean.parseBoolean(s[index++]);
			enemies.get(i).getEnergyBlast().setX(x);
			enemies.get(i).getEnergyBlast().setY(y);
			enemies.get(i).setState(state);
			enemies.get(i).getEnergyBlast().setEBvisible(visible);
		}
		x = Integer.parseInt(s[index++]);
		y = Integer.parseInt(s[index++]);
		state = Integer.parseInt(s[index++]);
		visible = Boolean.parseBoolean(s[index++]);
		snake.getEnergyBlast().setX(x);
		snake.getEnergyBlast().setY(y);
		snake.setState(state);
		snake.getEnergyBlast().setEBvisible(visible);
	}

	public void record() throws IOException {
		StringBuffer record = new StringBuffer();
		int x, y, state;
		boolean visible;
		String oneRecord;
		for (int i = 0; i < brothers.size(); i++) {
			state = brothers.get(i).getState();
			x = brothers.get(i).getEnergyBlast().x();
			y = brothers.get(i).getEnergyBlast().y();
			visible = brothers.get(i).getEnergyBlast().getEBvisible();
			oneRecord = "" + x + " " + y + " " + state + " " + visible + " ";
			record.append(oneRecord);
		}
		for (int i = 0; i < enemies.size(); i++) {
			state = enemies.get(i).getState();
			x = enemies.get(i).getEnergyBlast().x();
			y = enemies.get(i).getEnergyBlast().y();
			visible = enemies.get(i).getEnergyBlast().getEBvisible();
			oneRecord = "" + x + " " + y + " " + state + " " + visible + " ";
			record.append(oneRecord);
		}
		state = snake.getState();
		x = snake.getEnergyBlast().x();
		y = snake.getEnergyBlast().y();
		visible = snake.getEnergyBlast().getEBvisible();
		oneRecord = "" + x + " " + y + " " + state + " " + visible + " ";
		record.append(oneRecord);
		this.allRecord.add(record.toString());
	}

	public void saveRecord() {
		FileWriter f;
		BufferedWriter bw;
		try {
			f = new FileWriter("record.txt");
			bw = new BufferedWriter(f);
			for (String record : allRecord) {
				bw.write(record.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("record file path wrong!");
			e.printStackTrace();
		}
	}
}
