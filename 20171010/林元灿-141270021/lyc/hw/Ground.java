package lyc.hw;

import java.util.ArrayList;
import java.util.List;

public class Ground {
    private int width;
    private int height;
    private static final String terrain = "\uD83C\uDF3F";
    private List armies;

    public Ground(int width, int height) {
        this.width = width;
        this.height = height;
        this.armies = new ArrayList();
    }

    public void layout(Formation f, Location loc){
        Location previously_loc =f.getLocation();
        f.setLocation(loc);
        armies.add(f);
    }

    @Override
    public String toString() {
        String battlefield[][] = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                battlefield[i][j] = terrain;
            }
        }

        for (Object obj : armies) {
            Formation f = (Formation) obj;
            for (int i = 0; i < f.getWidth(); i++) {
                for (int j = 0; j < f.getHeight(); j++) {
                    battlefield[f.getLocation().getX() + i][f.getLocation().getY() + j] = f.getContents()[i][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                sb.append(battlefield[i][j] + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
