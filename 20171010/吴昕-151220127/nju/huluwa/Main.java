package nju.huluwa;
public class Main {
	public static void main(String[] args) {
		Ground g = new Ground(10);

		try {
			SnakeFormation snake = new SnakeFormation("🍎");
			GooseFormation goose = new GooseFormation("🐸");

			g.layout(snake, new Location(2, 0));
			g.layout(goose, new Location(2, 6));
			g.layout(new SingleFormation("\uD83C\uDF37"), new Location(0, 0));
			g.layout(new SingleFormation("\uD83D\uDC51"), new Location(5, 5));
			System.out.println(g);
			System.out.println("Change formation:");

			try {
				g.clear();
			} catch(Exception e) {
				e.printStackTrace();
			}

			ArrowFormation arrow = new ArrowFormation("🐸");

			g.layout(snake, new Location(2, 0));
			g.layout(arrow, new Location(2, 4));
			g.layout(new SingleFormation("\uD83C\uDF37"), new Location(0, 0));
			g.layout(new SingleFormation("\uD83D\uDC51"), new Location(9, 9));
			System.out.print(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
