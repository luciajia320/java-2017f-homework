
public class GeeseFormation extends Formation{
	public GeeseFormation(String matter) {
		super(5,5);
		for(int i = 0;i < 5;++i)
			this.content[4-i][i] = matter;
		this.content[4][0] = "蝎";
		this.content[0][0] = "蛇";
	}
}
