import java.util.ArrayList;

public class YulinFormation extends Formation{
	public YulinFormation(ArrayList<Creature> creatures){
		super(5,4);
		this.content[3][2]=creatures.get(0).getMatter();
		this.content[2][1]=creatures.get(1).getMatter();
		this.content[2][3]=creatures.get(2).getMatter();
		this.content[1][0]=creatures.get(3).getMatter();
		this.content[1][2]=creatures.get(4).getMatter();
		this.content[1][4]=creatures.get(5).getMatter();
		this.content[0][2]=creatures.get(6).getMatter();
	}
}