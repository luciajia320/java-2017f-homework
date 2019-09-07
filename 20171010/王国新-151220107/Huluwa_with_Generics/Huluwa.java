
public class Huluwa implements Creature, Comparable{

	private COLOR color;
	private SENIORITY seniority;
	String matter;

	Huluwa(COLOR color, SENIORITY seiority,String matter) {
		this.color = color;
		this.seniority = seiority;
		this.matter = matter;
	}

	public COLOR getColor() {
		return color;
	}

	public SENIORITY getSeniority() {
		return seniority;
	}

	@Override
	public String getMatter() {
		return matter;
	}
	
	@Override
	public void report() {
		System.out.print("@"+this.seniority.toString());
	}

	@Override
    public boolean biggerThan(Comparable brother){

        if (brother instanceof  Huluwa)
            return this.getSeniority().ordinal()> ((Huluwa) brother).getSeniority().ordinal();
        else
            return false;
    }

}

enum COLOR {
	��, ��, ��, ��, ��, ��, ��
}

enum SENIORITY {
	�ϴ�, �϶�, ����, ����, ����, ����, ����
}