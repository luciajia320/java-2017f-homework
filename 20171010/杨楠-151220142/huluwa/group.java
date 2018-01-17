package huluwa;

public class group{
	protected creature [] g;
	private int n;
	public group(int x) {
		n = x;
		g = new creature [n];
		for(int i=0;i<n;i++)
			g[i] = new creature();
	}
	public void setposition(int stx,int sty,int type) {
		for(int i=0;i<n;i++)
			switch(type) {
			case 0:
				g[i].setposition(stx, sty+i);
				break;
			case 1:
				g[i].setposition(stx-i, sty+i);
				break;
			case 2:
				int a,b;
				if(i%2==0) {
					a = 1;
				}
				else
					a = -1;
				b = i/2 + 1;
				g[i].setposition(stx+a*b, sty+b);;
				break;
			}
	}
	public void leaveposition() {
		for(int i=0;i<n;i++)
				g[i].leaveposition();
	}
}
