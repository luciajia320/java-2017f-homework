
public class FishScalesFormation extends Formation{
	public FishScalesFormation(String matter) {
		super(7,5);
		this.content[0][0] = "蛇";
		this.content[3][0] = "蝎";
		this.content[5][1] = matter;
		this.content[1][2] = this.content[3][2] = this.content[5][2] = matter;
		for(int i = 0;i < 7;i += 2)
			this.content[i][3] = matter;
		this.content[3][4] = matter;
	}
}
