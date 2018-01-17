import java.awt.Image;

abstract public class Thing2D {

    private final int SPACE = 20;

    private Position position=null;
    private int x;
    private int y;
    private int rank;
    private Image image;
    
    protected int sleepTime=200;

    public Thing2D(int x, int y) {
        this.x = x;
        this.y = y;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

	
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		System.out.println("error->thing2D setPosition()");
	}
	
	public Position getPosition() {
		// TODO Auto-generated method stub
		System.out.println("error->thing2D getPosition()");
		return position;
	}

	public int getRank() {
		// TODO Auto-generated method stub
		System.out.println("error->thing2D getRank()");
		System.exit(-1);
		return 0;
	}
	
    


} 