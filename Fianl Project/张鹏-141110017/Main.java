
import java.awt.*;  
  
import javax.swing.*;  
  



public class Main extends JFrame {


    private static final int WIDTH = 1200;//����Ŀ��  
    private static final int HEIGHT = 1000;//����ĸ߶� 

    public static void main(String[] args) {  
        Main ge = new Main();  
        ge.ShowIt();  
    } 


    private void ShowIt(){  
        MyPanel panel = new MyPanel();//�õ�������  
        Thread t = new Thread(panel.huluwas0);//�������Ķ����߳�
        Thread t01 = new Thread(panel.huluwas1);
        Thread t02 = new Thread(panel.huluwas2);

        Thread t03 = new Thread(panel.huluwas3);
        Thread t04 = new Thread(panel.huluwas4);
        Thread t05 = new Thread(panel.huluwas5);
        Thread t06 = new Thread(panel.huluwas6);
        Thread t2 = new Thread(panel.xiezi);
        Thread t3 = new Thread(panel.laohan);
        Thread t4 = new Thread(panel.snake);
        Thread t5 = new Thread(panel.haha1);
        Thread t6 = new Thread(panel.haha2);
        Thread t7 = new Thread(panel.haha3);
        Thread t8 = new Thread(panel.haha4);
        Thread t9 = new Thread(panel.haha5);
        t4.start();
        t2.start();
        t5.start();          
        t6.start();
        t7.start();
        t8.start();          
        t9.start();
        t.start();

        t01.start();

        t02.start();
        t03.start();
        t04.start();
        t05.start();
        t06.start();


        t3.start();
        this.add(panel);//�������ص�Frame��������  
    }  
      
    public Main(){  
        this.setSize(WIDTH, HEIGHT);//����GUI����Ŀ��  
        this.setTitle("��«�޴�սŷ��");//���ñ���  
        this.setResizable(false);//���ô��ڴ�С���ɸı�  
        this.setLocationRelativeTo(null);//���ô���λ�þ���  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ĭ�Ϲرղ���  
        this.setVisible(true);//���ô��ڿɼ�  
        }  
      
      
      
} 




 class MyPanel extends JPanel {  
  
    int x = 0;  
    int y = 0;  
    int r = 50;  
  
      public     space hulushan=new space(17);
                     
      public  Huluwa   huluwas0=new Huluwa("����",COLOR.RED,1,hulushan,"src/a1.png");
      public  Huluwa   huluwas1=new Huluwa("����",COLOR.ORANGE,2,hulushan,"src/a2.png");
      public Huluwa    huluwas2=new Huluwa("����",COLOR.YELLOW,3,hulushan,"src/a3.png");
      public Huluwa huluwas3=new Huluwa("����",COLOR.GREEN,4,hulushan,"src/a4.png");
      public Huluwa     huluwas4=new Huluwa("����",COLOR.CYAN,5,hulushan,"src/a5.png");
      public Huluwa     huluwas5=new Huluwa("����",COLOR.BLUE,6,hulushan,"src/a6.png");
      public Huluwa     huluwas6=new Huluwa("����",COLOR.PURPLE,7,hulushan,"src/a7.png");
      public   yaoguai xiezi=new yaoguai(hulushan,"src/c6.png","Ы��");

      public   yaoguai snake=new yaoguai(hulushan,"src/c7.png","�߾�");
      public   yaoguai haha1=new yaoguai(hulushan,"src/c1.png","���");

      public   yaoguai haha2=new yaoguai(hulushan,"src/c2.png","����");
      public   yaoguai haha3=new yaoguai(hulushan,"src/c3.png","���");
      public   yaoguai haha4=new yaoguai(hulushan,"src/c2.png","��");
      public   yaoguai haha5=new yaoguai(hulushan,"src/c3.png","����");

   //   public   yaoguai snake=new yaoguai(hulushan,"src/c7.png","�߾�");           
         
      public supporting laohan=new supporting(hulushan,"src/d.png");                     


         



    @Override  
    public void paint(Graphics g) {  

           



        Image bg,hl,yg;  
        ImageIcon icon,icon2;  
        super.paint(g);  
        icon = new ImageIcon("src/b.jpg");  
        bg =icon.getImage();  
        g.drawImage(bg,0,0, null);  
          
        icon=new ImageIcon(laohan.GetPath());
        hl=icon.getImage();
        g.drawImage(hl,laohan.GetX()*50,laohan.GetY()*50,null); 


        icon =new ImageIcon(xiezi.GetPath());
        
        yg=icon.getImage();
        g.drawImage(yg,xiezi.GetX()*50,xiezi.GetY()*50,null);
        icon =new ImageIcon(haha1.GetPath());
        
        yg=icon.getImage();
        g.drawImage(yg,haha1.GetX()*50,haha1.GetY()*50,null);
        icon =new ImageIcon(haha2.GetPath());
        
        yg=icon.getImage();
        g.drawImage(yg,haha2.GetX()*50,haha2.GetY()*50,null);
        icon =new ImageIcon(haha3.GetPath());
        
        yg=icon.getImage();
        g.drawImage(yg,haha3.GetX()*50,haha3.GetY()*50,null);
        icon =new ImageIcon(haha4.GetPath());
        
        yg=icon.getImage();
        g.drawImage(yg,haha4.GetX()*50,haha4.GetY()*50,null);
        icon =new ImageIcon(haha5.GetPath());
        
        yg=icon.getImage();
        g.drawImage(yg,haha5.GetX()*50,haha5.GetY()*50,null);
        icon =new ImageIcon(snake.GetPath());
        
        yg=icon.getImage();
        g.drawImage(yg,snake.GetX()*50,snake.GetY()*50,null);

      
        icon=new ImageIcon(huluwas0.GetPath());
        hl=icon.getImage();
        g.drawImage(hl,huluwas0.GetX()*50,huluwas0.GetY()*50,null);

        icon=new ImageIcon(huluwas1.GetPath());
        hl=icon.getImage();
        g.drawImage(hl,huluwas1.GetX()*50,huluwas1.GetY()*50,null);
        icon=new ImageIcon(huluwas2.GetPath());
        hl=icon.getImage();
        g.drawImage(hl,huluwas2.GetX()*50,huluwas2.GetY()*50,null);

        icon=new ImageIcon(huluwas3.GetPath());
        hl=icon.getImage();
        g.drawImage(hl,huluwas3.GetX()*50,huluwas3.GetY()*50,null);
        icon=new ImageIcon(huluwas4.GetPath());
        hl=icon.getImage();
        g.drawImage(hl,huluwas4.GetX()*50,huluwas4.GetY()*50,null);

        icon=new ImageIcon(huluwas5.GetPath());
        hl=icon.getImage();
        g.drawImage(hl,huluwas5.GetX()*50,huluwas5.GetY()*50,null);
        
        icon=new ImageIcon(huluwas6.GetPath());
        hl=icon.getImage();
        g.drawImage(hl,huluwas6.GetX()*50,huluwas6.GetY()*50,null);
        
        

       // hulushan.printout();


                 
        paintComponents(g); 



    }  
      
     
  

      
}  

 