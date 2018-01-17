import java.util.ArrayList;

public class Changshe implements Formation {
    private int x, y;
    private int borderX, borderY;

    public void form(int length, Position position, Field field) {
        x = position.getX();
        y = position.getY();

        borderX = field.getSpaceX();
        borderY = field.getSpaceY();

        for (int i = 0; i < length; i++) {
            if (x >= 0 && x < borderX && y >= 0 && y < borderY) {
                char c = String.valueOf(i+1).charAt(0);
                field.modifyLevel(x+i, y, c);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBorderX() {
        return borderX;
    }

    public int getBorderY() {
        return borderY;
    }
}
