package javaH2;

public class field {			//Õ½³¡
	position place[][]=new position[20][20];
	
	public field()
	{
		for (int i = 0; i < place.length; i++) {
			for (int j = 0; j < place[i].length; j++) {
				place[i][j]=new position();
				place[i][j].setX(i);
				place[i][j].setY(j);
				place[i][j].setMaster(new creature());
			}
		}
	}
	
	public void AssignH(Huluwa[] A,int x,int y)
	{
		int i=x,j=y;
		for (int j2 = 0; j2 < A.length-1; j2++) {
			A[j2].getPosition(i, j++);
			place[i][j].setMaster(A[j2]);
		}
	}
	public void AssignM(Monster A,int x,int y)
	{
		A.getPosition(x, y);
		place[x][y].setMaster(A);
		//A.setSMpos(1);			//°ÚÕóÐÍ
		/*int t=A.getCount();
		for (int i = 0; i < t; i++) {
			place[A.getSM()[i].getPos().getX()][A.getSM()[i].getPos().getY()].setMaster(A.getSM()[i]);
		}*/
	}
	public void AssignS(Snake A,int x,int y)
	{
		A.setPos(x, y);
		//place[x][y].setMaster(A);
	}
	public void AssignG(Grandpa A,int x,int y)
	{
		A.setPos(x, y);
		//place[x][y].setMaster(A);
	}
	
	public void clear()
	{
		for (int i = 0; i < place.length; i++) {
			for (int j = 0; j < place[i].length; j++) {
				place[i][j].setMaster(new creature());
			}
		}
	}
	
	public void show()
	{
		for (int i = 0; i < place.length; i++) {
			for (int j = 0; j < place[i].length; j++) {
				System.out.print(place[i][j].getMaster().Tell()+"  ");
			}
			System.out.println();
		}
	}
	
}
