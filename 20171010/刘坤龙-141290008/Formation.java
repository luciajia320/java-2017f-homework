package lonhh.huluwa;


public class Formation {

    public static final String PLACE_HOLDER = "🈳";
    private Position location;

    public Position getLocation() {
        return location;
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private int width;
    private int height;

    public Creature[][] getContent() {
        return content;
    }

    public void setContent(Creature[][] content) {
        this.content = content;
    }

    protected Creature[][] content;

    public Formation(int x, int y) {
        this.width = x;
        this.height = y;

        this.content = new Creature[x][y];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                content[i][j] = null;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if(content[i][j] == null)
                    sb.append(PLACE_HOLDER + " ");
                else
                    sb.append(content[i][j] + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
