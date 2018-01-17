package Creature;

import java.util.ArrayList;
import java.util.Collections;

public class HuluBrothers implements Creatures{
	
	public ArrayList<HuluBoy> hulubrothers;
	
	public HuluBrothers() {
		hulubrothers = new ArrayList<HuluBoy>();
		HuluBoy bigwa = new BigWa();
		HuluBoy erwa = new ErWa();
		HuluBoy sanwa = new SanWa();
		HuluBoy siwa = new SiWa();
		HuluBoy wuwa = new WuWa();
		HuluBoy liuwa = new LiuWa();
		HuluBoy qiwa = new QiWa();
		hulubrothers.add(bigwa);
		hulubrothers.add(erwa);
		hulubrothers.add(sanwa);
		hulubrothers.add(siwa);
		hulubrothers.add(wuwa);
		hulubrothers.add(liuwa);
		hulubrothers.add(qiwa);
	}
	
	@Override
	public void add(String n, int h, int a) {
		HuluBoy temp = new HuluBoy(n,h,a);
		hulubrothers.add(temp);
	}
	
	
	
	@Override
	public void show() {
		for(int i=0;i<hulubrothers.size();i++)
			hulubrothers.get(i).report();
		System.out.print("\n");
	}
	
	@Override
	public Creature getCreature(int n) {
		return hulubrothers.get(n);
	}
	
	@Override
	public int getnumber() {
		return hulubrothers.size();
	}
	

}
