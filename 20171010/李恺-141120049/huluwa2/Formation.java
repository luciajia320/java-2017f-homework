package huluwa2;

public class Formation {
	public Position position = null;
	public int no;
}

class LongSnake extends Formation { // ÈL…ﬂ
	public LongSnake() {
	 for(int i=0;i<no;i++) {
		 no = 7;
		 position = new Position(no, i);
		 position=(Position) space.getPosition(i+1,1);
	 }	 
class Oblique<Space> extends Formation{//—„––
	public Oblique() {
		Space space;
		no = 20;
		int i;
		position = new Position(no,i);
		for(i=0;i<no;i++);		
	  }
    }
  }
}


