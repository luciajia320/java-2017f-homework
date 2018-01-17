

import java.awt.Image;

public class Thing2D {

    private final int SPACE = 20;

    private int x;
    private int y;
    private boolean is_live=true;
    private Image image;
    private Role role;

    public Thing2D(int x, int y,Role r) {
        this.x = x;
        this.y = y;
        this.is_live=true;
        this.role=r;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }
    
    public boolean is_live(){
    	return this.is_live;
    }
    
    public Role role(){
    	return this.role;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setL(boolean l){
    	this.is_live=l;
    }

} 