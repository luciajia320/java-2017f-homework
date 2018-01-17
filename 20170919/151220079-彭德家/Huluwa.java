package myJavahomework;
enum Color{
	红,橙,黄,绿,蓝,靛,紫;
}
enum NO{
	老大,老二,老三,老四,老五,老六,老七
}

public class Huluwa {
	private String color;
	private String no;
	Huluwa(int i){
		color = Color.values()[i].toString();
		no = NO.values()[i].toString();
	}
	public int get_no() {
		return NO.valueOf(no).ordinal();
	}
	public int get_color() {
		return Color.valueOf(color).ordinal();
	}
	public void print_no() {
		System.out.print(no+" ");
	}
	public void print_color() {
		System.out.print(color+" ");
	}
	public void hop(int i,int j) {
		print_no();
		System.out.println(":" + i +"->" + j);
	}
}
