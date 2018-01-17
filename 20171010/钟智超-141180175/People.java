public class People implements Creature{
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
	
	public People(){
		this.name = new String("Human");
		this.picture = new String("爷");
	}
}