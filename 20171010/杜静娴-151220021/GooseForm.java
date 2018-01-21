public class GooseForm extends Formation {
    public GooseForm(int x, int y, Creature[] men) {
        super(5, 5, "gooseform");
        this.setPos(5,2);
        for(int i = 0;i < 5; i ++){
            content[i][4-i] = men[i];
        }
    }
}
