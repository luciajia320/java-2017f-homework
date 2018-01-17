
public class HuLuWa extends Creature {
	private int number;
	private String PaiHang;
	private String color;
	public HuLuWa(int number) {
		super("HuLuWa");
		this.number=number;
		switch(number) {
		case 1: {
			PaiHang="LaoDa";
			color="RED";
			break;
		}
		case 2: {
			PaiHang="LaoEr";
			color="ORANGE";
			break;
		}
		case 3: {
			PaiHang="LaoSan";
			color="YELLOW";
			break;
		}
		case 4: {
			PaiHang="LaoSi";
			color="GREEN";
			break;
		}
		case 5: {
			PaiHang="LaoWu";
			color="CYAN";
			break;
		}
		case 6: {
			PaiHang="LaoLiu";
			color="BLUE";
			break;
		}
		case 7: {
			PaiHang="LaoQi";
			color="PURPLE";
			break;
		}
		}
	}
	public int getNumber() {
		return number;
	}
	public String getPaihang() {
		return PaiHang;
	}
	public String getColor() {
		return color;
	}
	@Override
	public void report() {
		System.out.print("^");
	}
}
