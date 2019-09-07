
public class Grandpa extends Creature{
	private int age;
	
	Grandpa()
	{
		super("үү");
	}

	public String toString() //үү˵����λ���ý���үү������ү������
	{
		return "ү";
	}
	
	public HuLuWa[] huluwaBorn()	//үү���º�«�ٺ󳤳��˺�«�ޣ���ʾ������
	{
		HuLuWa []huluwa=new HuLuWa[HuLuWa.huluwaNum];
		huluwa[0]=new HuLuWa(3,HuLuWa.Color.yellow,"");
		huluwa[1]=new HuLuWa(5,HuLuWa.Color.cyan,"");
		huluwa[2]=new HuLuWa(6,HuLuWa.Color.blue,"");
		huluwa[3]=new HuLuWa(1,HuLuWa.Color.red,"");
		huluwa[4]=new HuLuWa(2,HuLuWa.Color.orange,"");
		huluwa[5]=new HuLuWa(7,HuLuWa.Color.purple,"");
		huluwa[6]=new HuLuWa(4,HuLuWa.Color.green,"");
		return huluwa;
	}
	
	public void callHuluwa(Land land,HuLuWa []huluwa)//���к�«��
	{
		for(int i=1;i<=HuLuWa.huluwaNum;i++)
			land.getPosition()[0][i].setObj(huluwa[i-1]);
	}
	
	public void followHuluwa(Land land,Position grandpaSrcPos)	//�ߵ���«���м�Ϊ����͹ľ�
	{
		grandpaSrcPos.popObj();
		land.getPosition()[4][2].setObj(this);
	}
	
	
	public void singleLine(Land land,HuLuWa []huluwa)	//�ú�«�ްڳ�����
	{	
		for(int i=1;i<=HuLuWa.huluwaNum;i++)
		{
			land.getPosition()[0][i].popObj();
			land.getPosition()[i][0].setObj(huluwa[i-1]);
		}
		
		//��«�޸������е���˳��
		for(int i=1;i<HuLuWa.huluwaNum;i++)
			for(int j=i+1;j<HuLuWa.huluwaNum+1;j++)
			{
				HuLuWa cur=(HuLuWa)land.getPosition()[i][0].getObj();
				HuLuWa other=(HuLuWa)land.getPosition()[j][0].getObj();
				//��cur��«�޵����б�other��«��С������Ҫ��other��«�޻���λ��
				if(cur.compareTo(other)<0)
				{
					Position temp=land.emptyPosition();
					//cur��«���뿪ԭ���õ��ط����ߵ�һ����λ��
					land.getPosition()[i][0].popObj();
					temp.setObj(cur);
					//other��«���뿪ԭ���õ��ط����ߵ�cur��«��ԭ����λ��
					land.getPosition()[j][0].popObj();
					land.getPosition()[i][0].setObj(other);
					//cur��«���ߵ�other��«��ԭ����λ��
					temp.popObj();
					land.getPosition()[j][0].setObj(cur);				
				}
			}
		
	}
	
}
