
public class SideDoorFormation extends Formation{
	public SideDoorFormation(ScorpionFaction matter) {
		super(5,5);
		this.content[1][3] = this.content[3][1] = matter.getLittleMinions();
		for(int i = 2;i >= 0;--i)
			this.content[i][2-i] = matter.getLittleMinions();
		for(int i = 2;i <= 4;++i)
			this.content[i][6-i] = matter.getLittleMinions();
		this.content[2][0] = matter.getLeader();
	}
}
