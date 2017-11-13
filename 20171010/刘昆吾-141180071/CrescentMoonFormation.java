
public class CrescentMoonFormation extends Formation{
	public CrescentMoonFormation(String matter) {
		super(9,6);
		for(int i = 3;i <= 5;++i)
			for(int j = 0;j <= 1;++j)
				this.content[i][j] = matter;
		for(int i = 2;i <= 6;++i)
			this.content[i][2] = matter;
		for(int i = 1;i <= 2;++i) {
			this.content[i][3] = this.content[5+i][3] = matter;
		}
		this.content[1][4] = this.content[7][4] = matter;
		this.content[0][5] = this.content[8][5] = matter;
		this.content[4][0] = "蝎";
		this.content[0][0] = "蛇";
	}
}
