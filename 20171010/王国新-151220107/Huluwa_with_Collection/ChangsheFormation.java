import java.util.ArrayList;

public class ChangsheFormation extends Formation {
	public ChangsheFormation(ArrayList<Creature> creatures){
		super(1,7);
		for(int i=0;i<7;i++) {
			this.content[i][0]=creatures.get(i).getMatter();
		}
	}
}
