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
		Monster C=new Monster("蝎");
		C.creatSM();
		Snake S=new Snake("蛇");
		Grandpa G=new Grandpa("爷");
		
		

		A.AssignH(God.getHuluM(),3,5);
		
		A.AssignM(C,13,10);		//设定初始位置
		C.setSMpos(2);			//布阵
		C.enterField(A);		//Monster进入战场
		
		A.AssignG(G,2,8);
		G.enterField(A);
		
		A.AssignS(S,13,6);
		S.enterField(A);
		
		A.show();
		A.clear();//战场清理
		
		System.out.println("#######################################################");
		
		A.AssignH(God.getHuluM(),4,6);
		
		A.AssignM(C,12,12);		//设定初始位置
		C.setSMpos(4);			//布阵
		C.enterField(A);		//Monster进入战场
		
		A.AssignG(G,2,8);
		G.enterField(A);
		
		A.AssignS(S,13,6);
		S.enterField(A);
		
		A.show();
		A.clear();//战场清理
		
		System.out.println("#####################################################");
		
		A.AssignH(God.getHuluM(),4,6);
		
		A.AssignM(C,12,12);		//设定初始位置
		C.setSMpos(6);			//布阵
		C.enterField(A);		//Monster进入战场
		
		A.AssignG(G,2,8);
		G.enterField(A);
		
		A.AssignS(S,13,6);
		S.enterField(A);
		
		A.show();
		A.clear();//战场清理
		
		
		
		
		
		//System.out.println("先看下初始情况：");
		//God.Show();
		
		/*System.out.println("集合了，看下他们位置发生了那些变化：");
		God.assemble();
		System.out.println("集合完毕，看下他们怎么站队的：");
		God.Show();
		
		System.out.println("冒泡排序后，队列发生的变化：");
		God.BubbleSort();
		System.out.println("排序完毕，看下他们怎么站队的：");
		God.Show();
		
		//解散
		
		System.out.println("集合了，看下他们位置发生了那些变化：");
		God.assemble();
		System.out.println("集合完毕，看下他们怎么站队的：");
		God.Show();
		
		
		System.out.println("快速排序后，队列发生的变化：");
		God.Quicksort();
		System.out.println("排序完毕，看下他们怎么站队的：");
		God.Show();*/

	}

}