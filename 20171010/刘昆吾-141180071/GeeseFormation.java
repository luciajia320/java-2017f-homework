
public class GeeseFormation extends Formation{
	public GeeseFormation(ScorpionFaction matter) {
		super(5,5);
		for(int i = 0;i < 5;++i)
			this.content[4-i][i] = matter.getLittleMinions();
		this.content[4][0] = matter.getLeader();
	}
}
