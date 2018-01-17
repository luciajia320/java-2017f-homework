package niuxuTool;

import nju.java.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class newWindow {
	public static void printResult() {
        String resultStr=("final scores\nGoodTeam:BadTeam "+score.good_score+":"+score.bad_score+"\n");
        if(score.good_score>score.bad_score)
        	resultStr+=("justice victory!");
        else
        	resultStr+=("injustice victory!");
        story.writeStory(resultStr+"\r\n");
        score.good_score=0;
        score.bad_score=0;
        JFrame resultFrame = new JFrame();
        JPanel resultPanel = new JPanel();
        //result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setSize(80,120);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setTitle("result");
        JTextArea resultText = new JTextArea(resultStr);
        resultText.setLineWrap(true);
        resultText.setSize(150, 100);
        resultPanel.add(resultText);
        resultFrame.add(resultPanel);
        resultFrame.show();
	}
}
