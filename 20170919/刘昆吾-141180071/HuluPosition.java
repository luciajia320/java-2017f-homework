
//һ��λ�õ����Ժͷ���

public class HuluPosition {

	private boolean isEmpty;//��λ���Ƿ�Ϊ��

	private int huluNum;//�޵ı�ţ�������ޣ���Ϊ-1

	
	public HuluPosition(){


		huluNum=-1;

		isEmpty=true;

	}

	//��λ���Ϸ��ñ��Ϊi�ĺ�«��

	public HuluPosition(int i){


		huluNum=i;

		isEmpty=false;

	}

	

	//��Ӻ�«��

	public void addHulu(int huluNum){

		this.huluNum=huluNum;

		isEmpty=false;

	}

	

	//λ���Ƿ�Ϊ��

	public boolean isPosEmpty(){

		return (isEmpty==true);

	}

	//��ȡ��«�ޱ��
	public int getHuluNum(){

		return huluNum;

	}

}