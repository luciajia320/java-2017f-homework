import java.util.ArrayList;

public class Space {
	private int num;
	private ArrayList<ArrayList<position>> PositionArray;
	Space(int num){
		this.num=num;
		PositionArray=new ArrayList<ArrayList<position>>();
		for(int i=0;i<num;i++) {
			ArrayList<position> tmp=new ArrayList<position>();
			for(int j=0;j<num;j++) {
				position key=new position(i,j);
				tmp.add(key);
			}
			PositionArray.add(tmp);
		}
	}
	public boolean setACreature(creature a,int i,int j) {
		return PositionArray.get(i).get(j).setCreature(a);
	}
	public void printSpace() {
		for(int i=0;i<num;i++) {
			for(int j=0;j<num;j++) {
					PositionArray.get(i).get(j).printPosition();
					System.out.print("  ");
			}
			System.out.println();
		}
	}
	public void DeleteCreature(int i,int j) {
		PositionArray.get(i).get(j).deleteCreature();
	}
	public void clear() {
		for(int i=0;i<num;i++){
			for(int j=0;j<num;j++) {
				PositionArray.get(i).get(j).deleteCreature();
			}
		}
	}
	public ArrayList<ArrayList<position>> GetPositionArray(){
		return PositionArray;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Space Sp=new Space(10);
		Sp.printSpace();
		Sp.setACreature(new creature("´óÍÞ"), 3, 5);
		Sp.printSpace();
	}

}
