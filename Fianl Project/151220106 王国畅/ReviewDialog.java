import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReviewDialog extends JFrame implements ActionListener{
	JButton open=null;
	File fl=null;
	
	public ReviewDialog(){
		open=new JButton("Open GameLog to Review");
		this.add(open);
		this.setSize(400,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		open.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
		jfc.showDialog(new JLabel(), "选择");
		File file=jfc.getSelectedFile();
		if(file.isDirectory()){
			System.out.println("文件夹:"+file.getAbsolutePath());
		}else if(file.isFile()){
			System.out.println("文件:"+file.getAbsolutePath());
		}
		fl = file;
		ReviewField revFld = new ReviewField(null, fl);
		Ground revGrd = new Ground(revFld,300,400);
		revGrd.setVisible(true);
	}

}

class ReviewField extends Field{
	private FileReader fr;
	private Image[] roles;
	
	public ReviewField(ExecutorService exec,File file) {
		super(exec);
		// TODO Auto-generated constructor stub
		roles = new Image[11];
        URL loc = this.getClass().getClassLoader().getResource("body.png");
        ImageIcon iia = new ImageIcon(loc);
		roles[0] = iia.getImage();
		for(int i=1;i<=7;i++){
			loc = this.getClass().getClassLoader().getResource("huluwa"+i+".png");
			iia = new ImageIcon(loc);
			roles[i] = iia.getImage();
		}
		loc = this.getClass().getClassLoader().getResource("Louluo.png");
		iia = new ImageIcon(loc);
		roles[8] = iia.getImage();
		loc = this.getClass().getClassLoader().getResource("Xiezijing.png");
		iia = new ImageIcon(loc);
		roles[9] = iia.getImage();
		loc = this.getClass().getClassLoader().getResource("Shejing.png");
		iia = new ImageIcon(loc);
		roles[10] = iia.getImage();
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        printGrass(g);
        for(int i = 0;i < M;i ++){
        	for(int j = 0;j < N;j ++){
        		try {
					int getNum = fr.read();
					if(getNum<11)
						g.drawImage(roles[i], j*OFFSET+OFFSET, i*OFFSET+OFFSET, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
        	}
        }
    }
}