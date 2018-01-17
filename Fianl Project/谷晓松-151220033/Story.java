package com.gxs;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;


/*
 * maven
 * 解决图片问题
 */

/*之后加入泛型*/
public class Story extends JFrame 
{
	//private JButton load=null;
	
	Story()
	{
		this.setSize(Constant.MAP_WIDTH,Constant.MAP_HEIGHT);
	
		this.setTitle("互撸娃151220033");
		new Thread(Judge.getAcess()).start();
		this.add(Space.getMap());
		InitControler();
		this.setVisible(true);	
		
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void InitControler()
	{
		Judge j=Judge.getAcess();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_SPACE:	j.start();	break;
					//replay尚且存在严重线程问题
				//	case KeyEvent.VK_R:	j.restart();	break;
					case KeyEvent.VK_L:	chooseFile();	break;//j.loadSave();	break;
				}
			}
		});
	}
	private void chooseFile()
	{
		File f=null;
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setCurrentDirectory(new File(Constant.SAVEPATH));
		fileChooser.setDialogTitle("选择存档");
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
           f = fileChooser.getSelectedFile();
        Judge.getAcess().loadSave(f);
	}
}
