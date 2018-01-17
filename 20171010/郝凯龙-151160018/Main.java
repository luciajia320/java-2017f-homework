public class Main
{
	public static void main(String[] args)
	{
		Grandpa grandpa=new Grandpa();//爷爷
		Hulu brothers[]=new Hulu[7];//葫芦七兄弟
		brothers[0]=new Hulu(1,"一","红");
		brothers[1]=new Hulu(2,"二","橙");
		brothers[2]=new Hulu(3,"三","黄");
		brothers[3]=new Hulu(4,"四","绿");
		brothers[4]=new Hulu(5,"五","青");
		brothers[5]=new Hulu(6,"六","蓝");
		brothers[6]=new Hulu(7,"七","紫");
		Snake snake=new Snake();//蛇精
		Scorpion scorpion=new Scorpion();//蝎子精
		Soldier soldiers[]=new Soldier[9];//9个小喽罗		
		for(int i=0;i<9;++i)
			soldiers[i]=new Soldier();
		//System.out.println("hkl"+soldiers[3].getName());
		Space battlefield=new Space(21);//一个21*21的战场

		/*长蛇阵VS锋矢阵*/
		System.out.println("长蛇阵VS锋矢阵");

		grandpa.sort(brothers);
		battlefield.IFormation(brothers,7,grandpa);
		battlefield.AFormation(scorpion,soldiers,9,snake);
		/*长蛇阵VS鹤翼阵*/
		System.out.println("长蛇阵VS鹤翼阵");

		battlefield.VFormation(scorpion,soldiers,9,snake);

		/*战争结束，蛇精关键时刻使用玉如意逃脱了追击，以后定会有一场血战*/

	}
}
