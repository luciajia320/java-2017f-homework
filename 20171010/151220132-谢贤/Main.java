
public class Main {
	public static final int land_length = 15; 
	public static final int land_width = 15;
	public static void main(String[] args)
	 {	 
		 //�ܾúܾ���ǰ����һƬ����ĺ�«���
		 Land huluLand=new Land(land_length,land_width);
		 //����������Ы�Ӿ����߾��Լ�һλ��үү
		 Scorpion scorpion=new Scorpion();				
		 Snake snake=new Snake();
		 Grandpa grandpa=new Grandpa();
		 //��һ����үүżȻ������һ����«�٣�����ȥ�������˺�«�ޣ�	 
		 HuLuWa []huluwa=grandpa.huluwaBorn(); 			
		//��«�޵ĵ��������˺�«��صĳ���...
		 huluLand.showActivities(grandpa,huluwa,scorpion,snake);
		 
	 }	 
}
