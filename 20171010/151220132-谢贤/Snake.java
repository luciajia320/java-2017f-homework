
public class Snake extends Creature
{
	Snake()
	{
		super("�߾�");
	}
	
	public String toString()
	{
		return "��";
	}
	
	public Position findScorpion(Land land)
	{
		for(int i=0;i<land.getLength();i++)
			for(int j=0;j<land.getWidth();j++)	
			{
				if(land.getPosition()[i][j].getObj() instanceof Scorpion)
					return land.getPosition()[i][j];
			}
		return null;
	}
	
	public Position followScorpion(Land land,Position snakeSrcPos)//����Ы�Ӿ�
	{
		Position scorpionPosition=findScorpion(land);//�ڴ����Ѱ��Ы�Ӿ�λ��
		if(scorpionPosition!=null)	//�ߵ�Ы�Ӿ����
		{
			if(land.getPosition()[scorpionPosition.getX()][scorpionPosition.getY()+1].isEmpty())
			{
				snakeSrcPos.popObj();
				land.getPosition()[scorpionPosition.getX()][scorpionPosition.getY()+1].setObj(this);
				return land.getPosition()[scorpionPosition.getX()][scorpionPosition.getY()+1];
			}
			else 
			{
				snakeSrcPos.popObj();
				land.getPosition()[scorpionPosition.getX()][scorpionPosition.getY()-1].setObj(this);
				return land.getPosition()[scorpionPosition.getX()][scorpionPosition.getY()-1];
			}
			
		}
		return null;		
	}
	
}
