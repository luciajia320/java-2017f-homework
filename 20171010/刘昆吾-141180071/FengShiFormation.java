
public class FengShiFormation extends Formation{
	public FengShiFormation(String matter) {
		super(9,6);
		for(int i = 0;i <6;++i)
			this.content[4][i] = matter;
		for(int i = 0;i <= 2;++i) {
			this.content[i][3-i] = matter;
			this.content[6+i][1+i] = matter;
		}
		this.content[4][0] = "蝎";
		this.content[0][0] = "蛇";
	}
}
