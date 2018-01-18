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
			System.out.print("Îï");
			break;
		case 1:
			System.out.print("ÍÞ");
			break;
		case 2:
			System.out.print("Ò¯");
			break;
		case 3:
			System.out.print("Éß");
			break;
		case 4:
			System.out.print("Ð«");
			break;
		case 6:
			System.out.print("ºì");
			break;
		case 7:
			System.out.print("³È");
			break;
		case 8:
			System.out.print("»Æ");
			break;
		case 9:
			System.out.print("ÂÌ");
			break;
		case 10:
			System.out.print("Çà");
			break;
		case 11:
			System.out.print("À¶");
			break;
		case 12:
			System.out.print("×Ï");
			break;
		case 5:
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
