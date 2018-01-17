
import java.util.Random;

public class HuluFaction {

	private HuluBrother[] brothers;
	private HuluPosition[] position;
	public HuluFaction(){
		brothers=new HuluBrother[8];//放置7个葫芦娃
		position=new HuluPosition[8];//7个位置
	}
	//添加一个葫芦娃
	public void addOneHulu(String name,Order order,Color color,int curPos,int num){
		brothers[num]=new HuluBrother(name,order,color,curPos);	
		position[curPos] = new HuluPosition(num);
	}
	//初始化
	public void  huluInitialize(){	
		boolean []isEmpty = new boolean[8];
		for(int i=1;i<=7;i++)
			isEmpty[i]=true;
		int []randNum=new int[8];
		Random random = new Random();
		for(int i=1;i<=7;i++){
			int rand = random.nextInt(7) + 1;
		    while(isEmpty[rand] == false)
				rand = random.nextInt(7) + 1;
			randNum[i] = rand;
			isEmpty[rand] = false;
		}
		//生成葫芦娃
		addOneHulu("红娃",Order.一,Color.红色,randNum[1],1);
		addOneHulu("橙娃",Order.二,Color.橙色,randNum[2],2);
		addOneHulu("黄娃",Order.三,Color.黄色,randNum[3],3);
		addOneHulu("绿娃",Order.四,Color.绿色,randNum[4],4);
		addOneHulu("青娃",Order.五,Color.青色,randNum[5],5);
		addOneHulu("蓝娃",Order.六,Color.蓝色,randNum[6],6);
		addOneHulu("紫娃",Order.七,Color.紫色,randNum[7],7);
	}
	
	void swapPos(int pos1,int num1,int pos2,int num2) {
		brothers[num1].movePos(pos2);
		brothers[num2].movePos(pos1);	
		position[pos1].addHulu(num2);
		position[pos2].addHulu(num1);	
	}

	public void bubbleSort(){
		int num1,num2;
		for(int i=7;i>1;i--)
			for(int j=1;j<i;j++){						
				num1=position[j].getHuluNum();			
				num2=position[j+1].getHuluNum();
				if(brothers[num1].getOrder().ordinal()>brothers[num2].getOrder().ordinal()){			
					swapPos(j,num1,j+1,num2);
				}			
			}
	}

	

	public void subQuickSort(int low, int high) {		
		if(low < high) {			
			int pivotPos = low;		
			int pivot = position[low].getHuluNum(); 
	        int num1;		
			for(int i = pivotPos + 1;i <= high;++i) {				
				int num2 = position[i].getHuluNum();				
				if(brothers[num2].getColor().ordinal() < brothers[pivot].getColor().ordinal()) {					
					pivotPos++;					
					if(pivotPos != i) {					
						num1 = position[pivotPos].getHuluNum();					
						swapPos(pivotPos,num1,i,num2);				
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

	public void reportOrder(){
		for(int i=1;i<=7;i++){
			brothers[position[i].getHuluNum()].printOrder();
		}
	}
	
	public void reportColor(){
		for(int i=1;i<=7;i++){
			brothers[position[i].getHuluNum()].printColor();
		}
	}

	public HuluBrother[] getBrothers() {
		return brothers;
	}

}