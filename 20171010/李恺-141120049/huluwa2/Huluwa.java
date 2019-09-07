package huluwa2;

enum HuluwaCharacterastic {
	RED("�ϴ�", "��ɫ", 1), ORANGE("�϶�", "��ɫ", 2), YELLOW("����", "��ɫ", 3), GREEN("����", "��ɫ", 4), CYANBLUE("����", "��ɫ",
			5), BLUE("����", "��ɫ", 6), PURPLE("����", "��ɫ", 7);

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

	// ��������
	public String getColor() {
		return color;
	}

	// �����ɫ
	public int getNum() {
		return num;
	}

	// �������
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
			System.out.print(this.getNum());// �õ����
			// TODO �Զ����ɵķ������

		 }
	}
}
