package huluwa;

public class type {
	private int t;
	public type() {
		t = -1;
	}
	public void settype(int n) {
		t = n;
	}
	public int gettype() {
		return t;
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
			System.out.print("ү");
			break;
		case 3:
			System.out.print("��");
			break;
		case 4:
			System.out.print("Ы");
			break;
		case 6:
			System.out.print("��");
			break;
		case 7:
			System.out.print("��");
			break;
		case 8:
			System.out.print("��");
			break;
		case 9:
			System.out.print("��");
			break;
		case 10:
			System.out.print("��");
			break;
		case 11:
			System.out.print("��");
			break;
		case 12:
			System.out.print("��");
			break;
		case 5:
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
