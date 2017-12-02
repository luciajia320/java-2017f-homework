
public class Formation {
	//ºáÖáÎªx=width,×ÝÖáÎªy=height
	
	private Location location;
	private int width;
	private int height;
	protected String [][]content;
	
	
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public String[][] getContent() {
        return content;
    }

    public void setContent(String[][] content) {
        this.content = content;
    }
    
    
    public Formation(int width,int height){
    	this.width = width;
    	this.height = height;
    	content = new String[height][width];
    	
    	for(int i=0;i<height;i++) {
    		for(int j=0;j<width;j++) {
    			content[i][j]="null";
    		}
    	}
    }
}
