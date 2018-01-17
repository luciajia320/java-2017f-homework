package formation;

import creature.Creature;
import creature.Grass;
import location.Location;

public class Formation {
    protected int height;
    protected int width;

    protected Creature[][] contents;

    public  Formation(int height, int width){
        this.height = height;
        this.width = width;

        contents = new Creature[height][width];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                contents[i][j] = new Grass();
                contents[i][j].setLoc(new Location(i, j));
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                sb.append(contents[i][j].toString());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return  sb.toString();
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

    public Creature[][] getContents() {
        return contents;
    }

    public void setContents(Creature[][] contents) {
        this.contents = contents;
    }

}
