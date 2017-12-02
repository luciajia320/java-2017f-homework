
public class Ground {
	public static final String PLACE_HOLDER = "🏻";
	private int sizelength;
	private String[][] content;
	public Ground(int n) {
		this.sizelength = n;
		content = new String[n][n];
		for(int i=0;i<sizelength;i++) {
			for(int j=0;j<sizelength;j++) {
				content[i][j] = "null";
			}
		}
	}
	
	public void layoutFormation(Formation formation,Location location) {
		if(OutOfBound(formation,location)) {
			System.out.println("Out of bound!");
			return;
		}
		if(Overlap(formation,location)) {
			System.out.println("Overlay!");
			return;
		}
		formation.setLocation(location);
		
		for(int i=0;i<formation.getHeight();i++) {
			for(int j=0;j<formation.getWidth();j++) {
				content[location.getY()+i][location.getX()+j] = formation.getContent()[i][j];
			}
		}
		
	}

	public void clearFormation(Formation formation) {
		if(OutOfBound(formation,formation.getLocation())) {
			System.out.println("Out of bound!");
			return;
		}
		for(int i=0;i<formation.getHeight();i++) {
			for(int j=0;j<formation.getWidth();j++) {
				content[formation.getLocation().getY()+i][formation.getLocation().getX()+j] = "null";
			}
		}
	}

	public Boolean OutOfBound(Formation formation,Location location) {
		if(location.getX()<0||location.getX()<0)
			return true;
		if(location.getX()+formation.getWidth()>this.sizelength 
				|| location.getY()+formation.getHeight()>this.sizelength)
			return true;
		return false;
	}
	
	public Boolean Overlap(Formation formation,Location location) {
		for(int i=location.getY();i<location.getY()+formation.getHeight();i++) {
			for(int j=location.getX();j<location.getX()+formation.getWidth();j++) {
				if(content[location.getY()][location.getX()]!="null")
					return true;
			}
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<sizelength;i++) {
			for(int j=0;j<sizelength;j++) {
				if(content[i][j] == "null")
					sb.append(PLACE_HOLDER+" ");
				else
					sb.append(content[i][j]+" ");
			}
			sb.append("\n");	
		}
		return sb.toString();
	}
}
