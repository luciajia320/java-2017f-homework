package lyc.hw;

public class SnakeFormation extends Formation {
    public SnakeFormation(String creature) {
        super(7, 1);
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                this.contents[i][j] = creature;
            }
        }
    }

}
