package nju.hulu;

import java.awt.Image;
import java.net.URL;

public interface Creature {

	
	public void setState(State i);
	public State getState();
	public Type getType();
	public Group getGroup();
	public Image getImage();
	public Position<Creature> getPosition();
	public void setPosition(Position<Creature> position);
//	public boolean smallerThan(Creature creature,int type);
	public URL getImgURL();
//	public void tellMove(Position<Creature> a,Position<Creature> b);
	
	
}
