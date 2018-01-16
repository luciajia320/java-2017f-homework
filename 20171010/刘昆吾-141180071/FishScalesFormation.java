
public class FishScalesFormation extends Formation{
	public FishScalesFormation(ScorpionFaction matter) {
		super(7,5);
		this.content[3][0] = matter.getLeader();
		this.content[5][1] = matter.getLittleMinions();
		this.content[1][2] = this.content[3][2] = this.content[5][2] = matter.getLittleMinions();
		for(int i = 0;i < 7;i += 2)
			this.content[i][3] = matter.getLittleMinions();
		this.content[3][4] = matter.getLittleMinions();
	}
}
