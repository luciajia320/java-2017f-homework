package nju.huluwa;

public class ArrowFormation extends Formation {
	public ArrowFormation(String matter) {
		super(5, 5);
		for (int i = 0; i < 5; i++)
			content[2][i] = matter;
		for (int i = 0; i < 3; i++) {
			content[i][2 - i] = matter;
			content[2 + i][i] = matter;
		}
	}
}
