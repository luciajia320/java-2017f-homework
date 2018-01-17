
public class position {
	public int x_post;
	public int y_post;
	public creature Cre;
	public boolean is_hasCreature;
	position(){
		x_post=0;
		y_post=0;
		is_hasCreature=false;
	}
	position(int x,int y){
		x_post=x;
		y_post=y;
		Cre=null;
		is_hasCreature=false;
	}
	public void setPosition(int x,int y) {
		x_post=x;
		y_post=y;
	}
	public boolean setCreature(creature cre) {
		if(!is_hasCreature) {
			Cre=cre;
			is_hasCreature=true;
			return true;
		}
		else return false;
	}
	public void deleteCreature() {
		Cre=null;
		is_hasCreature=false;
	}
	public boolean GetIsHasCreature() {
		return is_hasCreature;
	}
	public int GetPosition_X() {
		return x_post;
	}
	public int GetPosition_Y() {
		return y_post;
	}
	public creature GetCre() {
		if(is_hasCreature) {
			return Cre;
		}
		else return null;
	}
	public void printPosition() {
		System.out.print(this.toString());
	}
	public String toString() {
		if(is_hasCreature) {
			return this.Cre.ToString();
		}
		else
			return "      ";
	}
}
