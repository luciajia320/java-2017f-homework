package com.gxs;

import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.io.File;
import java.net.URL;
import javax.swing.*;

import javax.swing.*;
import java.util.ArrayList;

public class Space extends JPanel implements Runnable
{
	private static  final int WIDTH=Constant.MAP_WIDTH;
	private static  final int HEIGHT=Constant.MAP_HEIGHT;
	
	
	private  Judge judge=Judge.getAcess();
	private DrawTimer drawTimer=new DrawTimer();
	private Image img=null;
	
	private Space()
	{
		judge.setDrawTimer(drawTimer);
		img=new ImageIcon(Constant.IMGPATH+"bk.jpg").getImage();
		
		/*
		 URL loc = this.getClass().getClassLoader().getResource("bk.jpg");
	     ImageIcon iia = new ImageIcon(loc);
	     img = iia.getImage();
	     */
	     
		assert(img!=null);
	}
	
	private static final  Space map=new Space();
	
	public static int getMapWidth() {return WIDTH;}
	public static int getMapHeight() {return HEIGHT;}
	public static Space getMap() {return map;}
	
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ArrayList<Creature>creature=Creature.getToRun();
		
		System.out.println("start to draw");
		
		g.drawImage(img, 0, 0, Constant.MAP_WIDTH, Constant.MAP_HEIGHT,null);
		
		if(!Judge.getAcess().showCreature())
			return;
		
		for(int i=0;i<creature.size();i++)
		{
			Creature c=creature.get(i);Position p=c.getPhyPos();
		//	System.out.println(p.getY()+" "+p.getX());
			if(c.isAlive())
				g.drawImage(c.getImage(),p.getY(),p.getX(), Constant.CREATURE_WIDTH, Constant.CREATURE_HEIGHT, null);
		}
		
	}
	
	public void draw()
	{
		this.repaint();
	}
	public void run()
	{
		try {
			while(!Thread.interrupted())
			{
				//绘图
				draw();
				TimeUnit.MILLISECONDS.sleep(Constant.DRAW_FREQ);
				System.out.println("draw over");
				//绘图完成
				drawTimer.drawOver();
				//等待动作
				drawTimer.waitForMove();
			}
		}catch(InterruptedException e)
		{
			System.out.println("space exits via inrerruption");
		}
	}
}
