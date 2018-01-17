public class Hulu extends Creature
{
	private int key;//关键字
	private String rank;//排行
	private String color;//颜色
	public Hulu()
	{
		key=-1;
		rank=null;
		color=null;
	}
	public Hulu(int _key,String _rank,String _color)
	{
		key=_key;
		rank=_rank;
		color=_color;
	}
	public String getName()
	{
		return "Hulu";
	}
	public int getKey()
	{
		return key;
	}
	public String getRank()
	{
		return rank;
	}
	public String getColor()
	{
		return color;
	}
}
