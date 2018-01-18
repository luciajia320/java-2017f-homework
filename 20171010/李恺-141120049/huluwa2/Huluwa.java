package huluwa2;

enum HuluwaCharacterastic {
	RED("老大", "红色", 1), ORANGE("老二", "橙色", 2), YELLOW("老三", "黄色", 3), GREEN("老四", "绿色", 4), CYANBLUE("老五", "青色",
			5), BLUE("老六", "蓝色", 6), PURPLE("老七", "紫色", 7);

	private String rank;
	private String color;
	private int num;

	private HuluwaCharacterastic(String rank, String color, int num) {
		this.rank = rank;
		this.color = color;
		this.num = num;
	}

	public String getRank() {
		return rank;
	}

	// 返回排行
	public String getColor() {
		return color;
	}

	// 返回顏色
	public int getNum() {
		return num;
	}

	// 返回順序
	public class Huluwa extends Creature {
		private HuluwaCharacterastic huluwacharacterastic;
		private int num;

		public HuluwaCharacterastic getCharacterastic() {
			return huluwacharacterastic;
		}

		public int getNum() {
			return num;
		}

		Huluwa(int num, HuluwaCharacterastic huluwacharacterastic) {
			this.species = Species.HULUWA;
			this.num = num;
			this.huluwacharacterastic = huluwacharacterastic;
		}

		@Override
		public void report() {
			System.out.print(this.getNum());// 得到順序
			// TODO 自动生成的方法存根

		 }
	}
}
