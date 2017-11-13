
public class ChongEFormation extends Formation{
	public ChongEFormation(String matter) {
		super(2,6);
		for(int i = 1;i < 6;i += 2)
			this.content[0][i] = matter;
		for(int i = 0;i < 6;i += 2)
			this.content[1][i] = matter;
		this.content[1][0] = "蝎";
		this.content[0][0] = "蛇";
	}

}
