import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newCachedThreadPool();
		Field field = new Field(exec);
        Ground ground = new Ground(field);
        ground.setVisible(true);
		while(true){
			synchronized(field.getField()){
				if(field.gameIsOver()){
					exec.shutdownNow();
					System.out.println("gameover");
					JOptionPane.showMessageDialog(null,field.getWinner()+" Win!");
					System.exit(0);
					return;
				}
				Thread.yield();
			}
		}
		
	}

}
