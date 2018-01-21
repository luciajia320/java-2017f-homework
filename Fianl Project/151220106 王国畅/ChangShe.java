
public class ChangShe implements Formation{

	@Override
	public void setFormation(Creature[] group, Field field) {
		// TODO Auto-generated method stub
		int side = group[0].getSide();
		Position basepoint = field.getFieldPos(3, side*7+3);
		
		int base_x = basepoint.getX();
		int base_y = basepoint.getY();
		for(int i = 0; i < group.length; i++){
			System.out.println(group[i].setFieldPos(field.getFieldPos(base_x + i, base_y)));
		}
	}

}
