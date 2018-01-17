package Calabashboy_v3;

//变换阵型

public class Formation {
	
	//一字长蛇阵
	public boolean Single_line_array_cala(Land map, int i, int j) {
		boolean can_do = true;
		if(map.Get_lines() < i + 6 || map.Get_cols() < j+2 || j < 1 || i < 1)
			return false;
		can_do &= map.Put_creature(new Calabash(Color.Red), 	i,   j+2);
		can_do &= map.Put_creature(new Calabash(Color.Orange), 	i+1, j+2);
		can_do &= map.Put_creature(new Calabash(Color.Yellow), 	i+2, j+2);
		can_do &= map.Put_creature(new Calabash(Color.Green), 	i+3, j+2);
		can_do &= map.Put_creature(new Calabash(Color.Cyan), 	i+4, j+2);
		can_do &= map.Put_creature(new Calabash(Color.Blue), 	i+5, j+2);
		can_do &= map.Put_creature(new Calabash(Color.Purple), 	i+6, j+2);
		can_do &= map.Put_creature(new Calabash(Characters.Grandpa), i+3, j);
		if(!can_do) {
			map.Clear_map();
			System.out.println("无法排阵");
		}
		return can_do;
	}
	
	
	//鹤翼阵
	public boolean Crane_wing_array_monster(Land map, int i, int j) {
		boolean can_do = true;
		if(map.Get_lines() < i + 6 || map.Get_cols() < j + 10)
			return false;
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i,   j);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+1, j+1);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+2, j+2);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+3, j+3);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+4, j+4);
		can_do &= map.Put_creature(new Monster(Characters.Scorpion_man),	i+6, j+5);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+4, j+6);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+3, j+7);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+2, j+8);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+1, j+9);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i,   j+10);
		can_do &= map.Put_creature(new Monster(Characters.Snake_woman), 	i+2, j+5);
		if(!can_do) {
			map.Clear_map();
			System.out.println("无法排阵");
		}
		return can_do;
	}
	
	
	//偃月阵
	public boolean Crescent_moon_array_monster(Land map, int i, int j) {
		boolean can_do = true;
		if(map.Get_lines() < i + 8 || map.Get_cols() < j + 6)
			return false;
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i,   j+6);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+1, j+4);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+1, j+5);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+2, j+3);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+2, j+4);
		can_do &= map.Put_creature(new Monster(Characters.Toad),			i+3, j+1);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+3, j+2);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+3, j+3);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+4, j+1);
		can_do &= map.Put_creature(new Monster(Characters.Scorpion_man), 	i+4, j+2);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+4, j+3);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+5, j+1);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+5, j+2);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+5, j+3);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+6, j+3);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+6, j+4);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+7, j+4);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+7, j+5);
		can_do &= map.Put_creature(new Monster(Characters.Toad), 			i+8, j+6);
		can_do &= map.Put_creature(new Monster(Characters.Snake_woman), 	i+4, j+5);
		if(!can_do) {
			map.Clear_map();
			System.out.println("无法排阵");
		}
		return can_do;
	}
}
