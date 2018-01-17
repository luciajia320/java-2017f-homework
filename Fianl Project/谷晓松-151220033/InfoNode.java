package com.gxs;

import java.io.*;

public class InfoNode implements Serializable
{
	private int id;
	private Position dest;
	private boolean alive=true;
	
	InfoNode(int id,Position dest)
	{
		this.id=id;
		this.dest=dest;
	}
	InfoNode(int id,Position pos,boolean alive)
	{
		this(id,pos);
		this.alive=alive;
	}
	public int getId() {	return id;}
	public Position getPos() {	return dest;}
	public boolean getAlive() {	return alive;}
}
