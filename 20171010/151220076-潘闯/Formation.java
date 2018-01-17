package assignment3;

class Formation {
	
	public enum FORMATION{HEYI,YANXING,CHANGSHE,SINGLE};
	Position[] position = new Position[20];
	
	Formation()
	{
		for(int i = 0;i < position.length;++i)
			position[i] = new Position();
	}
	
	public Position[] getAllPos()
	{
		return position;
	}
		
	public void setFormation(FORMATION formation,int length)
	{
		
		switch(formation)
		{
		case HEYI:
		{
			position[0].setX(0);position[0].setY(0);
			for(int i = 1;i < length;++i)
			{

				if(i % 2 != 0)
				{
					int delta = (i+1) / 2;
					position[i].setX(-delta);
					position[i].setY(-delta);

				}
				else
				{
					int delta = i / 2;
					position[i].setX(-delta);
					position[i].setY(delta);
				}
			}
			break;
		}
		case YANXING:
		{	
			position[0].setX(0);position[0].setY(0);
			for(int i = 1;i < length;++i)
			{
				position[i].setX(i);
				position[i].setY(-i);
			}
			break;
		}
		case CHANGSHE:
		{
			position[0].setX(0);position[0].setY(0);
			for(int i = 1;i < length;++i)
			{
				position[i].setX(-i);
				position[i].setY(0);
			}			
		}	
		case SINGLE:
		{
			position[0].setX(0);position[0].setY(0);
		}
		default:
		{	
			
		}
		
		}	
	}
}
