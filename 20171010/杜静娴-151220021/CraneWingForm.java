public class CraneWingForm extends Formation {
    public CraneWingForm(int x, int y, Creature[] men) {
        super(4, 7, "craneform");
        this.setPos(x,y);
        content[0][3] = men[0];
        for(int i = 1;i < 4; i ++){
            content[i][3-i] = men[2*i-1];
            content[i][3+i] = men[2*i];
        }
    }
}
