
public class creature {
	protected String name;
	//private position Post;
	private boolean properties;
	creature(String name){
		this.name=name;
		//Post=post;
	}
	creature(){
		name="NoCrea";
		properties=true;
	}
	public String getName() {
		return name;
	}
//	public position GetPosition() {
//		return Post;
//	}
//	public void ChangePosition(position post) {
//		Post=post;
//	}
//	public void ChangePosition(int x,int y) {
//		Post=new position(x,y);
//	}
	public void setProperties(boolean bool) {
		properties=bool;
	}
	public void printCreature() {
		System.out.print(this.toString());
	}
	public String ToString() {
		return this.name;
	}
	public boolean GetProperties() {
		return properties;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
