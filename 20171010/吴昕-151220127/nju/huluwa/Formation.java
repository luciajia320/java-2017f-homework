package nju.huluwa;

public class Formation {
	public static final String PLACE_HOLDER = "🈳";

	public Formation(int x, int y) {
		this.width = x;
		this.height = y;

		this.content = new String[x][y];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				content[i][j] = PLACE_HOLDER;
	}

	public Location getLocation() { return location; }
	public void setLocation(Location location) {
		this.location = location;
	}

	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }

	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }

	public String[][] getContent() { return content; }
	public void setContent(String[][] content) {
		this.content = content;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++)
				sb.append(content[i][j] + " ");
			sb.append("\n");
		}

		return sb.toString();
	}

	protected String[][] content;

	private Location location;
	private int width, height;
}
