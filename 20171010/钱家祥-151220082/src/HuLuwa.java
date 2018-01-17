/**
 * 
 */
enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum NUMBER {
    一, 二, 三, 四, 五, 六, 七
}
public class HuLuwa extends Creature  {
	private COLOR color;
    private NUMBER number;
    private Position position;
    
	HuLuwa(COLOR color, NUMBER number) {
        this.color = color;
        this.number = number;
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
        return this.number+"(" + this.color + ")(" + this.position.getX() + ","+this.position.getY()+")";
    }
}
