
public class Map {
	private int numberOfRow;
	private Position[] map;
	private Brothers brothers;
	private BadGuy badGuy;
	public Map(int n) {
		numberOfRow=n;
		map=new Position[n*n];
		for(int i=0;i<n*n;i++) {
			map[i]=new Position();
		}
		brothers=null;
		badGuy=null;
	}
	public void setBadGuy(BadGuy b) {
		badGuy=b;
	}
	public void setBrothers(Brothers b) {
		brothers=b;
	}
	public void setGrandpa(int x,int y) {
		Creature g=new Grandpa();
		setCreature(g,x,y);
	}
	public void setSnake(int x,int y) {
		Creature s=new Snake();
		setCreature(s,x,y);
	}
	public void brothersStrategy(int x,int y) {
		brothers.sort();
		for(HuLuWa h:brothers.getList()) {
			setCreature(h,x++,y);
		}
	}
	public void badGuyStrategy(int x,int y) {
		badGuy.formation(this, x, y);
	}
	
	
	public void setCreature(Creature c,int x,int y) {
		map[x*numberOfRow+y].setContent(c);
	}
	
	public void showMap() {
		for(int i=0;i<numberOfRow;i++) {
			for(int j=0;j<numberOfRow;j++) {
				map[i*numberOfRow+j].showContent();
			}
			System.out.print("\n");
		}
		System.out.print("\n\n");
	}
}
