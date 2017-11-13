
public class SideDoorFormation extends Formation{
	public SideDoorFormation(String matter) {
		super(5,5);
		this.content[1][3] = this.content[3][1] = matter;
		for(int i = 2;i >= 0;--i)
			this.content[i][2-i] = matter;
		for(int i = 2;i <= 4;++i)
			this.content[i][6-i] = matter;
		this.content[2][0] = "蝎";
		this.content[0][0] = "蛇";
	}
}
