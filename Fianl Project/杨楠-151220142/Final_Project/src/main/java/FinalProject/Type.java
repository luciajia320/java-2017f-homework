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
			System.out.print("��");
			break;
		case 1:
			System.out.print("��");
			break;
		case 2:
			System.out.print("��");
			break;
		case 3:
			System.out.print("��");
			break;
		case 4:
			System.out.print("��");
			break;
		case 5:
			System.out.print("��");
			break;
		case 6:
			System.out.print("��");
			break;
		case 7:
			System.out.print("��");
			break;
		case 8:
			System.out.print("ү");
			break;
		case 9:
			System.out.print("��");
			break;
		case 10:
			System.out.print("Ы");
			break;
		case 11:
			System.out.print("��");
			break;
		case -1:
			System.out.print("��");
			break;
		default:
			System.out.print("W");
			break;
		}
	}
}