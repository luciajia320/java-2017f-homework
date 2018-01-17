
import javax.swing.JFrame;

public class Main extends JFrame {
	
	private final int OFFSET = 200;
	
	public Main(){
		initUI();
	}
	
	public void initUI(){
		 	Field field = new Field();
	        add(field);

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(field.getBoardWidth() + OFFSET,
	                field.getBoardHeight() + 2 * OFFSET);
	        setLocationRelativeTo(null);
	        setTitle("ºùÂ«ÍÞVSÉß¾«");
	}
	
	public static void main(String[] args) throws Exception{
        Main ground = new Main();
        ground.setVisible(true);
    }
}
