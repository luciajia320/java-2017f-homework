package javaH2;

public class sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		HuluM God=new HuluM();
		God.add(new Huluwa(NAME.values()[0],COLOR.values()[0]));
		God.add(new Huluwa(NAME.values()[1],COLOR.values()[1]));
		God.add(new Huluwa(NAME.values()[2],COLOR.values()[2]));
		God.add(new Huluwa(NAME.values()[3],COLOR.values()[3]));
		God.add(new Huluwa(NAME.values()[4],COLOR.values()[4]));
		God.add(new Huluwa(NAME.values()[5],COLOR.values()[5]));
		God.add(new Huluwa(NAME.values()[6],COLOR.values()[6]));
		
		field A=new field();
		Monster C=new Monster("Ы");
		C.creatSM();
		Snake S=new Snake("��");
		Grandpa G=new Grandpa("ү");
		
		

		A.AssignH(God.getHuluM(),3,5);
		
		A.AssignM(C,13,10);		//�趨��ʼλ��
		C.setSMpos(2);			//����
		C.enterField(A);		//Monster����ս��
		
		A.AssignG(G,2,8);
		G.enterField(A);
		
		A.AssignS(S,13,6);
		S.enterField(A);
		
		A.show();
		A.clear();//ս������
		
		System.out.println("#######################################################");
		
		A.AssignH(God.getHuluM(),4,6);
		
		A.AssignM(C,12,12);		//�趨��ʼλ��
		C.setSMpos(4);			//����
		C.enterField(A);		//Monster����ս��
		
		A.AssignG(G,2,8);
		G.enterField(A);
		
		A.AssignS(S,13,6);
		S.enterField(A);
		
		A.show();
		A.clear();//ս������
		
		System.out.println("#####################################################");
		
		A.AssignH(God.getHuluM(),4,6);
		
		A.AssignM(C,12,12);		//�趨��ʼλ��
		C.setSMpos(6);			//����
		C.enterField(A);		//Monster����ս��
		
		A.AssignG(G,2,8);
		G.enterField(A);
		
		A.AssignS(S,13,6);
		S.enterField(A);
		
		A.show();
		A.clear();//ս������
		
		
		
		
		
		//System.out.println("�ȿ��³�ʼ�����");
		//God.Show();
		
		/*System.out.println("�����ˣ���������λ�÷�������Щ�仯��");
		God.assemble();
		System.out.println("������ϣ�����������ôվ�ӵģ�");
		God.Show();
		
		System.out.println("ð������󣬶��з����ı仯��");
		God.BubbleSort();
		System.out.println("������ϣ�����������ôվ�ӵģ�");
		God.Show();
		
		//��ɢ
		
		System.out.println("�����ˣ���������λ�÷�������Щ�仯��");
		God.assemble();
		System.out.println("������ϣ�����������ôվ�ӵģ�");
		God.Show();
		
		
		System.out.println("��������󣬶��з����ı仯��");
		God.Quicksort();
		System.out.println("������ϣ�����������ôվ�ӵģ�");
		God.Show();*/

	}

}