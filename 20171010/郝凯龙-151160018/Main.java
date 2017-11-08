public class Main
{
	public static void main(String[] args)
	{
		Grandpa grandpa=new Grandpa();//爷爷
		Hulu brothers[]=new Hulu[7];//葫芦七兄弟
		brothers[0]=new Hulu(1,"第一","红色");
		brothers[1]=new Hulu(2,"第二","橙色");
		brothers[2]=new Hulu(3,"第三","黄色");
		brothers[3]=new Hulu(4,"第四","绿色");
		brothers[4]=new Hulu(5,"第五","青色");
		brothers[5]=new Hulu(6,"第六","蓝色");
		brothers[6]=new Hulu(7,"第七","紫色");
		Snake snake=new Snake();//蛇精
		Scorpion scorpion=new Scorpion();//蝎子精
		Soldier soldiers[]=new Soldier[9];//9个小喽罗		
		for(int i=0;i<9;++i)
			soldiers[i]=new Soldier();
		//System.out.println("hkl"+soldiers[3].getName());
		Space battlefield=new Space(21);//一个21*21的战场

		//爷爷指挥葫芦娃排队
		grandpa.sort(brothers);
		//七个葫芦娃摆出长蛇阵,爷爷助阵
		battlefield.IFormation(brothers,7,grandpa);
		//蝎子精带领九个小喽罗摆出锋矢阵,蛇精助阵
		battlefield.AFormation(scorpion,soldiers,9,snake);
		//敌方溃不成军，变换为鹤翼阵
		battlefield.VFormation(scorpion,soldiers,9,snake);

		/*战争结束，蛇精关键时刻使用玉如意逃脱了追击，以后定会有一场血战*/

	}
}
