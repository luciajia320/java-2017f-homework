
import java.util.Random;


//��Ϸ����

public class HuluGame {

	private HuluBrother[] brothers;//���鱣���«���ֵ�

	private HuluPosition[] position;//���鱣��1-7��λ��

	public HuluGame(){

		brothers=new HuluBrother[8];//��«�����1-7

		position=new HuluPosition[8];//λ�����1-7

	}

	//����һ����«��

	public void addOneHulu(String name,Order order,Color color,int curPos,int num){

		brothers[num]=new HuluBrother(name,order,color,curPos);
		
		position[curPos] = new HuluPosition(num);


	}

	

	//��ʼ��

	public void  huluInitialize(){
		
		boolean []isEmpty = new boolean[8];//��¼��λ�����Ƿ�Ϊ��

		for(int i=1;i<=7;i++)

			isEmpty[i]=true;

		int []randNum=new int[8];

		Random random = new Random();

		//��ʼλ����������������randNum������

		for(int i=1;i<=7;i++){

			int rand = random.nextInt(7) + 1;

		    while(isEmpty[rand] == false)

				rand = random.nextInt(7) + 1;

			randNum[i] = rand;

			isEmpty[rand] = false;

		}

		//�������к�«��

		addOneHulu("����",Order.�ϴ�,Color.��ɫ,randNum[1],1);

		addOneHulu("����",Order.�϶�,Color.��ɫ,randNum[2],2);

		addOneHulu("����",Order.����,Color.��ɫ,randNum[3],3);

		addOneHulu("����",Order.����,Color.��ɫ,randNum[4],4);

		addOneHulu("����",Order.����,Color.��ɫ,randNum[5],5);

		addOneHulu("����",Order.����,Color.��ɫ,randNum[6],6);

		addOneHulu("����",Order.����,Color.��ɫ,randNum[7],7);

	}
	
	//����pos1��pos2λ���ϵĺ�«��

	void swapPos(int pos1,int num1,int pos2,int num2) {
		
		//��«���ƶ�λ��

		brothers[num1].movePos(pos2);

		brothers[num2].movePos(pos1);	

		//λ���ϵĺ�«�޸ı�

		position[pos1].addHulu(num2);

		position[pos2].addHulu(num1);
		
	}

	//ð������

	public void bubbleSort(){

		int num1,num2;

		for(int i=7;i>1;i--)

			for(int j=1;j<i;j++){			
				
				//jλ�ú�j+1λ���ϵĺ�«�ޱ��
				
				num1=position[j].getHuluNum();
				
				num2=position[j+1].getHuluNum();

				if(brothers[num1].getOrder().ordinal()>brothers[num2].getOrder().ordinal()){			

					swapPos(j,num1,j+1,num2);

				}			

			}

	}

	

	//��������
	public void subQuickSort(int low, int high) {
		
		if(low < high) {
			
			int pivotPos = low;
			
			int pivot = position[low].getHuluNum(); //����λ���Ϻ�«�ޱ��
			
	        int num1;
			
			for(int i = pivotPos + 1;i <= high;++i) {
				
				int num2 = position[i].getHuluNum();
				
				if(brothers[num2].getColor().ordinal() < brothers[pivot].getColor().ordinal()) {
					
					pivotPos++;
					
					if(pivotPos != i) {
						
						num1 = position[pivotPos].getHuluNum();
						
						swapPos(pivotPos,num1,i,num2);//����iλ�ú�pivotPosλ���ϵĺ�«��
						
					}
				}
			}
			
			num1 = position[pivotPos].getHuluNum();
			
			swapPos(pivotPos,num1,low,pivot);
			
			subQuickSort(low,pivotPos-1);
			
			subQuickSort(pivotPos+1,high);
		}
	}

	public void quickSort(){

		subQuickSort(1, 7);

	}

	//1��7��λ�õĺ�«�ޱ�������

	public void reportOrder(){

		for(int i=1;i<=7;i++){

			brothers[position[i].getHuluNum()].printOrder();

		}

	}
	
	//1��7��λ�õĺ�«�ޱ�����ɫ

	public void reportColor(){

		for(int i=1;i<=7;i++){

			brothers[position[i].getHuluNum()].printColor();

		}

	}

}