
//一个位置的属性和方法

public class HuluPosition {

	private boolean isEmpty;//该位置是否为空

	private int huluNum;//娃的编号，如果无娃，则为-1

	
	public HuluPosition(){


		huluNum=-1;

		isEmpty=true;

	}

	//该位置上放置编号为i的葫芦娃

	public HuluPosition(int i){


		huluNum=i;

		isEmpty=false;

	}

	

	//添加葫芦娃

	public void addHulu(int huluNum){

		this.huluNum=huluNum;

		isEmpty=false;

	}

	

	//位置是否为空

	public boolean isPosEmpty(){

		return (isEmpty==true);

	}

	//获取葫芦娃编号
	public int getHuluNum(){

		return huluNum;

	}

}