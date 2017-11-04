package lyc.hw;

public class Formation {
    public static final String holder = "\uD83C\uDE33";
    private Location location;
    private int width;
    private int height;
    protected String[][] contents;

    public Formation(int width, int height) {
        this.width = width;
        this.height = height;
        this.contents = new String[width][height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.contents[i][j] = holder;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String[][] getContents() {
        return contents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                sb.append(contents[i][j] + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
