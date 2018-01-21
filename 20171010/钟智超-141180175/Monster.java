public class Monster implements Creature{
	private String name;
	private String picture;
	private Position position;
	@Override
	public String getName(){
		return name;
	}
	@Override
	public String getPicture(){
		return picture;
	}
	@Override
	public void setPosition(Position position){
		this.position = position;
	}
	@Override
	public Position getPosition(){
		return position;
	}
	@Override
	public void outputPicture(){
		System.out.print(picture);
	}

	public Monster(){
		this.name = new String("Monster");
		this.picture = new String("虾");
	}
}