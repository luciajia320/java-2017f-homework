
public class LaoYeye extends Creature{
	private String name;
    private Position position;
    
    LaoYeye() {
    	this.name = "老爷爷";
    	this.position = null;
    }
    
	@Override
	public void print() {
		System.out.print(this);
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
        position.setHolder(this);
	}

	@Override
	public Position getPosition() {
		return this.position;
	}
	
	@Override
    public String toString(){
        return this.name+"(" + this.position.getX()+","+this.position.getY()+");";
	}
}
