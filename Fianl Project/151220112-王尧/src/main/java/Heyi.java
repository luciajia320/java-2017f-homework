public class Heyi implements Formation{
    private int x, y;
    private int borderX, borderY;

    public void form(int length, Position position, Field field) {
        x = position.getX();
        y = position.getY();

        field.modifyLevel(x, y, '&');
        field.modifyLevel(x+1, y, '$');
        for (int i = 0; i < length; i++) {
            field.modifyLevel(x+1+i, y+1+i, '0');
            field.modifyLevel(x-i, y+1+i,'0');
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
