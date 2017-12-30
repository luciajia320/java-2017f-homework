package huluwa;

public class battle {
	public static void main(String[] args) {
		stage stg = new stage(10);
		huluwa.setkids();
		shejing Shejing = new shejing();
		xiezi Xiezi = new xiezi();
		yeye Yeye = new yeye();
		huluwa Huluwa = new huluwa(7);
		louluo Louluo = new louluo(6);
		Huluwa.setposition(0, 0, 0);
		Yeye.setposition(0, 9);
		Xiezi.setposition(6, 0);
		Louluo.setposition(6, 0, 2);
		Shejing.setposition(9, 9);
		stg.show();
		System.out.println("±‰ªª’Û∑®!!!");
		Xiezi.leaveposition();
		Louluo.leaveposition();
		Xiezi.setposition(9, 0);
		Louluo.setposition(8, 1, 1);
		stg.show();
	}
}
