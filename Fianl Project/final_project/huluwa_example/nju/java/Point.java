package nju.java;

import niuxuCharacter.Thing2D;

public class Point {
	private int x;
	private int y;
	public Thing2D holder;
	public Point(int x, int y) {
		super();
		holder = null;
		this.x=x;
		this.y=y;
	}
	public Thing2D getHolder() {
		return holder;
	}
	public void setHolder(Thing2D holder) {
		this.holder = holder;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}