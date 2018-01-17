import java.util.Scanner;

public class MainBattle {
	private GoodPeople HuluTeam;
	private BadPeople SnakeTeam;
	private Space BattleFeild;
	MainBattle(int n){
		HuluTeam=new GoodPeople();
		HuluTeam.initial();
		HuluTeam.SortByColor();
		SnakeTeam=new BadPeople();
		BattleFeild=new Space(n);
	}
	public void setCreature(creature a,int x,int y) {
		BattleFeild.setACreature(a, x, y);
	}
	public void printBattleFeild() {
		BattleFeild.printSpace();
	}
	public void ClearFeild() {
		BattleFeild.clear();
	}
	public void SetGradPa(int x,int y) {
		BattleFeild.setACreature(HuluTeam.GetGrandPa(), x, y);
	}
	public void SetSnake(int x,int y) {
		BattleFeild.setACreature(SnakeTeam.GetSnake(), x, y);
	}
	public boolean IsHasACreate(int x,int y) {
		return BattleFeild.GetPositionArray().get(x).get(y).GetIsHasCreature();
	}
	public void SetHeYiFormation(Object[] objects,boolean properties) {
		if(objects.length<7) {
			for(int i=objects.length;i<7;i++) {
				SnakeTeam.addEssence("BadEss");
			}
		}
		int []a= {0,-3,-2,-1,1,2,3};
		int []b= {0,3,2,1,1,2,3};
		for(int i=0;i<7;i++) {
			BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(i), 3+a[i], 6+b[i]);
		}
		BattleFeild.setACreature(this.SnakeTeam.GetSnake(),3,8);
		this.SnakeTeam.GetSnake().cheerUp();
	}
	public void SetChangSheFormation(Object[] objects,boolean properties) {
		
		if(properties) {
			for(int i=0;i<objects.length;i++) {
			BattleFeild.setACreature((creature)objects[i], i, 2);
		}
			BattleFeild.setACreature(this.HuluTeam.GetGrandPa(),objects.length/2, 1);
			this.HuluTeam.GetGrandPa().cheerUp();
		}
		else {
			if(objects.length<7) {
				for(int i=objects.length;i<7;i++) {
					SnakeTeam.addEssence("BadEss");
				}
			}
			for(int i=0;i<7;i++) {
				BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(i), i, 7);
			}
			BattleFeild.setACreature(this.SnakeTeam.GetSnake(),SnakeTeam.SizeOfBadPeople()/2,8);
			this.SnakeTeam.GetSnake().cheerUp();
		}
	}
	public void SetYuLingFormation(Object[] objects,boolean properties) {
		if(objects.length<10) {
			for(int i=objects.length;i<10;i++) {
				SnakeTeam.addEssence("BadEss");
			}
		}
		BattleFeild.setACreature(this.SnakeTeam.GetSnake(),3,6);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(0), 2, 7);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(1), 3, 8);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(2), 4, 6);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(3), 4, 7);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(4), 4, 8);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(5), 5, 5);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(6), 5, 6);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(7), 5, 7);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(8), 5, 8);
		BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(9), 5, 9);
		this.SnakeTeam.GetSnake().cheerUp();
	}
	public void SetYanXingFormation(Object[] objects,boolean properties) {
		if(objects.length<7) {
			for(int i=objects.length;i<7;i++) {
				SnakeTeam.addEssence("BadEss");
			}
		}
		for(int i=0;i<7;i++) {
			BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(i), i, 3+i);
		}
		BattleFeild.setACreature(this.SnakeTeam.GetSnake(),1,6);
		this.SnakeTeam.GetSnake().cheerUp();
	}
	public void SetFengShiFormation(Object[] objects,boolean properties) {
		if(objects.length<11) {
			for(int i=objects.length;i<11;i++) {
				SnakeTeam.addEssence("BadEss");
			}
		}
		int []a= {0,0,1,-1,2,0,-2,3,-3,0,0};
		int[]b= {0,1,1,1,2,2,2,3,3,3,4,5};
		for(int i=0;i<11;i++) {
			BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(i), 3+a[i], 4+b[i]);
		}
		BattleFeild.setACreature(this.SnakeTeam.GetSnake(),3,9);
		this.SnakeTeam.GetSnake().cheerUp();
	}
	public void SetYanYueFormation(Object[] objects,boolean properties) {
		if(objects.length<19) {
			for(int i=objects.length;i<19;i++) {
				SnakeTeam.addEssence("BadEss");
			}
		}
		int []a= {0,1,-1,0,1,-1,0,1,-1,2,-2,2,-2,3,-3,3,-3,4,-4};
		int[]b= {0,0,0,1,1,1,2,2,2,2,2,3,3,3,3,4,4,5,5};
		for(int i=0;i<19;i++) {
			BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(i), 4+a[i], 4+b[i]);
		}
		BattleFeild.setACreature(this.SnakeTeam.GetSnake(),4,8);
		this.SnakeTeam.GetSnake().cheerUp();
	}
	public void SetDongEFormation(Object[] objects,boolean properties) {
		if(objects.length<8) {
			for(int i=objects.length;i<8;i++) {
				SnakeTeam.addEssence("BadEss");
			}
		}
		for(int i=0;i<8;i++) {
			BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(i), 1+i/2+i%2, 5+i%2);
		}
		BattleFeild.setACreature(this.SnakeTeam.GetSnake(),3,7);
		this.SnakeTeam.GetSnake().cheerUp();
	}
	public void SetFangYuanFormation(Object[] objects,boolean properties) {
		if(objects.length<8) {
			for(int i=objects.length;i<8;i++) {
				SnakeTeam.addEssence("BadEss");
			}
		}
		int []a= {0,1,-1,2,-2,1,-1,0};
		int[]b= {0,1,1,2,2,3,3,4};
		for(int i=0;i<8;i++) {
			BattleFeild.setACreature(SnakeTeam.GetLittleEssence().get(i), 3+a[i], 5+b[i]);
		}
		BattleFeild.setACreature(this.SnakeTeam.GetSnake(),3,7);
		this.SnakeTeam.GetSnake().cheerUp();
	}
	public void RandSetEnemyFormation() {
		int a=(int) (Math.random()*8);
		switch(a) {
		case 0:this.SetChangSheFormation(this.SnakeTeam.GetLittleEssence().toArray(), false);break;
		case 1:this.SetDongEFormation(this.SnakeTeam.GetLittleEssence().toArray(), false);break;
		case 2:this.SetFangYuanFormation(this.SnakeTeam.GetLittleEssence().toArray(), false);break;
		case 3:this.SetFengShiFormation(this.SnakeTeam.GetLittleEssence().toArray(), false);break;
		case 4:this.SetHeYiFormation(this.SnakeTeam.GetLittleEssence().toArray(), false);break;
		case 5:this.SetYanXingFormation(this.SnakeTeam.GetLittleEssence().toArray(), false);break;
		case 6:this.SetYanYueFormation(this.SnakeTeam.GetLittleEssence().toArray(), false);break;
		case 7:this.SetYuLingFormation(this.SnakeTeam.GetLittleEssence().toArray(), false);break;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainBattle MB=new MainBattle(10);
		Scanner in=new Scanner(System.in);
		while(!in.nextLine().equals("exit")) {
			MB.ClearFeild();
			MB.SetChangSheFormation(MB.HuluTeam.GetHuluBoy(), true);
			MB.RandSetEnemyFormation();
			MB.printBattleFeild();
		}
		in.close();
	}

}
