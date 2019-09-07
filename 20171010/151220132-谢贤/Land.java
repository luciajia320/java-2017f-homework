
public class Land 
{
	private int length;	//��صĳ�
	private int width;	//��
	private Position [][]position;//�������
	Land(int length,int width)
	{
		this.length=length;
		this.width=width;
		position=new Position[length][width];
		for(int i=0;i<length;i++)
			for(int j=0;j<width;j++)	
			{
				position[i][j]=new Position(i,j);
				position[i][j].setX(i);
				position[i][j].setY(j);
				position[i][j].setEmpty(true);
			}
	}
	

	void showActivities(Grandpa grandpa,HuLuWa []huluwa,Scorpion scorpion,Snake snake)		//չʾ����ϵĻ
	{
		Position scorpionPos=position[4][width-2],snakePos=position[4][width-1];
		//Ы�Ӿ��ǳ�
		scorpionPos.setObj(scorpion);
		//�߾��ǳ�
		snakePos.setObj(snake);
		//үү�ǳ�
		Position grandpaPos=position[0][0];
		grandpaPos.setObj(grandpa);	
		
		//үү����������ԣ��Ͻ����к�«�޹�����æ
		grandpa.callHuluwa(this,huluwa);
		
		//��ʱ�ֳ������һ������
		Reporter reporter=new Reporter();
		//�ֳ����ߴ���һ�ź�«�޼�ææ���������վ�ӵ���Ƭ
		reporter.takePhoto(this,"��«�����վ��:");
		
		//үүָ�Ӻ�«�ްڳ���������Է�
		grandpa.singleLine(this,huluwa); 
		//����ץ�ĵ�����һ˲��
		reporter.takePhoto(this,"\n��«�޳�����:");
		
		//Ы�Ӿ���״��Ҳ����С�ධڳ�������
		scorpionPos=scorpion.EchelonArray(this,scorpionPos);
		//үү�ܵ���«���м�Ϊ��«�޼���
		grandpa.followHuluwa(this,grandpaPos);
		//�߾�Ҳ�ܵ�Ы�Ӿ��Ա�Ϊ��ľ�
		snakePos=snake.followScorpion(this,snakePos);	
		
		//�ֳ����߲��˲���ǵĿ�ˮ����������һ����
		reporter.takePhoto(this,"\n��һ�غϣ�������VS������:");
		
		//Ы�Ӿ�����«�޼�Ц������̫��һŭ֮�·ų���������Ϊ��˧�ķ�����
		scorpionPos=scorpion.CrescentMoonArray(this,scorpionPos);
		//�߾�������Ы�Ӿ�
		snakePos=snake.followScorpion(this,snakePos);	
		
		//�������΢΢һЦ����������������һ���ž���
		reporter.takePhoto(this,"\n�ڶ��غϣ�������VS������:");	
	}
	
	
	public Position emptyPosition()		//���������ϵ�һ����λ��
	{
		for(int i=0;i<length;i++)
			for(int j=0;j<width;j++)	
			{
				if(position[i][j].isEmpty())
					return position[i][j];
			}
		return new Position(length,width);
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Position[][] getPosition() {
		return position;
	}

	public void setPosition(Position[][] position) {
		this.position = position;
	}

	
}
