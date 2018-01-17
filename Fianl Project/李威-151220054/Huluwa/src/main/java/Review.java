import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.activation.FileDataSource;
import javax.swing.ImageIcon;

public class Review extends Thing2D implements Runnable {
    private Field field;
    private int i=0;

    public Review(int x, int y, Field field) {
        super(x, y);

        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("hong.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    public void run() {
        while (!Thread.interrupted()) {
          
        
        		// this.move(rand.nextInt(10), rand.nextInt(10));
                try {

                    Thread.sleep(sleepTime);
                    //field.getBuffer().append("battle: louluo"+rank+" lose hulu"+huluRank+"\r\n");
                    i++;
                    //System.out.println(i);
                    for(;i<=field.getLoadStrs().size();i++) {
                    	String[] s=field.getLoadStrs().get(i-1).split(" ");
                    	if(i%16==1) {
                    		field.getHuLuWas()[0].setX(Integer.parseInt(s[1]));
                    		field.getHuLuWas()[0].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getHuLuWas()[0].setAlive(true);
                    		else
                    			field.getHuLuWas()[0].setAlive(false);
                    		
                    	}
                    	else if(i%16==2) {
                    		field.getHuLuWas()[1].setX(Integer.parseInt(s[1]));
                    		field.getHuLuWas()[1].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getHuLuWas()[1].setAlive(true);
                    		else
                    			field.getHuLuWas()[1].setAlive(false);
                    	}
                    	else if(i%16==3) {
                    		field.getHuLuWas()[2].setX(Integer.parseInt(s[1]));
                    		field.getHuLuWas()[2].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getHuLuWas()[2].setAlive(true);
                    		else
                    			field.getHuLuWas()[2].setAlive(false);
                    	}
                    	else if(i%16==4) {
                    		field.getHuLuWas()[3].setX(Integer.parseInt(s[1]));
                    		field.getHuLuWas()[3].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getHuLuWas()[3].setAlive(true);
                    		else
                    			field.getHuLuWas()[3].setAlive(false);
                    	}
                    	else if(i%16==5) {
                    		field.getHuLuWas()[4].setX(Integer.parseInt(s[1]));
                    		field.getHuLuWas()[4].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getHuLuWas()[4].setAlive(true);
                    		else
                    			field.getHuLuWas()[4].setAlive(false);
                    	}
                    	else if(i%16==6) {
                    		field.getHuLuWas()[5].setX(Integer.parseInt(s[1]));
                    		field.getHuLuWas()[5].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getHuLuWas()[5].setAlive(true);
                    		else
                    			field.getHuLuWas()[5].setAlive(false);
                    	}
                    	else if(i%16==7) {
                    		field.getHuLuWas()[6].setX(Integer.parseInt(s[1]));
                    		field.getHuLuWas()[6].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getHuLuWas()[6].setAlive(true);
                    		else
                    			field.getHuLuWas()[6].setAlive(false);
                    	}
                    	else if(i%16==8) {
                    		field.getXiaolouluos()[0].setX(Integer.parseInt(s[1]));
                    		field.getXiaolouluos()[0].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getXiaolouluos()[0].setAlive(true);
                    		else
                    			field.getXiaolouluos()[0].setAlive(false);
                    	}
                    	else if(i%16==9) {
                    		field.getXiaolouluos()[1].setX(Integer.parseInt(s[1]));
                    		field.getXiaolouluos()[1].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getXiaolouluos()[1].setAlive(true);
                    		else
                    			field.getXiaolouluos()[1].setAlive(false);
                    	}
                    	else if(i%16==10) {
                    		field.getXiaolouluos()[2].setX(Integer.parseInt(s[1]));
                    		field.getXiaolouluos()[2].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getXiaolouluos()[2].setAlive(true);
                    		else
                    			field.getXiaolouluos()[2].setAlive(false);
                    	}
                    	else if(i%16==11) {
                    		field.getXiaolouluos()[3].setX(Integer.parseInt(s[1]));
                    		field.getXiaolouluos()[3].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getXiaolouluos()[3].setAlive(true);
                    		else
                    			field.getXiaolouluos()[3].setAlive(false);
                    	}
                    	else if(i%16==12) {
                    		field.getXiaolouluos()[4].setX(Integer.parseInt(s[1]));
                    		field.getXiaolouluos()[4].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getXiaolouluos()[4].setAlive(true);
                    		else
                    			field.getXiaolouluos()[4].setAlive(false);
                    	}
                    	else if(i%16==13) {
                    		field.getXiaolouluos()[5].setX(Integer.parseInt(s[1]));
                    		field.getXiaolouluos()[5].setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getXiaolouluos()[5].setAlive(true);
                    		else
                    			field.getXiaolouluos()[5].setAlive(false);
                    	}
                    	else if(i%16==14) {
                    		field.getShejing().setX(Integer.parseInt(s[1]));
                    		field.getShejing().setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getShejing().setAlive(true);
                    		else
                    			field.getShejing().setAlive(false);
                    	}
                    	else if(i%16==15) {
                    		field.getXiezijing().setX(Integer.parseInt(s[1]));
                    		field.getXiezijing().setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getXiezijing().setAlive(true);
                    		else
                    			field.getXiezijing().setAlive(false);
                    	}
                    	else if(i%16==0) {
                    		field.getGrandpa().setX(Integer.parseInt(s[1]));
                    		field.getGrandpa().setY(Integer.parseInt(s[2]));
                    		if(s[3].charAt(0)=='t') 
                    			field.getGrandpa().setAlive(true);
                    		else
                    			field.getGrandpa().setAlive(false);
                    		break;
                    	}
                    	
                    	
                    	
                    }
                    
                    this.field.repaint();

                } catch (Exception e) {

                }
        	}
        	
        	
        }
}

