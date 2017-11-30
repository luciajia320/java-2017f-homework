

import java.util.ArrayList;
import java.util.List;

public class Ground {

    public static final String PLACE_HOLDER = "\uD83C\uDF3F";
    private int sideLength;
    private String[][] content;
    private List exists;

    public Ground(int n) {
        this.sideLength = n;

        exists = new ArrayList();
        
        content = new String[sideLength][sideLength];

        for (int i = 0; i < this.sideLength; i++) {
            for (int j = 0; j < this.sideLength; j++) {
                content[i][j] = PLACE_HOLDER;
            }
        }


    }
    
    public void layout(Creature creature, Location location) {
    	this.content[location.getX()][location.getY()] = creature.getName();
    }

    public void layout(Formation formation, Location location) throws TooCrowdedException {

        if ((location.getX() + formation.getWidth() > sideLength)
                || (location.getY() + formation.getHeight() > sideLength))
            throw new TooCrowdedException("Out of bounds"); //out of bounds


        Location loc = formation.getLocation();

        formation.setLocation(location);


        for (Object exist : exists) {
            if (conflict((Formation) exist, formation)) {
                formation.setLocation(loc);
                throw new TooCrowdedException("Conflicted");
            }

        }
            for (int i = 0; i < formation.getWidth(); i++) {
                for (int j = 0; j < formation.getHeight(); j++) {
                    content[i + formation.getLocation().getX()][j + formation.getLocation().getY()] = formation.getContent()[i][j];
                }
            }
        
        exists.add(formation);
    }

    private boolean conflict(Formation f1, Formation f2) {
        if (contains(f1, f2))
            return true;

        if (contains(f2, f1))
            return true;

        if (overlap(f1, f2))
            return true;

        if (overlap(f2, f1))
            return true;

        return false;
    }

    private boolean contains(Formation f1, Formation f2) {
        if ((f1.getLocation().getX() >= f2.getLocation().getX())
                && (f1.getLocation().getY() >= f2.getLocation().getY())
                && (f1.getLocation().getX() + f1.getWidth() <= f2.getLocation().getX() + f2.getWidth())
                && (f1.getLocation().getY() + f1.getWidth() <= f2.getLocation().getY() + f2.getHeight())) {
            return true;
        }
        return false;
    }

    private boolean overlap(Formation f1, Formation f2) {

        if (((f1.getLocation().getX() + f1.getWidth() >= f2.getLocation().getX()))
                && (f1.getLocation().getY() + f1.getHeight() >= f2.getLocation().getY())
                && (f1.getLocation().getX() <= f2.getLocation().getX())
                && (f1.getLocation().getY() <= f2.getLocation().getY()))
            return true;
        return false;
    }

    public void clear() {
    	exists.clear();
    	for(int i = 0;i < sideLength;++i)
    		for(int j = 0;j < sideLength;++j) {
    			content[i][j] = PLACE_HOLDER;
    		}
    		
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < content.length; j++) {
            for (int i = 0; i < content[j].length; i++) {
                sb.append(content[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();

    }


}
