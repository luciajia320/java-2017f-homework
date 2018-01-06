public class Formation {

    public static String PLACE_HOLDER = "🈳";
    private Position beginposition; //一个阵型的起点（左上角
    private int width;
    private int height;

    public Position getBeginPosition() {
        return beginposition;
    }

    public void setBeginPosition(Position position) {
        this.beginposition = position;
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



    public String[][] getContent() {
        return content;
    }

    public void setContent(String[][] content) {
        this.content = content;
    }

    protected String[][] content;

    public Formation(int x, int y) {
        this.width = x;
        this.height = y;
        beginposition=null; //起始时还没有指定位置

        this.content = new String[x][y];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                content[i][j] = PLACE_HOLDER;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                sb.append(content[i][j] + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


}