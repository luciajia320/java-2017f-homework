import java.util.ArrayList;
import java.util.Random;

public class Brothers {
	private ArrayList<HuLuWa> list = new ArrayList<HuLuWa>();
	public Brothers() {
		ArrayList<HuLuWa> temp = new ArrayList<HuLuWa>();
		for(int i=1; i<=7; i++) {
			HuLuWa huLu = new HuLuWa(i);
			temp.add(huLu);
		}
		while(!temp.isEmpty()) {
			int point = new Random().nextInt(temp.size());
			list.add(temp.get(point));
			temp.remove(point);
		}
	}
	public ArrayList<HuLuWa> getList() {
		return list;
	}
	public void showPaiHang() {
		for(HuLuWa a : list) {
			System.out.print(a.getPaihang()+" ");
		}
		System.out.println();
	}
	public void showColor() {
		for(HuLuWa a : list) {
			System.out.print(a.getColor()+" ");
		}
		System.out.println();
	}
	public void sort() {
		showPaiHang();
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<list.size()-i-1; j++) {
				if(list.get(j).getNumber()>list.get(j+1).getNumber()) {
					System.out.println(list.get(j).getPaihang()+":"+j+"->"+(j+1));
					System.out.println(list.get(j+1).getPaihang()+":"+(j+1)+"->"+j);
					HuLuWa a = list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, a);
				}
			}
		}
		showPaiHang();
	}
}