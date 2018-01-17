

public class SnakeFormation extends Formation {
    public SnakeFormation(HuluFaction hulu) {
        super(7, 2);
        HuluBrother[] huluBrother = hulu.getBrothers();
        for(int i = 0;i < 7;++i) {
        	this.content[i][0] = huluBrother[i+1].getOrder().toString();
        }
        this.content[3][1] = "爷";
    }
}
