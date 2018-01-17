package nju.java;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import nju.java.common.Constant;

public class ReplayLoader implements Runnable {
    Field field;
    File file;
    ReplayLoader(Field field1, File file1){
        this.field = field1;
        file = file1;
    }

    @Override
    public void run() {
        try{
            field.setState(Constant.STATE.REPLAY);
            BufferedReader br = field.LoadSave(file);
            String line;
            String words[];
            int counter = 1;
            long last_time = 0;
            long current_time = 0;
            while((line = br.readLine()) != null & line != "\n"){
                words = line.split(" ");
                counter++;
                try {
                    if ( words.length != 1) throw new MySaveFormatException("Error Occurs on line " + counter +"  \""+ line +"\"");

                    ArrayList<Record> records = new ArrayList<>();
                    last_time = current_time;
                    current_time = Long.parseLong(words[0]);
                    Thread.sleep(current_time - last_time);

                    for ( int i = 0; i < field.getHumans().size() + field.getMonsters().size(); i++){
                        line = br.readLine();
                        counter++;
                        words = line.split(" ");
                        if ( words.length != 3 ) throw new MySaveFormatException("Error Occurs on line " + counter +"  \""+ line +"\"");
                        int x_coord = Integer.parseInt(words[0]);
                        int y_coord = Integer.parseInt(words[1]);
                        boolean is_alive = Boolean.parseBoolean(words[2]);
                        records.add( new Record(x_coord, y_coord, is_alive) );
                    }
                    field.ShowRecord(records);
                }catch ( MySaveFormatException | NumberFormatException e1){
                    //以上两种异常发生在读取+运行中，并且可以进行不重启处理。
                    e1.printStackTrace();
                    //创建对话框，打印异常，选择继续自由运行，或是重新开始
                    Object[] options ={ "重新开始", "自由运行" };
                    int n = JOptionPane.showOptionDialog(field.getParentFrame(),
                            "数据格式错误。请选择下一步操作", "标题",JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if ( n == 1) {
                        field.repaint();
                        field.StartPlay();
                        return;
                    }else{
                        field.restartLevel();
                        field.repaint();
                        return;
                    }
                }catch (InterruptedException e6){
                    throw new Exception(e6);
                }
            }
        } catch ( Exception e5 ){
            //所有其他异常的处理方式
            //包括FileNotFoundException， IOException e5， InterruptedException等
            JOptionPane.showMessageDialog(null, e5.getMessage(), "标题",JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.field.setState(Constant.STATE.REPLAYENDED);
        return;
    }
}
