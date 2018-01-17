package Calabashboy_v3;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Land map = new Land(10,20);
		map.Clear_map();
		Formation sort = new Formation();
		
		System.out.println("长蛇阵vs鹤翼阵");
		boolean can_do = true;
		can_do &= sort.Single_line_array_cala(map, 1, 1);
		can_do &= sort.Crane_wing_array_monster(map, 1, 5);
		if(can_do)
			map.Print_map();
		else {
			System.out.println("排阵有错误，不能实现\n");
		}
		
		System.out.println("长蛇阵vs偃月阵");
		can_do = true;
		map.Clear_map();
		can_do &= sort.Single_line_array_cala(map, 2, 1);
		can_do &= sort.Crescent_moon_array_monster(map, 1, 5);
		if(can_do)
			map.Print_map();
		else {
			System.out.println("排阵有错误，不能实现\n");
		}
	}

}
