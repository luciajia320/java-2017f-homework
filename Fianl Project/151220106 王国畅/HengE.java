
public class HengE implements Formation{

	@Override
	public void setFormation(Creature[] group, Field field) {
		int side = group[0].getSide();
		Position basepoint = field.getFieldPos(3, side*7+3);
		
		boolean reverse = side==0?false:true;
		int base_x = basepoint.getX();
		int base_y = basepoint.getY();
		int r = reverse==true?-1:1;
		for(int i = 0; i < group.length; i++){
			group[i].setFieldPos(field.getFieldPos(base_x + i, base_y + r*(i%2)));
		}	
	}

}
