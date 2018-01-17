import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class MyContainer
{
	private Vector vId;
	private Vector vR;
	private Vector vC;
	private Vector vA;
	private int totalStep = 0;
	private final int PLAYER_NUM = 16;
	
	MyContainer()
	{
		totalStep = 0;
		vId = new Vector();
		vR = new Vector();
		vC = new Vector();
		vA = new Vector();	
	}
	
	public void readfile(String filename) throws FileNotFoundException
	{
		totalStep = 0;
		vId.clear();
		vR.clear();
		vC.clear();
		vA.clear();
		
		File f = new File(filename);
		Scanner s = new Scanner(f);	
		
		int counter = 0;
		while(s.hasNextLine())
		{
			String str = s.nextLine();
			String[] buf;

			int id,row,col,alive = 0;
			buf = str.split(",");
			id = Integer.parseInt(buf[0]);
			row = Integer.parseInt(buf[1]);
			col = Integer.parseInt(buf[2]);
			alive = Integer.parseInt(buf[3]);
			vId.addElement(new Integer(id));
			vR.addElement(new Integer(row));
			vC.addElement(new Integer(col));
			vA.addElement(new Integer(alive));
			++counter;
			if(counter == PLAYER_NUM)
			{
				++totalStep;
				counter = 0;
			}
			
		}	
			
	}
	
	public int getTotalStep()
	{
		return totalStep;
	}
	public int getPlayerNum()
	{
		return PLAYER_NUM;
	}
	
	public int getVIdValue(int index)
	{
		return (int) vId.elementAt(index);
	}
	public int getVRValue(int index)
	{
		return (int) vR.elementAt(index);
	}
	public int getVCValue(int index)
	{
		return (int) vC.elementAt(index);
	}
	public int getVAValue(int index)
	{
		return (int) vA.elementAt(index);
	}
}

