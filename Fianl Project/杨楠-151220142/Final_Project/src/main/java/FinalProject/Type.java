package FinalProject;

public class Type {
	protected int t;
	protected int row;
	protected int col;
	public Type() {
		t = 0;
	}
	public void setposition(int x,int y) {
		System.out.println("to be override");
	}
	public void leaveposition() {
		System.out.println("to be override");
	}
	public void settype(int n) {
		t = n;
	}
	public int gettype() {
		return t;
	}
	public int getroop() {
		if(t<=8&&t>0)
			return 1;
		else if(t>8)
			return -1;
		else
			return 0;
	}
	public void showname() {
		switch(t) {
		case 0:
			System.out.print("ËÀ");
			break;
		case 1:
			System.out.print("ºì");
			break;
		case 2:
			System.out.print("³È");
			break;
		case 3:
			System.out.print("»Æ");
			break;
		case 4:
			System.out.print("ÂÌ");
			break;
		case 5:
			System.out.print("Çà");
			break;
		case 6:
			System.out.print("À¶");
			break;
		case 7:
			System.out.print("×Ï");
			break;
		case 8:
			System.out.print("Ò¯");
			break;
		case 9:
			System.out.print("Éß");
			break;
		case 10:
			System.out.print("Ð«");
			break;
		case 11:
			System.out.print("†ª");
			break;
		case -1:
			System.out.print("¿Õ");
			break;
		default:
			System.out.print("W");
			break;
		}
	}
}