public class Huluwa implements Creature{
	private String name;
	private Position position;
	private int number;
	private COLOR color;
	private SENIORITY seniority;

	public Huluwa(COLOR color,SENIORITY seniority){
		this.color = color;
		this.seniority = seniority;
		this.number = seniority.ordinal() + 1;
		this.name = new String("Huluwa");
	}
	@Override
	public String getPicture(){
		return this.color.toString();
	}
	@Override
	public String getName(){
		return this.seniority.toString();
	}
	@Override
	public void outputPicture(){
		System.out.print(this.color.toString());
	}
	@Override
	public void setPosition(Position position){
		this.position = position;
	}
	@Override
	public Position getPosition(){
		return position;
	}

	public int getNumber(){
		return number;
	}
}

enum COLOR{
	赤,橙,黄,绿,青,蓝,紫
}

enum SENIORITY{
	大,二,三,四,五,六,七
}