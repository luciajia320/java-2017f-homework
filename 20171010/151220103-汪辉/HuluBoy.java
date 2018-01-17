
public class HuluBoy extends creature{
	public enum Color{Red,Orange,Yellow,Green,Blue,Indigo,Violet}
	public enum Rank{BigBrother,SecBrother,ThirdBrother,ForthBrother,FifthBrother,SixBrother,SevenBrother}
	private Color color;
	private Rank rank;
	private int position; 

	HuluBoy(int i)
	{

		switch(i) {

		case 0:name="BigBro";color=Color.Red;rank=Rank.BigBrother;break;

		case 1:name="SecBro";color=Color.Orange;rank=Rank.SecBrother;break;

		case 2:name="ThrBro";color=Color.Yellow;rank=Rank.ThirdBrother;break;

		case 3:name="ForBro";color=Color.Green;rank=Rank.ForthBrother;break;
		case 4:name="FifBro";color=Color.Blue;rank=Rank.FifthBrother;break;

		case 5:name="SixBro";color=Color.Indigo;rank=Rank.SixBrother;break;

		case 6:name="SevBro";color=Color.Violet;rank=Rank.SevenBrother;break;

		}
		this.setProperties(true);
	}

	

	HuluBoy(){

		color=null;

		rank=null;

		position=-1;
		this.setProperties(true);

	}



	public void printBrother(){

		System.out.print(color.toString()+"  ");

	    System.out.print(rank.toString());

	}

	

	public String getName() {

		return rank.toString();

	}

	

	public void setPosition(int i) {

		position=i;

	}

	

	public int colorCompare(HuluBoy b) {

		return color.compareTo(b.color);

	}

	

	public int RankCompare(HuluBoy B) {

		return rank.compareTo(B.rank);

	}

	

	public int getposition() {

		return position;

	}

	public String getColor() {

		return color.toString();

	}

	public void ChangeBoy(HuluBoy B) {

		//System.out.print(this.rank.toString()+": "+this.color.toString()+": from "+this.position+" to "+B.position+" ;  ");

	//	System.out.print(B.rank.toString()+": "+B.color.toString()+": from "+B.position+" to "+this.position+" ;  ");

		HuluBoy temp=new HuluBoy();

		temp.color=this.color;

		temp.rank=this.rank;

		temp.position=this.position;

		this.color=B.color;

		this.rank=B.rank;

		this.position=B.position;

		B.color=temp.color;

		B.rank=temp.rank;

		B.position=temp.position;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
