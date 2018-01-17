
public class HuluPosition {

	private boolean isEmpty;
	private int huluNum;

	public HuluPosition(){
		huluNum=-1;
		isEmpty=true;
	}

	public HuluPosition(int i){
		huluNum=i;
		isEmpty=false;
	}

	public void addHulu(int huluNum){
		this.huluNum=huluNum;
		isEmpty=false;
	}

	public boolean isPosEmpty(){
		return (isEmpty==true);
	}

	public int getHuluNum(){
		return huluNum;
	}

}