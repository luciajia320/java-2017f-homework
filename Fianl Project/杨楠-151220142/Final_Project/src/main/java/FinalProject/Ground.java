package FinalProject;


import javax.swing.JFrame;


public final class Ground extends JFrame {

    private final int OFFSET = 30;

    private static Field field;
    private int level;
    private static boolean first;
    public Ground() {
    	first = false;
    }

    public void InitUI() {
        field = new Field();
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET -10,
                field.getBoardHeight() + 2 * OFFSET -20);
        setLocationRelativeTo(null);
        setTitle("Round"+Level.getlevel());
    }

    public static void flush() {
    	field.restartLevel();
    	field.repaint();
    }
    public void gamestart() {
    	if(!first) {
    		InitUI();
    		setVisible(true);
    		first = true;
    	}
    	else {
    		int i = Level.getlevel();
    		setTitle("Round"+i);
    	}
    }
    public void gamestart(int i) {
    	if(i==0)
    		setTitle("Load Mode");
    }
}