
public class Scorpion extends Creature{
	Scorpion()
	{
		super("Ы�Ӿ�");
	}
	
	public String toString()
	{
		return "Ы";
	}
	
	public Lesser[] callLesser(int lesserNum)	//��С�ඳ�ս
	{
		Lesser lessers[]=new Lesser[lesserNum];
		for(int i=0;i<lesserNum;i++)
			lessers[i]=new Lesser();
		return lessers;
	}
	
	
	public Position EchelonArray(Land land,Position scorpionSrcPos) //����С����������
	{
		int lesserNum=6;
		Lesser lessers[]=callLesser(lesserNum);
		//��������ϰ벿��С���Ⱦ�λ
		Position curPos=land.getPosition()[1][land.getWidth()-1];
		for(int i=0;i<lesserNum/2;i++)
		{
			curPos.setObj(lessers[i]);
			curPos=land.getPosition()[curPos.getX()+1][curPos.getY()-1];
		}
		//Ы�Ӿ��뿪ԭ����λ�ã��������м�
		scorpionSrcPos.popObj();
		curPos.setObj(this);	
		Position rtn=curPos;
		//��������°벿��С�඾�λ
		for(int i=lesserNum/2;i<lesserNum;i++)
		{
			curPos=land.getPosition()[curPos.getX()+1][curPos.getY()-1];
			curPos.setObj(lessers[i]);
		}
		return rtn;
				
	}
	
	public Position CrescentMoonArray(Land land,Position scorpionSrcPos) //����С���ŷ�����
	{
		//�Ȱ�ԭ����С������
		for(int i=0;i<land.getLength();i++)
			for(int j=0;j<land.getWidth();j++)	
			{
				if(land.getPosition()[i][j].getObj() instanceof Lesser)
					land.getPosition()[i][j].popObj();
			}
		
		//�ű�����
		int lesserNum=7,curLesserNum=0;
		Lesser lessers[]=callLesser(lesserNum);
		Position upPos=land.getPosition()[2][land.getWidth()-3],downPos=land.getPosition()[6][land.getWidth()-3];
		upPos.setObj(lessers[curLesserNum++]);downPos.setObj(lessers[curLesserNum++]);
		
		upPos=land.getPosition()[upPos.getX()+1][upPos.getY()-1];downPos=land.getPosition()[downPos.getX()-1][downPos.getY()-1];
		upPos.setObj(lessers[curLesserNum++]);downPos.setObj(lessers[curLesserNum++]);
		
		upPos=land.getPosition()[upPos.getX()][upPos.getY()+2];downPos=land.getPosition()[downPos.getX()][downPos.getY()+2];
		upPos.setObj(lessers[curLesserNum++]);downPos.setObj(lessers[curLesserNum++]);
		
		upPos=land.getPosition()[upPos.getX()+1][upPos.getY()-3];downPos=land.getPosition()[downPos.getX()-1][downPos.getY()+1];
		downPos.setObj(lessers[curLesserNum++]);
		scorpionSrcPos.popObj();
		upPos.setObj(this);	

		return upPos;
	}
	
}
