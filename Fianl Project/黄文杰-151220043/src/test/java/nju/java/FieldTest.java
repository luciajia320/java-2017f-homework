package nju.java;

import static org.junit.Assert.*;

import org.junit.Test;
class TestTask implements Runnable{
	Player player;
	public TestTask(Player player){
		this.player=player;
	}
	public void run(){
		if(this.player.getName().equals("player1"))
			this.player.moveTest(0, 1);
		else
			this.player.moveTest(0, -1);
	}
}
public class FieldTest {

	@Test
	public void testAvailable() {
		Field field=new Field(null);
		Player player1,player2;
		player1=new Player("player1",0,0, field,"huluwa1.png",0,15);		
		player2=new Player("player2",0,160, field,"huluwa2.png",0,15);
		new Thread(new TestTask(player1)).start();
		new Thread(new TestTask(player2)).start();
		try{
			Thread.sleep(5000);
		}catch(Exception e){
			e.printStackTrace();
		}
		assertNotEquals(player1.y(),player2.y());
		
		//fail("Not yet implemented");
	}
	
}
