import java.util.ArrayList;

public class HeyiFormation extends Formation{
	public HeyiFormation(ArrayList<Creature> creatures){
		super(7,4);
		this.content[3][3]=creatures.get(0).getMatter();
		for(int i=0;i<3;i++) {
			this.content[i][i] = creatures.get(i+1).getMatter();
			this.content[i][6-i] = creatures.get(6-i).getMatter();
		}
	}
}
