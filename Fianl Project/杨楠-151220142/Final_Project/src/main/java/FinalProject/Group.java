package FinalProject;

public class Group {
	protected Creature [] group;
	private int n;
	public Group(int x,Step s,int t) {
		n = x;
		group = new Creature [n];
		if(n == Huluwa.getkinds())
			for(int i=0;i<n;i++)
				group[i] = new Creature(i+1,s);
		else if(t==11)
			for(int i=0;i<n;i++)
				group[i] = new Creature(i+10,s);
		else if(t==12)
			for(int i=0;i<n;i++)
				group[i] = new Creature(i+8,s);
		else if(t==13)
			for(int i=0;i<n;i++)
				group[i] = new Creature(i+10,s);
		else if(t==14)
			for(int i=0;i<n;i++)
				group[i] = new Creature(i+14,s);
		else if(t==15)
			for(int i=0;i<n;i++)
				group[i] = new Creature(i+8,s);
	}
	public void settype(int i,int n) {
		group[i].settype(n);
	}
	public void setposition(int stx,int sty,int type) { //add zhenxing
		for(int i=0;i<n;i++)
			switch(type) {
			case 0:
				group[i].setposition(stx, sty+i);
				break;
			case 1:
				group[i].setposition(stx-i, sty+i);
				break;
			case 2:
				int a,b;
				if(i%2==0) {
					a = 1;
				}
				else
					a = -1;
				b = i/2 + 1;
				group[i].setposition(stx-b, sty+a*b);;
				break;
			case 3:
				if(i<n/2)
					group[i].setposition(stx, sty+i);
				else
					group[i].setposition(stx,sty+i+1);
				break;
			case 4:
				if(group[i].gettype()==12) {
					group[0].setposition(stx, sty);
					group[1].setposition(stx, Stage.ysize()-sty-1);
				}
				else {
					if(i%4==0)
						group[i].setposition(stx-1, sty);
					else if(i%4==1)
						group[i].setposition(stx+1, sty);
					else if(i%4==2)
						group[i].setposition(stx, sty+1);
					else if(i%4==3)
						group[i].setposition(stx, sty-1);
				}
				break;
			}
	}
	public void leaveposition() {
		for(int i=0;i<n;i++)
				group[i].leaveposition();
	}
	public void start() {
		for(int i=0;i<n;i++)
			group[i].start();
	}
}

