
public class Formation {
	
	public enum FORMATION{CHANGSHE,HEYI,YANXING,SINGLE};
	private int posX[] = new int[20];
	private int posY[] = new int[20];
	
	Formation()
	{
		
	}
	
	public int[] getArrayPosX()
	{
		return posX;
	}
	public int[] getArrayPosY()
	{
		return posY;
	}
	
	public void setFormation(FORMATION formation,int length)
	{
		switch(formation)
		{
		case HEYI:
		{
			posX[0] = 0; posY[0] = 0;	
			for(int i = 1;i < length;++i)
			{
				if(i % 2 != 0)
				{
					int delta = (i+1) / 2;
					posX[i] = -delta * 2;
					posY[i] = -delta * 2;
				}
				else
				{
					int delta = i / 2;
					posX[i] = delta * 2;
					posY[i] = -delta * 2;
				}
			}
			break;
		}
		case YANXING:
		{	
			posX[0] = 0; posY[0] = 0;
			for(int i = 1;i < length;++i)
			{
				posX[i] = i * 2;
				posY[i] = -i * 2;
			}
			break;
		}
		case CHANGSHE:
		{
			
			posX[0] = 0; posY[0] = 0;
			for(int i = 1;i < length;++i)
			{
				posX[i] = i * 2;
				posY[i] = 0;
			}			
		}	
		case SINGLE:
		{
			posX[0] = 0; posY[0] = 0;
		}
		default:
		{	
			
		}
		
		}
	}
}
