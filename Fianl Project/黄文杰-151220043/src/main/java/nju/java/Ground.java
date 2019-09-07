package nju.java;


import javax.swing.JFrame;


public final class Ground extends JFrame {

    private final int WOFFSET = 19;
    private final int HOFFSET = 47;

    public Ground() {
        InitUI();
    }

    public void InitUI() {
    	FileChooser fileChooser=new FileChooser(this);
        Field field = new Field(fileChooser);
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + WOFFSET,
                field.getBoardHeight() +  HOFFSET);
        setLocationRelativeTo(null);
        setTitle("Ground");
    }


   
        
    
}