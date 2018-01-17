
public class ChongEFormation extends Formation{
	public ChongEFormation(ScorpionFaction matter) {
		super(2,6);
		for(int i = 1;i < 6;i += 2)
			this.content[0][i] = matter.getLittleMinions();
		for(int i = 0;i < 6;i += 2)
			this.content[1][i] = matter.getLittleMinions();
		this.content[1][0] = matter.getLeader();
	}

}
