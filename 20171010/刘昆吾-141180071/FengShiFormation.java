
public class FengShiFormation extends Formation{
	public FengShiFormation(ScorpionFaction matter) {
		super(9,6);
		for(int i = 0;i <6;++i)
			this.content[4][i] = matter.getLittleMinions();
		for(int i = 0;i <= 2;++i) {
			this.content[i][3-i] = matter.getLittleMinions();
			this.content[6+i][1+i] = matter.getLittleMinions();
		}
		this.content[4][0] = matter.getLeader();
	}
}
