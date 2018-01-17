import javax.swing.JFrame;

public final class Ground extends JFrame {



    private final int OFFSET = 30;





    public Ground(Field field) {

        InitUI(field);

    }

    public Ground(Field field,int x,int y){
    	InitUI(field,x,y);
    }


    public void InitUI(Field field1) {

        Field field = field1;

        add(field);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(field.getBoardWidth() + OFFSET,

                field.getBoardHeight() + 2 * OFFSET);

        setLocationRelativeTo(null);

        setTitle("Huluwa VS Monster");

    }

    public void InitUI(Field field1,int x,int y){
        Field field = field1;

        add(field);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(field.getBoardWidth() + OFFSET,

                field.getBoardHeight() + 2 * OFFSET);

        setLocation(x, y);

        setTitle("Huluwa VS Monster Review");
    }



/*    public static void main(String[] args) {
    	Field field = new Field();

        Ground ground = new Ground(field);

        ground.setVisible(true);

    }*/

}