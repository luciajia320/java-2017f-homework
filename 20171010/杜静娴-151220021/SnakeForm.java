public class SnakeForm extends Formation{
    public SnakeForm(int x, int y, Creature[] men) {
        super(1,7, "snakeform");
        this.setPos(x,y);
        for(int i = 0; i < 7; i ++)
            content[0][i] = men[i];
    }
}
