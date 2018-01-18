package assignment3;

import assignment3.Formation.FORMATION;

class Space {
	
	private int size;
	Position board[][] = new Position[20][20];
	
	
	Space(int size)
	{
		this.size = size;
		for(int i = 0;i < size;++i)	
		{
			for(int j = 0;j < size;++j)
			{
				board[i][j] = new Position();
			}
		}
	}
	
	public boolean outOfBorder(Position p)
	{
		int x = p.getX();
		int y = p.getY();
		
		if(x >= 0 && x < this.size)
		{
			if(y >= 0 && y < this.size)
			{
				return false;
			}
		}
		return true;
	}
	public boolean isEmpty(Position p)
	{
		int x = p.getX();
		int y = p.getY();
		if(board[x][y].isEmpty())
			return true;
		else
			return false;
	}
	
	void setFormation(Creature []creatures,Position pos,FORMATION formation)
	{		
		Formation f = new Formation();
		f.setFormation(formation,creatures.length);
		
		Position fp[] = f.getAllPos();
		Position p[] = new Position[creatures.length];
		for(int i = 0;i < creatures.length;++i)
			p[i] = new Position();
		
		boolean flag = true;
		for(int i = 0;i < creatures.length;++i)
		{
			p[i].setX(fp[i].getX() + pos.getX());
			p[i].setY(fp[i].getY() + pos.getY());

			
			if(outOfBorder(p[i]))
			{
				System.out.println(formation);
				flag = false;
				System.out.println("无法布阵");
				break;
			}
			if(!isEmpty(p[i]))
			{
				
				
				System.out.println(formation);
				flag = false;
				System.out.println("无法布阵");
				break;
			}
		}
		if(flag)
		{
			for(int i = 0;i < creatures.length;++i)
			{								
				creatures[i].setPos(p[i]);
				board[p[i].getX()][p[i].getY()].setCreature(creatures[i]);
			}
		}
	}
	
	void clear()
	{
		for(int i = 0;i < size;++i)			
		{
			for(int j = 0;j < size;++j)
			{
				board[i][j].setEmpty();
			}
		}
	}
	
	void print()
	{
		for(int i = 0;i < size;++i)
		{
			for(int j = 0;j < size;++j)
			{
				String format = "%-12s";
				if(board[i][j].isEmpty())
				{
					String str = String.format(format, "~");
					System.out.print(str);
				}
				else
				{
					String str = String.format(format, board[i][j].getCreature().report());
					System.out.print(str);
				}
			}
			System.out.println();
		}
	}
}
